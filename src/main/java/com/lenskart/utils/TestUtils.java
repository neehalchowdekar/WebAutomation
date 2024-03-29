package com.lenskart.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lenskart.base.WebBaseTest;




public class TestUtils {
	public static final long WAIT =30;
	
	public HashMap<String, String> parseStringXml(InputStream file) throws Exception {
		HashMap<String, String> stringMap = new HashMap<String, String>();
		//Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		//Build Doucment
		Document document = builder.parse(file);
		
		//Normalize the XML structure
		document.getDocumentElement().normalize();
		
		//Here comes the root node
		Element root = document.getDocumentElement();
		
		//get all elements
		NodeList nList = document.getElementsByTagName("string");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node node = nList.item(temp);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				
				//Store each element key value in map
				stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
			}
		}
		return stringMap;
	}
	
	public String dateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public void log(String txt) {
		String msg = Thread.currentThread().getId() + Thread.currentThread().getStackTrace()[2].getClassName() + ":" + txt;
		
		System.out.println(msg);
		
		String strFile = "logsFile"; 

		File logFile = new File(strFile);

		if (!logFile.exists()) {
			logFile.mkdirs();
		}
		
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(logFile + File.separator + "log.txt",true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.println(msg);
	    printWriter.close();
	}
	
	public Logger log() {
		return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
				
	}
	
	public static Iterator<String[]> getDataFromCSV(String csvFilePath) throws IOException{
		String Path = System.getProperty("user.dir") + csvFilePath;
		CSVReadUtil csv = new CSVReadUtil(Path);
		List<String[]> list = csv.getEntriesAsList();

		for (int i = 1; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).length; j++) {
				if (list.get(i)[j].equals("null"))
					list.get(i)[j] = null;
			}
		}
		Iterator<String[]> itr = list.iterator();
		return itr;
	}
	
}
