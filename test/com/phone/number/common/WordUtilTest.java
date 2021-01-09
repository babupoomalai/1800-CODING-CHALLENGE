package com.phone.number.common;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.phone.number.model.PhoneDictionary;

public class WordUtilTest {

	WordUtil instance = WordUtil.getInstance(new PhoneDictionary());

	@Test
	public void testJoinWordBreaksFoundWordForAllDigits() {
		String expectedTest = "CALL-ME";
		String result = instance.joinWordBreaks(Lists.newArrayList("C", "A", "L", "L", "M", "E"),
				Lists.newArrayList("CALL", "ME"));
		Assert.assertEquals("Both string should be equal", expectedTest, result);
	}

	@Test
	public void testJoinWordBreaksOneNumberExistsEnd() {
		String expectedTest = "CALL-ME-2";
		String result = instance.joinWordBreaks(Lists.newArrayList("C", "A", "L", "L", "M", "E", "A"),
				Lists.newArrayList("CALL", "ME"));
		Assert.assertEquals("Both string should be equal", expectedTest, result);
	}

	@Test
	public void testJoinWordBreaksOneNumberExistsStart() {
		String expectedTest = "2-CALL-ME";
		String result = instance.joinWordBreaks(Lists.newArrayList("A", "C", "A", "L", "L", "M", "E"),
				Lists.newArrayList("CALL", "ME"));
		Assert.assertEquals("Both string should be equal", expectedTest, result);
	}

	@Test
	public void testJoinWordBreaksMoreDigitsExistsThanAllowed() {
		String result = instance.joinWordBreaks(Lists.newArrayList("A", "A", "C", "A", "L", "L", "M", "E"),
				Lists.newArrayList("CALL", "ME"));
		Assert.assertEquals("Both string should be equal", "No match found", result);
	}

}
