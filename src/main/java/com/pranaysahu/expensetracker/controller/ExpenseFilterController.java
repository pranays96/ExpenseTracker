package com.pranaysahu.expensetracker.controller;

import com.pranaysahu.expensetracker.dto.ExpenseDTO;
import com.pranaysahu.expensetracker.dto.ExpenseFilterDTO;
import com.pranaysahu.expensetracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.ParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExpenseFilterController {

    private final ExpenseService expenseService;

    @GetMapping("/filterExpenses")
    public String filterExpenses(@ModelAttribute("filter")ExpenseFilterDTO expenseFilterDTO, Model model) throws ParseException {
        System.out.println("Printing the filter dto:" +expenseFilterDTO);
        List<ExpenseDTO> list = expenseService.getFilteredExpenses(expenseFilterDTO);
        model.addAttribute("expenses", list);
        String totalExpenses = expenseService.totalExpenses(list);
        model.addAttribute("totalExpenses", totalExpenses);
        return "expenses-list";
    }


}
