package com.phone.number.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.phone.number.common.ConfigUtil;
import com.phone.number.dto.PhoneNumber;

/**
 * 
 * @author bpoomalai
 *
 */

public class PhoneDirectory {

	private String directoryPath = ConfigUtil.PHONE_DICTIONARY_PATH;

	public List<PhoneNumber> getListOfNumbersInDirectory() {
		List<PhoneNumber> listOfNumbers = new ArrayList<PhoneNumber>();
		try {
			File directoryInput = new File(directoryPath);
			BufferedReader br = new BufferedReader(new FileReader(directoryInput));
			String line = null;
			while ((line = br.readLine()) != null) {
				PhoneNumber number = new PhoneNumber(line);
				listOfNumbers.add(number);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return listOfNumbers;
	}

}
