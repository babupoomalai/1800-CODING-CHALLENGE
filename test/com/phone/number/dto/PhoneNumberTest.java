package com.phone.number.dto;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PhoneNumberTest {

	@Test
	public void testToStringList() {
		String number = "225563";
		PhoneNumber phoneNumber = new PhoneNumber(number);
		List<String> expectedList = Arrays.asList("2", "2", "5", "5", "6", "3");
		Assert.assertEquals(phoneNumber.toStringList(), expectedList);
	}

}
