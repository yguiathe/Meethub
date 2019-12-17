package com.tayfint.meethub.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tayfint.meethub.model.User;
import com.tayfint.meethub.service.UserService;
import com.tayfint.meethub.validator.EmailValidator;

@Component
public class UserFormValidator implements Validator {

	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;
	
	@Autowired
	UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		User user = (User) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.userForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm.email");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.userForm.address");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword","NotEmpty.userForm.confirmPassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.userForm.gender");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "citizenship", "NotEmpty.userForm.citizenship");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "primaryId", "NotEmpty.userForm.primaryId");

		if(!emailValidator.valid(user.getEmail())){
			errors.rejectValue("email", "Pattern.userForm.email");
		}

		if((user.getFirstName() == null) || (user.getLastName() == null)){
			errors.rejectValue("lastName", "NotEmpty.userForm.lastname");
		}

		if(user.getCitizenship() == null){
			errors.rejectValue("citizenship", "NotEmpty.userForm.citizenship");
		}

		if (!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "Diff.userform.confirmPassword");
		}

		if (user.getGender() == null) {
			errors.rejectValue("gender", "NotEmpty.userForm.sex");
		}

		if (user.getPrimaryId() == null) {
			errors.rejectValue("primaryId", "NotEmpty.userForm.primaryId");
		}

	}

}
