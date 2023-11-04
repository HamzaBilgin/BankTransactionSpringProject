package com.bank.springbank.genericMetods;

import org.springframework.stereotype.Component;

@Component
public class GenericMetods {
	public static <T extends Enum<T>> boolean isValidEnum(String value, Class<T> enumType) {
	    try {
	        Enum.valueOf(enumType, value);
	        return true;
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	}
}
