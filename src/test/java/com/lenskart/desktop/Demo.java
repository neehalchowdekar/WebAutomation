package com.lenskart.desktop;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

public class Demo {

	
	@Test
	public void callup() {
//		String s = something("2");
//		System.out.println(s);
		
		String a = "Wed, 6  |  9am-11am";
		a = a.replaceAll("[^a-zA-Z0-9]", "");
		System.out.println(a);
		String b = "Wednesday April 6 09:00 AM - 11:00 AM";

	}
	
	
	public static String something(String data) {
			String result = "";
			try {
				int plus = Integer.parseInt(data);
				Date dt = new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(dt);
				c.add(Calendar.DATE, plus);
				dt = c.getTime();
				String s = dt.toString();
				System.out.println(s);
				result = s.substring(8, 10);
				//System.out.println("rre " + result.indexOf('0'));
				if(result.indexOf('0') == 0) result = result.substring(1);
				System.out.println("Current Date + " + data + " is :- " + result);
			} catch (Exception e) {
				System.out.println("Exception Occured while getting desire date " + e);
			}
			return result;
		}
	
	public static boolean containsCheck(String a, String b) {
		return a.contains(b);
	}
	
	public static String dayFullName(String dayWithThreewords) {
		Map<String, String> fullDayName = Map.of("Mon","Monday", "Tue", "Tuesday", "Wed", "Wednesday", "Thur", "Thursday",
				"Fri", "Firday", "Sat","Saturday", "Sun", "Sunday");
		if( fullDayName.containsKey(dayWithThreewords))
			return fullDayName.get(dayWithThreewords);
		return "Wrong day";
	}
	
}
