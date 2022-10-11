package com.lenskart.TestNgPractice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LensData {
	
	private String searchText;
	private String lensName;
	private String powerName;

}
