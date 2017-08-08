package com.rit.projKPT;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Bigrams {

	Hashtable table;
	String bigram;
	String[] bigramStrings;
	int[] bigramCounts;
	BufferedReader inFile;
	static HashMap<String, Integer> uniqueIndex = new HashMap<>();
	// Enumeration elements, keys;
	// BigramsCounts[] fullResults;

	Map<String,Integer> collectStats(File f) {

		BufferedReader inFile = null;
		try {

			inFile = new BufferedReader(new FileReader(f));
		} catch (Exception e) {
			System.out.println("error");
		}

		HashMap<String,Integer> table = new HashMap<>();
		String line = "";
		StringTokenizer tokens;
		String token1 = "";
		String token2 = "";
		// cena playing cricket rit
		// cena playing
		
		try {
//			line = inFile.readLine();
			int i =0;
			int index = 0;
			while ((line = inFile.readLine()) != null) {
				tokens = new StringTokenizer(line);
				if (tokens.hasMoreTokens()) {
					token1 = tokens.nextToken();
				}
				while (tokens.hasMoreTokens()) {
					token2 = tokens.nextToken();
					bigram = token1 + " " + token2;
					if(table.containsKey(bigram)){
						int val = table.get(bigram);
						table.put(bigram+","+i, val + 1);
						
					}else{
						table.put(bigram + "," + i, 1);
					}
					
					if(!uniqueIndex.containsKey(bigram)){
						uniqueIndex.put(bigram, index++);
					}
					System.out.println(bigram);

					token1 = token2; // step forward
					// return bigram;
				}
				++i;
				// next
			}
			return table;
		}

		catch (Exception e) {
		}

		System.out.println("Number of bigrams is: " + table.size());
		return null;

	}

}
