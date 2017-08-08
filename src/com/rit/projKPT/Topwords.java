package com.rit.projKPT;
/**
 * 
 */
/**
 * @author srini
 *
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Topwords {

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


	private static Map<String, HashSet<Integer>> map; 

	public static void main(String[] args) throws IOException {

		List<File> fileList =new ArrayList<>();
		List<File> Input = new ArrayList<>();
		final File folder = new File("C:/Users/srini/Desktop/KPT/news/trainingSet");
		map = new HashMap<>(); // Initializing the Hashmap created
		Map<String, MapFreq> OMap = new HashMap<String, MapFreq>();
		FileRead fread = new FileRead();
		Input = fread.listFilesForFolder(folder, fileList);
		double tfidf = 0.0;
		List<String> stpwrds = StopWords.list;
		File file = new File("C:/Users/srini/Desktop/KPT/tfidfenter.txt");
		FileOutputStream output = new FileOutputStream(file);
		HashMap<String, Integer> words = new HashMap<>();
		int docs_Number = 0;
		HashMap<Integer,Integer> docTermCounts = new HashMap<>();
		//HashMap<Double, HashSet<Integer>> tfmap = new HashMap<>();
		ArrayList<Double> tfmax = new ArrayList<>();
		int uniqueIndex=0;

		Stemmer st = new Stemmer();

		HashMap<String, Double> cmap = new HashMap<>();



		for (int i = 0; i < Input.size(); i++) {

			FileInputStream in = new FileInputStream(Input.get(i)); 
			docs_Number = i;
			String word = ""; 								   
			int ch;
			int totalWordCount=0;

			while ((ch = in.read()) != -1) {			

				ch = Character.toLowerCase((char) ch);
				if (Character.isLetter((char) ch)) {		   

					word += (char) ch;					       
				} 
				else if ((char) ch == ' ') {		

//					boolean b = StopWord(stpwrds, word);
//					if (b) {
//
//						word = "";							   
//						continue;
//					}	    
//
//					String u = st.StemmerCall(word, st);
//					if (u.length() == 0)					   
//						continue;
					//
					//
					//					else if ((char) ch == ' ') {		

					if (!map.containsKey(word)) {	

						HashSet<Integer> nos = new HashSet<>(); 
						MapFreq mp = new MapFreq(nos);
						mp.Frequency++;
						mp.tw.add(i);
						OMap.put(word, mp);

						if(mp.termFreq.containsKey(i)){

							int val = mp.termFreq.get(i);
							mp.termFreq.put(i, val+1);

						}else{

							mp.termFreq.put(i, 1);
						}

						map.put(word, nos);						

					} else {

						HashSet<Integer> nos = map.get(word);		
						MapFreq mp = OMap.get(word);
						mp.tw.add(i);
						mp.Frequency++;
						if(mp.termFreq.containsKey(i)){

							int val = mp.termFreq.get(i);
							mp.termFreq.put(i, val+1);

						}else{

							mp.termFreq.put(i, 1);
						}
						OMap.put(word, mp);								
						map.put(word, nos);						

					}
					if(!words.containsKey(word)){

						words.put(word, uniqueIndex);
						++uniqueIndex;

					}

					totalWordCount++;
					word = "";								

				} 
				else {
					word = "";									
				}


			}
			docTermCounts.put(i, totalWordCount);
		}


	//System.out.println("After all :\n");
	//System.out.println(uniqueIndex +" " + docs_Number);
	double arr[][] = new double[docs_Number+1][uniqueIndex];
	Tfidf tf1 = new Tfidf();

	class Maxval implements Comparable<Maxval>{

		double tfidf;
		String word;
		public Maxval(double tfidf, String word) {
			super();
			this.tfidf = tfidf;
			this.word = word;
		}
		@Override
		public int compareTo(Maxval other) {

			if(this.tfidf > other.tfidf){
				return 1;
			}
			else if(this.tfidf < other.tfidf){
				return -1;
			}
			else
				return 0;
		}

		@Override
		public String toString() {
			return tfidf+ " " +word;
		}


	}

	List<Maxval> mv = new ArrayList<>();

	for(Entry<String, MapFreq> e: OMap.entrySet()){

		Map<Integer,Integer> tf = e.getValue().termFreq;

		for(Entry<Integer,Integer> e1 : tf.entrySet()){
								
								System.out.println(e.getKey());
			//					System.out.println(e.getValue().toString());
			//					System.out.println(e1.getKey());
			//					System.out.println(e1.getValue());
			//					System.out.println(docTermCounts.get(e1.getKey()));
			//					System.out.println(docs_Number);
			//					System.out.println(e.getValue().tw.size());

			tfidf = tf1.computeTFIDF(e1.getValue(), docTermCounts.get(e1.getKey()), docs_Number, e.getValue().tw.size());
			//cmap.put(e.getKey(), tfidf);
			//
			System.out.println(tfidf+ " "+ e.getKey());
			mv.add(new Maxval(tfidf,e.getKey()));
			//tfmax.add(tfidf);
			//					int l = e1.getKey();
			//					int m = words.get(e.getKey());
			//					arr[l][m] = tfidf;

			//System.out.println(e.getKey());

		}

	}

	Collections.sort(mv);
	List<Maxval> top = new ArrayList<Maxval>(mv.subList(mv.size() -100, mv.size()));


	System.out.println(top);
	System.out.println(top.size());

	//			FileOutputStream out = new FileOutputStream(file);
	//			PrintWriter pWriter = new PrintWriter(out);
	//			BufferedWriter writer = new BufferedWriter(pWriter);
	//
	//			for(int l = 0; l <arr.length; l++){
	//
	//				String row = "";
	//				for(int k=0; k< arr[0].length; k++ ){
	//
	//					row += "" + arr[l][k] +",";
	//				}
	//				pWriter.println(row.substring(0, row.length()-1));
	//
	//			}
	//
	//			writer.close();
}

}