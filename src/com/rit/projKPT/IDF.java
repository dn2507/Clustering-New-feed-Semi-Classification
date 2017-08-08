package com.rit.projKPT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class IDF {

	public static void main(String[] args) throws IOException {

		HashMap<String, Integer> words = new HashMap<>();

		File f = new File("C:/Users/srini/Desktop/KPT/news/stopstem/stopstementpolit.txt");

		BufferedReader inFile = null;

		inFile = new BufferedReader(new FileReader(f));
		String line = "";
		StringTokenizer tokens;

		int wordcount = 0;
		Double likelihood = 0.0;


		try{

			while ((line = inFile.readLine()) != null) {

				tokens = new StringTokenizer(line);

				while (tokens.hasMoreTokens()){	

					String nword = tokens.nextToken();
					
					if(!words.containsKey(nword)){
						
						
					}
					
				}
				
			}
			
		}
		
		finally{
			
			inFile.close();
		}
		
	}
}
