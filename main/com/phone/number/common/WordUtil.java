package com.phone.number.common;

import java.util.List;
import java.util.Set;

import com.phone.number.model.PhoneDictionary;
import com.phone.number.model.WordDictionary;

public class WordUtil {

	final String DELIMITER = "-";

	private static WordUtil instance;
	private PhoneDictionary phoneDictionary;

	private WordUtil(PhoneDictionary phoneDictionary) {
		this.phoneDictionary = phoneDictionary;
	}

	public static WordUtil getInstance(PhoneDictionary phoneDictionary) {
		if (instance == null) {
			instance = new WordUtil(phoneDictionary);
		}
		return instance;
	}

	public String joinWordBreaks(List<String> permutation, List<String> wordBreaks) {
		if (wordBreaks != null && wordBreaks.size() > 0) {
			int index = 0;
			int digitsAdded = 0;
			StringBuilder sb = new StringBuilder();

			for (String wordBreak : wordBreaks) {
				char wb = wordBreak.charAt(0);
				String charText = Character.toString(wb);
				if (!charText.equals(permutation.get(index))) {
					if (digitsAdded < WordDictionary.DIGITS_ALLOWED) {
						String digit = phoneDictionary.getDigit(charText);
						sb.append(digit + DELIMITER);
						index++;
						digitsAdded++;
					} else {
						return "No match found";
					}
				}
				index += wordBreak.length();
				sb.append(wordBreak + DELIMITER);
			}

			while (index < permutation.size()) {
				if (digitsAdded < WordDictionary.DIGITS_ALLOWED) {
					String digit = phoneDictionary.getDigit(permutation.get(index));
					sb.append(digit + DELIMITER);
					index++;
					digitsAdded++;
				} else {
					return "No match found";
				}
			}

			return sb.substring(0, sb.length() - 1);
		}
		return null;
	}

}
