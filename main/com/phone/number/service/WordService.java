package com.phone.number.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.phone.number.common.ConfigUtil;
import com.phone.number.model.PhoneDictionary;
import com.phone.number.model.WordDictionary;

public class WordService {
	private static WordService instance;
	static WordDictionary wordDictionary;

	PhoneDictionary phoneDictionary = new PhoneDictionary();

	public static WordService getInstance(WordDictionary wordDict) {
		if (instance == null) {
			instance = new WordService();
			wordDictionary = wordDict;
		}
		return instance;
	}

	public void addWords(List<String> words) {
		if (words.size() > 0) {
			return;
		}

		try {
			FileWriter fw = new FileWriter(ConfigUtil.WORD_DICTIONARY_PATH, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			out.println();

			for (String word : words) {
				out.println(word);
			}

			out.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Error while adding words to dictionary");
		}
	}

}
