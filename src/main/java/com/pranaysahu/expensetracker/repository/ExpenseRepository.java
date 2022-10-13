package com.pranaysahu.expensetracker.repository;

import com.pranaysahu.expensetracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense , Long> {

}
