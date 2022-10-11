package com.lenskart.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;

public class CSVWriteUtil {
	
	TestUtils utils = new TestUtils();
	
	  private CSVWriter writer;

	  public CSVWriteUtil(String csvWriteFile) throws IOException {
	    writer = new CSVWriter(new FileWriter(csvWriteFile));
	    utils.log().debug("CSV write initialized...");
	  }

	  public CSVWriteUtil(String csvWriteFile, boolean append) throws IOException {
	    writer = new CSVWriter(new FileWriter(csvWriteFile, append));
	    utils.log().debug("CSV write initialized...");
	  }

	  public CSVWriteUtil(String csvWriteFile, char seperator) throws IOException {
	    writer = new CSVWriter(new FileWriter(csvWriteFile), seperator);
	    utils.log().debug("CSV write initialized with a custom seperator..");
	  }

	  public CSVWriteUtil(String csvWriteFile, char seperator, Boolean append) throws IOException {
	    writer = new CSVWriter(new FileWriter(csvWriteFile, append), seperator);
	    utils.log().debug("CSV write initialized with a custom seperator..");
	  }

	  public void addLineToCSV(String[] entries) throws IOException {
	    writer.writeNext(entries);
	    utils.log().debug("wrote a line to CSV file");
	  }

	  public void addAllToCSV(List<String[]> allLines) throws IOException {
	    writer.writeAll(allLines);
	    utils.log().debug("wrote multiple lines to CSV file");
	  }

	  public void closeWriter() throws IOException {
	    writer.close();
	    utils.log().debug("CSV writer closed");
	  }

}
