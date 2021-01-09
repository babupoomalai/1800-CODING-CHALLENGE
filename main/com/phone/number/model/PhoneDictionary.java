package com.phone.number.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Sets;
import com.phone.number.common.ConfigUtil;

/**
 * 
 * @author bpoomalai
 * 
 *         Dictionary for Phone digits and equivalent alphabets
 *
 */

public class PhoneDictionary {

	private String dictionaryPath = ConfigUtil.PHONE_DICTIONARY_PATH;
	private Properties dictionaryData = new Properties();

	public PhoneDictionary() {
		setDictionaryData();
	}

	public Set<String> getAlphabets(String number) {
		String phrase = dictionaryData.getProperty(number);
		Set<String> letters;
		if (StringUtils.isBlank(phrase)) {
			return null;
		} else {
			letters = Sets.newHashSet(phrase.toUpperCase().split("\\s*,\\s*"));
		}
		return letters;
	}

	public String getDigit(String alphabet) {
		Collection<Object> keys = dictionaryData.keySet();
		for (Object key : keys) {
			String keyText = (String) key;
			Set<String> alphabets = getAlphabets(keyText);
			if (alphabets.contains(alphabet)) {
				return keyText;
			}
		}
		return null;
	}

	private void setDictionaryData() {
		try {
			File file = new File(dictionaryPath);
			FileInputStream fileInput;
			fileInput = new FileInputStream(file);
			dictionaryData.load(fileInput);
			fileInput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
