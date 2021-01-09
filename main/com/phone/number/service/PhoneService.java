package com.phone.number.service;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.phone.number.common.PhoneUtil;
import com.phone.number.common.WordUtil;
import com.phone.number.dto.PhoneNumber;
import com.phone.number.model.PhoneDictionary;
import com.phone.number.model.WordDictionary;

/**
 * 
 * @author bpoomalai
 * 
 *         Service class to handles all the functionalities
 *
 */

public class PhoneService {

	private static PhoneService instance;
	static WordDictionary wordDictionary;

	PhoneDictionary phoneDictionary = new PhoneDictionary();

	public static PhoneService getInstance(WordDictionary wordDict) {
		if (instance == null) {
			instance = new PhoneService();
			wordDictionary = wordDict;
		}
		return instance;
	}

	/*
	 * Print all the possible matches of number if found else return null
	 */
	public Set<String> printPossibleMatches(String number) {
		if (StringUtils.isBlank(number)) {
			return null;
		}

		String normalizedValue = PhoneUtil.normalize(number.trim());
		if (StringUtils.isBlank(normalizedValue)) {
			return null;
		}
		WordUtil wordUtil = WordUtil.getInstance(phoneDictionary);

		PhoneNumber phoneNumber = new PhoneNumber(normalizedValue);

		List<Set<String>> alphabetsList = getAlphabetsList(phoneNumber);

		if (alphabetsList == null || alphabetsList.size() == 0) {
			return null;
		}
		// Find the permutations of list of set of alphabets
		Set<List<String>> permutations = Sets.cartesianProduct(alphabetsList);
		Set<String> matches = Sets.newHashSet();
		// For each permutation, segment the string into readable words based on
		// dictionary
		Set<String> processedMatchWords = Sets.newHashSet();
		for (List<String> permutation : permutations) {
			String permutationText = PhoneUtil.toString(permutation);
			List<String> wordBreakList = wordDictionary.segmentString(permutationText);
			if (wordBreakList != null && !processedMatchWords.contains(String.join("", wordBreakList))) {
				String wordBreakText = wordUtil.joinWordBreaks(permutation, wordBreakList);
				if (StringUtils.isNotBlank(wordBreakText)) {
					matches.add(wordBreakText);
					// This is to prevent duplicate processing of same processed match
					processedMatchWords.add(String.join("", wordBreakList));
				}
			}
		}
		return matches;
	}

	/*
	 * Get alphabets list mapped to each digit
	 */
	private List<Set<String>> getAlphabetsList(PhoneNumber phoneNumber) {
		List<Set<String>> alphabetsList = Lists.newArrayList();

		for (String digit : phoneNumber.toStringList()) {
			Set<String> letters = phoneDictionary.getAlphabets(digit);
			if (letters == null) {
				return null;
			}
			alphabetsList.add(letters);
		}
		return alphabetsList;
	}

}
