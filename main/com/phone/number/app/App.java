package com.phone.number.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.phone.number.model.WordDictionary;
import com.phone.number.service.PhoneService;
import com.phone.number.service.WordService;

/**
 * 
 * @author bpoomalai
 *
 */
public class App {

	static WordDictionary wordDictionary = new WordDictionary();
	static PhoneService phoneService = PhoneService.getInstance(wordDictionary);

	public static void main(String args[]) {
		WordService wordService = WordService.getInstance(wordDictionary);

		if (args.length > 0) {
			// Add words to dictionary
			if (args[0].equalsIgnoreCase("-d")) {
				if (args[1].equalsIgnoreCase("add")) {
					Set<String> words = Sets.newHashSet();
					for (int i = 2; i < args.length; i++) {
						words.add(args[i]);
					}
					wordService.addWords(Lists.newArrayList(words));
				} else {
					System.out.println("Bad Input");
					return;
				}

			} else {
				// File reading
				String filePath = args[0];
				try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
					stream.forEach(line -> doProcess(line));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					System.out.println("Bad Input");
					return;
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Bad Input");
					return;
				}
			}
		} else {
			// command line input
			String input = "";
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter phone number: ");
			input = sc.nextLine();
			doProcess(input);
			sc.close();
		}
	}

	private static void doProcess(String word) {
		Set<String> matches = phoneService.printPossibleMatches(word);
		System.out.println("======= For Number: " + word + " =======");

		if (matches == null || matches.size() == 0) {
			System.out.println("Bad input \n");
			return;
		}
		// Print
		if (matches.size() > 0) {
			for (String match : matches) {
				System.out.println(match);
			}
		} else {
			System.out.println("No match found");
		}
		System.out.println();
	}
}
