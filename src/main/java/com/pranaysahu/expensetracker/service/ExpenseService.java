package com.pranaysahu.expensetracker.service;

import com.pranaysahu.expensetracker.dto.ExpenseDTO;
import com.pranaysahu.expensetracker.dto.ExpenseFilterDTO;
import com.pranaysahu.expensetracker.entity.Expense;
import com.pranaysahu.expensetracker.repository.ExpenseRepository;
import com.pranaysahu.expensetracker.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;


    public List<ExpenseDTO> getAllExpenses(){
        List<Expense> list = expenseRepository.findAll();
        List<ExpenseDTO> expenseList = list.stream().map(this::mapToDTO).collect(Collectors.toList());
        return expenseList;
    }

    private ExpenseDTO mapToDTO(Expense expense) {
        ExpenseDTO expenseDTO = modelMapper.map(expense, ExpenseDTO.class);
        expenseDTO.setDateString(DateTimeUtil.convertDateToString(expenseDTO.getDate()));
        return expenseDTO;
    }

    public ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO) throws ParseException {
        //map the DTO to entity
        Expense expense = maptoEntity(expenseDTO);

        //save the entity to database
        expense = expenseRepository.save(expense);

        //map the entity to DTO
        return mapToDTO(expense);
    }

    private Expense maptoEntity(ExpenseDTO expenseDTO) throws ParseException {
        //map the DTO to entity
        Expense expense =modelMapper.map(expenseDTO, Expense.class);

        //generate the expense id
        if(expense.getId() == null){
            expense.setExpenseId(UUID.randomUUID().toString());
        }

        //set the expense date
        expense.setDate(DateTimeUtil.convertStringToDate(expenseDTO.getDateString()));



        //return the expense entity
        return expense;
    }

    public void deleteExpense(String id){
        Expense existingExpense = getExpense(id);
        expenseRepository.delete(existingExpense);
    }

    public ExpenseDTO getExpenseById(String id){
        Expense existingExpense = getExpense(id);
        return mapToDTO(existingExpense);
    }

    private Expense getExpense(String id){
        return expenseRepository.findByExpenseId(id).orElseThrow(() -> new RuntimeException("Expense not found for the id"+ id));
    }
    public List<ExpenseDTO> getFilteredExpenses(ExpenseFilterDTO expenseFilterDTO) throws ParseException {
        String keyword = expenseFilterDTO.getKeyword();
        String sortBy = expenseFilterDTO.getSortBy();
        String startDateString = expenseFilterDTO.getStartDate();
        String endDateString =expenseFilterDTO.getEndDate();

        Date startDate = !startDateString.isEmpty() ? DateTimeUtil.convertStringToDate(startDateString) : new Date(0);
        Date endDate = !endDateString.isEmpty() ? DateTimeUtil.convertStringToDate(endDateString) : new Date(System.currentTimeMillis());

        List<Expense> list = expenseRepository.findByNameContainingAndDateBetween(keyword, startDate, endDate);
        List<ExpenseDTO> filteredList = list.stream().map(this::mapToDTO).collect(Collectors.toList());
        if (sortBy.equals("date")) {
            //sort it by expense date
            filteredList.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        }else {
            //sort it by expense amount
            filteredList.sort((o1, o2) -> o2.getAmount().compareTo(o1.getAmount()));
        }
        return filteredList;
    }


}
