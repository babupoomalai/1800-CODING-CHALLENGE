package com.phone.number.service;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Sets;
import com.phone.number.model.WordDictionary;

public class PhoneServiceTest {

	PhoneService instance = PhoneService.getInstance(new WordDictionary());

	@Test
	public void testPrintAllPossibleMatches() {
		Set<String> matches = instance.printPossibleMatches("3569377");
		Assert.assertEquals(matches, Sets.newHashSet("FLOWERS"));
	}

	@Test
	public void testPrintBadInput() {
		Set<String> matches = instance.printPossibleMatches("123");
		Assert.assertNull(matches);
	}

	@Test
	public void testPrintAllPossibleMatchesIgnoreSpacesAndPunctuattion() {
		Set<String> matches = instance.printPossibleMatches("3569377   .,");
		Assert.assertEquals(matches, Sets.newHashSet("FLOWERS"));
	}

	@Test
	public void testPrintNullString() {
		Set<String> matches = instance.printPossibleMatches(null);
		Assert.assertNull(matches);
	}

	@Test
	public void testPrintEmptyString() {
		Set<String> matches = instance.printPossibleMatches("");
		Assert.assertNull(matches);
	}

	@Test
	public void testPrintAllPossibleMatchesOneDigit() {
		Set<String> matches = instance.printPossibleMatches("35693772");
		Assert.assertEquals(matches, Sets.newHashSet("FLOWERS-2"));
	}

	@Test
	public void testPrintAllPossibleMatchesMultipleWords() {
		Set<String> matches = instance.printPossibleMatches("2342779");
		Assert.assertEquals(matches, Sets.newHashSet("BE-HAPPY"));
	}

	@Test
	public void testPrintAllPossibleMatchesTwoDigits() {
		Set<String> matches = instance.printPossibleMatches("356937722");
		Assert.assertTrue(matches == null || matches.size() == 0);
	}

}
