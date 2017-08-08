package com.rit.projKPT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Likelihood {

	public static void main(String[] args) throws IOException {


		HashMap<String, Integer> probMap = new HashMap<>();

		File folder = new File("C:/Users/srini/Desktop/KPT/news/trainingSet");
		File file1 = new File("C:/Users/srini/Desktop/KPT/nboutput.txt");
		//File[] listOfFiles = folder.listFiles();

		//File f = new File("C:/Users/srini/Desktop/KPT/news/stopstem/stopstementpolit.txt");
		BufferedReader inFile = null;
		//inFile = new BufferedReader(new FileReader(f));

		String line = "";
		StringTokenizer tokens;


		int wordcount = 0;
		Double likelihood = 0.0;
		Double sumlhood = 0.0;
		Double classprob = (double)408/1457;
		Double cmap = 0.0;
		int i = 1;
		int v = 1;
		int val = 0;
		int maxv = 0;
		int uniqueIndex=0;
		
		List<HashMap<String, HashMap<Integer, Integer>>> list = new ArrayList<>();
		ArrayList<Integer> maxc = new ArrayList<>();

		try{

			for(File file : folder.listFiles()){
				System.out.println(file.getName());
				HashMap<String, HashMap<Integer, Integer>> words = new HashMap<>();
				inFile = new BufferedReader(new FileReader(file));

				while ((line = inFile.readLine()) != null) {
					//System.out.println(line);
					tokens = new StringTokenizer(line);

					while (tokens.hasMoreTokens()){

						String nword = tokens.nextToken();
						if(!words.containsKey(nword)){
							HashMap<Integer, Integer> map = new HashMap<>();
							map.put(i,v);
							words.put(nword, map);
						}
						else{

							HashMap<Integer,Integer> eh = words.get(nword);
							//System.out.println(eh.get(i) +" : "+ i);
							val = eh.get(i);
							eh.put(i, val+1);
							//eh.put(nword, map);

						}
						if(!probMap.containsKey(nword)){
							
							probMap.put(nword, uniqueIndex);
							++uniqueIndex;
							
						}
						maxc.add(wordcount);
						++wordcount;
					}

				}
				list.add(words);
				i++;
			}
			
			maxv = Collections.max(maxc);
			System.out.println(list.get(1));
			double arr[][] = new double[i][uniqueIndex];
			
		for (int j = 0; j < list.size(); j++) {
			System.out.println(j);
			for (Entry<String, HashMap<Integer, Integer>> e : list.get(j).entrySet()) {

				for(Entry<Integer, Integer> in : e.getValue().entrySet()){
					
					int m = probMap.get(e.getKey());

					System.out.println(e.getKey());
					likelihood = Math.abs(((double)in.getValue()+1)/(wordcount+uniqueIndex));
					//likelihood = Math.abs(Math.log((double)e.getValue()/wordcount));
					arr[j][m] = likelihood;
					//sumlhood += likelihood;
					//probMap.put(e.getKey(), likelihood);
					//System.out.println(arr);

				}

			}

			
		}
			
		FileOutputStream out = new FileOutputStream(file1);
		PrintWriter pWriter = new PrintWriter(out);
		BufferedWriter writer = new BufferedWriter(pWriter);
		
		for(int l = 0; l < arr.length; l++){
			
			String row = "";
			for(int k=0; k< arr[0].length; k++ ){
				
				row+="" + arr[l][k] +",";
			}
			pWriter.println(row.substring(0, row.length()-1));
			
		}
		
		writer.close();


			//System.out.println(wordcount);
			//System.out.println(classprob);
			cmap = classprob + sumlhood;

			//System.out.println(probMap);
			//System.out.println("The maximum a posteriori is: "+cmap+"");
		}

		finally{

			inFile.close();
		}


	}
}
