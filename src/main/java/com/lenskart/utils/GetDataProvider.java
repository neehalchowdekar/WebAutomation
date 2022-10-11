package com.lenskart.utils;

import java.io.IOException;

public class GetDataProvider {
	
	 private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public GetDataProvider(String path) {
		this.path = path;
	}
	
	

	public String[][] getData() throws IOException{
		String path = System.getProperty("user.dir") + this.path;

		// Get row and colounm from execl sheet
		int rowcount = XLUtility.getRowCount(path, "Sheet1");
		int colcount = XLUtility.getCellCount(path, "Sheet1", 1);

		// create a static 2D array string and add row count and col count
		String data[][] = new String[rowcount][colcount];

		// Run for loop to capture the data from execl sheet
		for (int i = 1; i <= rowcount; i++) {
			for (int j = 0; j < colcount; j++) {
				// as there will be header for i row we should neglect it so
				// using i-1 for that
				data[i - 1][j] = XLUtility.getCellData(path, "Sheet1", i, j);
			}
		}

		// String emp[][] = {{"qwe123","12000","20"}, {"ert123","2321","23"}};
		return data;

	}
	}

