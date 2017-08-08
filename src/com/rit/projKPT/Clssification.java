package com.rit.projKPT;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Clssification {
	
	

	public static void main(String[] args) {
		
		List<File> Input = new ArrayList<>();
		List<File> fileList = new ArrayList<>();
		Stemmer st = new Stemmer();
		
		StopWords stp = new StopWords();
		List<String> stpwrds = StopWords.list;
		int docs_Number;
		final File folder = new File("C:/Users/srini/Desktop/KPT/news/ClassificationTest/sportsTest");
		FileRead fread = new FileRead();
		File file = new File("C:/Users/srini/Desktop/KPT/output.txt");
		File file1 = new File("C:/Users/srini/Desktop/KPT/news/stopstem/stopstementsport.txt");
		
		Input = fread.listFilesForFolder(folder, fileList);
		try {
			FileOutputStream output = new FileOutputStream(file);
		
		FileOutputStream out1 = new FileOutputStream(file1);
		PrintWriter pWriter1 = new PrintWriter(out1);
		BufferedWriter writer1 = new BufferedWriter(pWriter1);
		
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

					boolean b = stp.StopWord(stpwrds, word);
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
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
}
