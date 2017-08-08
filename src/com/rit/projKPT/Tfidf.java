package com.rit.projKPT;

/**
 * 
 */
/**
 * @author srini
 *
 */



public class Tfidf {
	private double score;
	
	/***
	 * 
	 * @param wordFreq    
	 * @param totalWordcnt   
	 * @param N
	 * @param n
	 * @return score 
	 */
	public double computeTFIDF(int wordFreq, int totalWordcnt, int N, int n){
		double termFreq1 = (double)wordFreq/(double)totalWordcnt;
		double idf = Math.log(N/n);
		
		score = termFreq1 * idf;
		return score;
	}
}