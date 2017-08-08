/**
 * 
 */
/**
 * @author srini
 *
 */
package com.rit.projKPT;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class ProjStem {

	// Method for handling stop words

	private static boolean StopWord(List<String> stpwrds, String u) {
		boolean b = false;
		for (String stp : stpwrds) {
			if (stp.equals(u)) {
				b = true;
			}
		}

		return b;

	}

	private static Map<String, Integer> map;

	public static void main(String[] args) throws IOException {

		Stemmer st = new Stemmer();
		Bigrams bg = new Bigrams();
		
		List<File> fileList = new ArrayList<>();
		List<File> Input = new ArrayList<>();
		
		final File folder = new File("C:/Users/srini/Desktop/KPT/news/ClassificationTest/businessTest");
		
		map = new HashMap<>(); // Initializing the Hashmap created
		Map<String, MapFreq> OMap = new HashMap<String, MapFreq>();
		
		FileRead fread = new FileRead();
		Input = fread.listFilesForFolder(folder, fileList);
		
		double tfidf = 0.0;
		List<String> stpwrds = StopWords.list;
		
		File file = new File("C:/Users/srini/Desktop/KPT/output1.txt");
		File file1 = new File("C:/Users/srini/Desktop/KPT/news/ClassificationTest/stopstem.txt");
		
		FileOutputStream output = new FileOutputStream(file);
		
		HashMap<String, Integer> words = new HashMap<>();
		int docs_Number = 0;
		HashMap<Integer, Integer> docTermCounts = new HashMap<>();
		int uniqueIndex = 0;
		HashMap<String, Integer> limitedWords = new HashMap<>();
		int bigramFrequency = 1;

		FileOutputStream out1 = new FileOutputStream(file1);
		PrintWriter pWriter1 = new PrintWriter(out1);
		BufferedWriter writer1 = new BufferedWriter(pWriter1);

		List<String> h1 = new ArrayList<>(Arrays.asList("portfolio",
				"takeover", "purchase", "acquisition", "employement",
				"conference", "exchange", "empowerment", "dollar", "currency",
				"revenue", "tax"));
		List<String> h2 = new ArrayList<>(Arrays.asList("theater", "comedy",
				"documentary", "genres", "film"));
		List<String> h3 = new ArrayList<>(Arrays.asList("democrats",
				"election", "budget", "chancellor", "laws", "commission",
				"govern", "war", "labor"));
		List<String> h4 = new ArrayList<>(Arrays.asList("olympic", "champions",
				"medal", "trophy", "injury", "chelsea", "defender", "soccer",
				"football", "strike"));
		int idx = 0;
		for (String s : h1) {
			limitedWords.put(s, idx++);
		}

		for (String s : h2) {
			limitedWords.put(s, idx++);
		}

		for (String s : h3) {
			limitedWords.put(s, idx++);
		}
		for (String s : h4) {
			limitedWords.put(s, idx++);
		}

		for (int i = 0; i < Input.size(); i++) {

			FileInputStream in = new FileInputStream(Input.get(i));
			docs_Number = i;
			String word = "";
			int ch;

			while ((ch = in.read()) != -1) {

				ch = Character.toLowerCase((char) ch);
				if (Character.isLetter((char) ch)) {

					word += (char) ch;
				} else if ((char) ch == ' ') {

					boolean b = StopWord(stpwrds, word);
					if (b) {

						word = "";
						continue;
					}
					String u = st.StemmerCall(word, st);

					if (u.length() == 0)
						continue;

					pWriter1.printf(u + " ");
					word = "";
					//
				} else {
					word = "";
				}

			}
			pWriter1.println();
			// docTermCounts.put(i, totalWordCount);
		}

		Map<String, Integer> bgu = bg.collectStats(file1);
		Map<String, Integer> uniqIndex = Bigrams.uniqueIndex;
		long[][] arr = new long[docs_Number + 1][uniqIndex.size()];

		for (Entry<String, Integer> e : bgu.entrySet()) {
			String s = e.getKey();
			String split[] = s.split(",");
			int frq = e.getValue();
			if (uniqIndex.containsKey(split[0])) {
				int index = uniqIndex.get(split[0]);
				arr[Integer.parseInt(split[1])][index] = frq;
			}
		}

		FileOutputStream out = new FileOutputStream(file);
		PrintWriter pWriter = new PrintWriter(out);
		BufferedWriter writer = new BufferedWriter(pWriter);

		for (int l = 0; l < arr.length; l++) {

			String row = "";
			for (int k = 0; k < arr[0].length; k++) {
				
				row += "" + arr[l][k] + ",";
			}
			 System.out.println(row);
			pWriter.println(row.substring(0, row.length() -1));

		}

		writer.close();

	}
}
