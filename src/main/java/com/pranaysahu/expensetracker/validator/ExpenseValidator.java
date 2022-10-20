package com.pranaysahu.expensetracker.validator;

import com.pranaysahu.expensetracker.dto.ExpenseDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;

public class ExpenseValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return ExpenseDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ExpenseDTO expenseDTO = (ExpenseDTO) target;
        if(expenseDTO.getDateString().equals("")
                || expenseDTO.getDateString().isEmpty()
                || expenseDTO.getDateString() == null){

            errors.rejectValue("dateString",
                    null,
                    "Expense Date should not be null");

        }
    }
}
