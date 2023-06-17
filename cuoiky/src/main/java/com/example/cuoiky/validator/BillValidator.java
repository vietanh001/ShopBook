package com.example.cuoiky.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.example.cuoiky.model.Bill;
import com.example.cuoiky.model.Book;
import com.example.cuoiky.service.BillService;


@Component
public class BillValidator implements Validator{
	
	private final BillService billService;
	
	
	
	public BillValidator(BillService billService) {
		super();
		this.billService = billService;
	}
	
	
	public boolean supports(Class<?> aClass) {
		return Book.class.equals(aClass);
	}
	
	public void validate(Object o, Errors errors) {
		Bill bill = (Bill) o;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gmail", "NotGmail");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotPhone");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotAddress");
		
		/*
		 * String phone = bill.getPhone(); if(!phone.matches("[0-9]{10}") &&
		 * !phone.equals("")) { errors.rejectValue("phone", "DuplicatePhone"); }
		 * 
		 * String gmail = bill.getGmail(); int n = gmail.length(); if (n > 10) { String
		 * mail = gmail.substring(n - 10, n); if (!mail.equals("@gmail.com")) {
		 * errors.rejectValue("gmail", "DuplicateMail"); } } else { if
		 * (!gmail.equals("")) { errors.rejectValue("gmail", "DuplicateMailSmall"); } }
		 */

	}


	@Override
	public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName, Class<?>... groups) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, String propertyName, Object value,
			Class<?>... groups) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public BeanDescriptor getConstraintsForClass(Class<?> clazz) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <T> T unwrap(Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ExecutableValidator forExecutables() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
