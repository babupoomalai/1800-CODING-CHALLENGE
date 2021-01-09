package com.phone.number.common;

import java.util.List;

/**
 * 
 * @author bpoomalai
 *
 */

public class PhoneUtil {

	public static String normalize(String number) {
		String whiteSpaceRemoved = removeWhiteSpace(number);
		String punctutationsRemoved = removePunctuation(whiteSpaceRemoved);

		return punctutationsRemoved;
	}

	public static String toString(List<String> list) {
		StringBuilder sb = new StringBuilder();
		for (String s : list) {
			sb.append(s);
		}
		return sb.toString();
	}

	private static String removeWhiteSpace(String string) {
		return string.replaceAll("\\s", "");
	}

	private static String removePunctuation(String string) {
		return string.replaceAll("[^a-zA-Z0-9]", "");
	}
}
