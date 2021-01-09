package com.phone.number.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.phone.number.common.ConfigUtil;

/**
 * 
 * @author bpoomalai
 *
 */
public class WordDictionary {

	private String dictionaryPath = ConfigUtil.WORD_DICTIONARY_PATH;

	TrieNode root = new TrieNode();
	static final int ALPHABET_SIZE = 26;
	public static final int DIGITS_ALLOWED = 1;

	// Trie node
	class TrieNode {
		boolean endOfTree;
		TrieNode children[] = new TrieNode[ALPHABET_SIZE];

		TrieNode() {
			endOfTree = false;
			for (int i = 0; i < ALPHABET_SIZE; i++) {
				children[i] = null;
			}
		}
	}

	public WordDictionary() {
		setDictionaryData();
	}

	private void setDictionaryData() {
		try {
			try (Stream<String> stream = Files.lines(Paths.get(dictionaryPath))) {
				stream.forEach(line -> insert(line));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// If not present, inserts a key into the trie
	public void insert(String word) {
		int length = word.length();
		word = word.toUpperCase();

		int index;

		TrieNode pcrawl = root;

		for (int i = 0; i < length; i++) {
			index = word.charAt(i) - 'A';

			if (pcrawl.children[index] == null) {
				pcrawl.children[index] = new TrieNode();
			}

			pcrawl = pcrawl.children[index];
		}

		// mark last node as leaf
		pcrawl.endOfTree = true;

	}

	// function break the string
	List<String> wordBreaks = Lists.newArrayList();

	/*
	 * Break the sentence to understandable words
	 */
	public List<String> segmentString(String word) {
		wordBreaks = Lists.newArrayList();
		wordBreak(root, word, 0, 0, 0);
		if (StringUtils.join(wordBreaks, "").length() >= word.length() - 1) {
//			return StringUtils.join(wordBreaks, "-");
			return wordBreaks;
		}
//		else if (digitsProcessed == 1) {
//			digitsProcessed = wordBreak(root, word.substring(1), 0, 0);
//			if (StringUtils.join(wordBreaks, "").length() >= word.length() - 1) {
//				return StringUtils.join(wordBreaks, "-");
//			}
//		}
		return null;
	}

	private int wordBreak(TrieNode node, String word, int start, int level, int digitsAdded) {
		TrieNode pCrawl = node;

		if (start == word.length()) {
			return -1;
		}

		int index = start;
		for (; index < word.length(); index++) {
			int wordChar = word.charAt(index) - 'A';
			if (pCrawl == null || pCrawl.children[wordChar] == null) {
				int digitsCount = index - start;
				if (DIGITS_ALLOWED > digitsAdded && digitsCount <= (DIGITS_ALLOWED - digitsAdded)) {
					digitsAdded += digitsCount;
//					System.out.println(word + ", " + start + " " + i + ", " + digitsAdded);
//					wordBreak(node, word, i, level, digitsAdded);
//					for (int y = 0; y < digitsCount; y++) {
					pCrawl = root.children[wordChar];
					start += digitsAdded;
//					}
					continue;
				} else {
					return index;
				}
			} else if (pCrawl.children[wordChar].endOfTree) {
				wordBreaks.add(word.substring(start, index + 1).toUpperCase());
				wordBreak(root, word, index + 1, level + 1, digitsAdded);

			}
			pCrawl = pCrawl.children[wordChar];
		}
		return index;
	}

}
