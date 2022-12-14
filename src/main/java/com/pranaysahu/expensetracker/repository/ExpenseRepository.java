package com.pranaysahu.expensetracker.repository;

import com.pranaysahu.expensetracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense , Long> {

    // SELECT * FROM tbl_expenses WHERE expenseId=?
    Optional<Expense>findByExpenseId(String id);

    //SELECT * FROM tbl_expenses WHERE name LIKE %keyword%
    List<Expense> findByNameContainingAndDateBetweenAndUserId(String keyword, Date startDate, Date endDate, Long id);


    List<Expense> findByUserId(Long id);

    List<Expense> findByDateBetweenAndUserId(Date startDate, Date endDate, Long id);
}
