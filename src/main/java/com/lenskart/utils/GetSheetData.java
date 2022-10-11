package com.lenskart.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.gson.JsonObject;

public class GetSheetData {
	private static final String APPLICATION_NAME = "Lenskart";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String TOKEN_DIRECTORY_PATH = "tokens";

	/*
	 * Global instance of the scopes required by this quickstart. If modifying these
	 * scopes, delete your previously saved tokens/ folder.
	 */
	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
	private static final String CREDENTAILS_FILE_PATH = "/client_secret.json";

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @param HTTP_TRANSPORT The network HTTP Transport.
	 * @return An authorized Credential object.
	 * @throws IOException If the credentials.json file cannot be found.
	 */

	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		InputStream in = GetSheetData.class.getResourceAsStream(CREDENTAILS_FILE_PATH);
		if (in == null) {
			throw new FileNotFoundException("Resource not found: " + CREDENTAILS_FILE_PATH);
		}
		GoogleClientSecrets clientScrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// build flow and trigger user authorization request
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientScrets, SCOPES)
						.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKEN_DIRECTORY_PATH)))
						.setAccessType("offline").build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

	public static List<List<Object>> getGoogleSheetdata(String spreadsheetId, String range)
			throws GeneralSecurityException, IOException {
		// Build new Authorization API client serivice.
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		if (values == null || values.isEmpty()) {
			System.out.println("No data found");
			return null;
		} else {
			return values;
		}

	}

	public static List<HashMap<String, String>> getData() throws GeneralSecurityException, IOException {

		List<HashMap<String, String>> listOfTestData = new ArrayList<>();
		List<List<Object>> values = GetSheetData.getGoogleSheetdata("1omoL3HrP4GZZBA1I0ntmKty7yXiYU_Zk1wct3RufbXQ",
				"A1:X10");

		if (!values.isEmpty() && values != null) {
			List<Object> headers = values.get(0);
			String[] fieldNames = new String[headers.size()];

			for (int j = 0; j < headers.size(); j++) {
				fieldNames[j] = headers.get(j).toString();
			}

			for (int i = 1; i < values.size(); i++) {
				HashMap<String, String> dataMap = new HashMap<String, String>();

				List<Object> data = values.get(i);
				for (int k = 0; k < headers.size(); k++) {
					String fieldName;
					String value;
					fieldName = fieldNames[k];

					try {
						value = data.get(k).toString();
					} catch (IndexOutOfBoundsException e) {
						value = "";
					}
					dataMap.put(fieldName, value);
				}
				listOfTestData.add(dataMap);
			}
		}
		return listOfTestData;

	}

	public static List<List<Object>> getGoogleData() throws GeneralSecurityException, IOException {
		List<List<Object>> values = GetSheetData.getGoogleSheetdata("1omoL3HrP4GZZBA1I0ntmKty7yXiYU_Zk1wct3RufbXQ",
				"A2:X10");
		return values;

	}

	public static List<Object[]> getGoogleData1() throws GeneralSecurityException, IOException {
		List<List<Object>> l = GetSheetData.getGoogleData();
		// List<HashMap<String,String>> l = GetSheetData.getData();
		Object[] data = new Object[l.size()];
		List<Object[]> list = new ArrayList<Object[]>();

		for (int i = 0; i < data.length; i++) {
			data[i] = l.get(i);
			list.add(data);
		}
		return list;

	}
}

class Fetch {

	@Test
	public void testdata() throws GeneralSecurityException, IOException {
		List<HashMap<String, String>> obj = GetSheetData.getData();
		for(HashMap<String, String> s : obj) {
			System.out.println(s);
		}
		
		
		//List<HashMap<String, String>> value = GetSheetData.getData();
	}

	@Test(dataProvider = "testData1")
	public void test3(HashMap<String, String> data) {
		System.out.println("date: " + data.get("Date"));
		System.out.println("desc: " + data.get("details"));
		System.out.println("hour: " +  data.get("hour"));

	}

	@DataProvider(name = "testData2")
	public static Object[] testData2() throws IOException, GeneralSecurityException {
		Object[] obj = GetSheetData.getGoogleData().toArray();
		return obj;
//		List<Object[]> list = GetSheetData.getGoogleData1();
//		
//		for(int i = 0; i<list.size();i++) {
//			System.out.println(list.get(i).toString());
//			
//		}
		
}
	
	@DataProvider(name = "testData1")
	public static Object[] testData1() throws IOException, GeneralSecurityException {
		List<HashMap<String, String>> obj = GetSheetData.getData();
		Object[] hashMap = obj.toArray();
		return hashMap;
	}
	

//	@DataProvider
//	public Iterator<List<Object>> data() throws GeneralSecurityException, IOException{
//		List<List<Object>> s = GetSheetData.getData();
//		s.toString();
//		
//		return s.iterator();
//	}
}
