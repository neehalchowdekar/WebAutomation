package com.lenskart.TestNgPractice;

import org.testng.annotations.Test;

//import io.github.sskorol.core.DataSupplier;
//import io.github.sskorol.data.CsvReader;
//import static io.github.sskorol.data.TestDataReader.use;
//import one.util.streamex.StreamEx;

public class DataSupplierTest {
	
	@Test(dataProvider = "getData")
	public void testdatasupplier(LensData lensData) {
		System.out.println(lensData.getSearchText() + " -> " + lensData.getLensName() + " -> " + lensData.getPowerName());
	}
	
//	@DataSupplier
//	public StreamEx<LensData> getData(){
//		return DataSupplierTest.getAnyData(LensData.class, "lensOrderData.csv");
//		//return use(CsvReader.class).withTarget(LensData.class).withSource("lensOrderData.csv").read();
//	}
//	
//	
//	public static <T> StreamEx<T> getAnyData(final Class<T> dataReaderClass, String csvPath){
//		return use(CsvReader.class).withTarget(dataReaderClass).withSource(csvPath).read();
//	}

}
