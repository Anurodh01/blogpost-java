package in.project.blogpost.customvalidator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserNameValidatorClass implements ConstraintValidator<UserNameValidator, String> {

	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		Logger logger = LoggerFactory.getLogger(UserNameValidator.class);
		
		logger.info("message from UsernameValidatorClass: {}", value);
		
		if(value.isBlank())
		{
			return false;
		}
		if(value.equals("anurodh"))
		{
			return false;
		}
		return true;
		
	}

}
