package net.ruangtedy.java.spring.bookstore.validation;

import net.ruangtedy.java.spring.bookstore.domain.Address;
import net.ruangtedy.java.spring.bookstore.domain.Order;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class OrderValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return (Order.class).isAssignableFrom(clazz);
		
	}
	private void validateAddress(Address address, Errors errors, String type) {
		ValidationUtils.rejectIfEmpty(errors, type + ".street", "required", new Object[] { "Street" });
		ValidationUtils.rejectIfEmpty(errors, type + ".city", "required", new Object[] { "City" });
		ValidationUtils.rejectIfEmpty(errors, type + ".country", "required", new Object[] { "Country" });

	}
	@Override
	public void validate(Object target, Errors errors) {
		Order order = (Order) target;
		validateAddress(order.getShippingAddress(), errors, "shippingAddress");
		if (!order.isBillingSameAsShipping()) {
			validateAddress(order.getShippingAddress(), errors, "billingAddress");
		}
	}

}
