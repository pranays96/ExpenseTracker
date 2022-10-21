package com.pranaysahu.expensetracker.repository;

import com.pranaysahu.expensetracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
