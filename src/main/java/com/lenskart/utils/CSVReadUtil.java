package com.lenskart.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class CSVReadUtil {

	protected static TestUtils utils = new TestUtils();
	private CSVReader reader;

	/**
	 * Creates a reader object and initializes with the csvfile provided.
	 * 
	 * @param csvReadFile
	 * @throws IOException
	 */
	public CSVReadUtil(String csvReadFile) throws IOException {
		reader = new CSVReader(new FileReader(csvReadFile));
		utils.log().debug("CSV reader initialized");
	}

	/**
	 * Creates a reader object and initializes with the csvfile provided where data
	 * are seperator by a custom seperator.
	 * 
	 * @param csvReadFile
	 * @throws IOException
	 */
	public CSVReadUtil(String csvReadFile, char seperator) throws IOException {
		reader = new CSVReader(new FileReader(csvReadFile), seperator);
		utils.log().debug("CSV reader initialized");
	}

	/**
	 * Creates a reader object and initializes with the csvfile provided where data
	 * are separator by a custom separtor and can skip lines
	 * 
	 * @param csvReadFile
	 * @param seperator
	 * @param skipLines
	 * @throws IOException
	 */
	public CSVReadUtil(String csvReadFile, char seperator, int skipLines) throws IOException {
		reader = new CSVReaderBuilder(new FileReader(csvReadFile)).withSkipLines(skipLines).build();
		utils.log().debug("CSV reader initialized");
	}

	/**
	 * returns the list containing arrays of strings; each array of string
	 * represents a line in CSV
	 * 
	 * @return List of arrays of strings
	 * @throws IOException
	 */
	public List<String[]> getEntriesAsList() throws IOException {
		List<String[]> myEntries = reader.readAll();
		utils.log().debug("Converted CSV file into List of String arrays");
		return myEntries;
	}

}
