package com.pranaysahu.expensetracker.controller;

import com.pranaysahu.expensetracker.dto.ExpenseDTO;
import com.pranaysahu.expensetracker.dto.ExpenseFilterDTO;
import com.pranaysahu.expensetracker.service.ExpenseService;
import com.pranaysahu.expensetracker.util.DateTimeUtil;
import com.pranaysahu.expensetracker.validator.ExpenseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping("/expenses")
    public String showExpenseList(Model model) {
        List<ExpenseDTO> list =expenseService.getAllExpenses();
//        list = null;
//        list.size();
        model.addAttribute("expenses", list);
        model.addAttribute("filter", new ExpenseFilterDTO(DateTimeUtil.getCurrentMonthStartDate(), DateTimeUtil.getCurrentMonthDate()));
        String totalExpenses = expenseService.totalExpenses(list);
        model.addAttribute("totalExpenses", totalExpenses);
        return "expenses-list";
    }
    @GetMapping("/createExpense")
    public String showExpenseForm(Model model){
        model.addAttribute("expense", new ExpenseDTO());
        return "expense-form";
    }
    @PostMapping("/saveOrUpdateExpense")
    public String saveOrUpdateExpenseDetails(@Valid @ModelAttribute("expense") ExpenseDTO expenseDTO,
                                        BindingResult result) throws ParseException {
        System.out.println("Printing the Expense DTO: "+ expenseDTO);

        new ExpenseValidator().validate(expenseDTO, result);

        if(result.hasErrors()){
            return "expense-form";
        }
        expenseService.saveExpenseDetails(expenseDTO);
        return "redirect:/expenses";
    }

    @GetMapping("/deleteExpense")
    public String deleteExpense(@RequestParam String id){
        System.out.println("Printing the expense id:" +id);
        expenseService.deleteExpense(id);
        return "redirect:/expenses";
    }

    @GetMapping("/updateExpense")
    public String updateExpense(@RequestParam String id, Model model){
        System.out.println("Printing the expense Id inside the update method :" +id);
        ExpenseDTO expense = expenseService.getExpenseById(id);
        model.addAttribute("expense", expense);
        return "expense-form";
    }





}
