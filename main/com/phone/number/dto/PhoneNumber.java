package com.phone.number.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author bpoomalai
 *
 */

public class PhoneNumber {

	private String number;

	public PhoneNumber(String number) {
		super();
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public List<String> toStringList() {
		List<String> numberList = new ArrayList<String>();
		for (int i = 0; i < number.length(); i++) {
			char c = number.charAt(i);
			numberList.add(Character.toString(c));
		}
		return numberList;
	}
}
