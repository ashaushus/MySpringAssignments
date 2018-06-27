package com.project.demo.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.project.demo.models.Category;

@Component
public class CategoryValidator implements Validator {
   
	@Override
    public boolean supports(Class<?> aClass) {
        return Category.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "text.error.name.NotEmpty");
    
    }

}
