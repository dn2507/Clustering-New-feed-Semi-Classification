package com.rit.projKPT;

import java.util.ArrayList;

public class CosineSimilarity {
	
	//private double similarity;
    double dotProduct = 0.0;
    double normA = 0.0;
    double normB = 0.0;
	
	public double computeSimilarity(ArrayList<Double> vectorA, ArrayList<Double> vectorB){
		
		for (int i = 0; i < vectorA.size(); i++) {
	        dotProduct += vectorA.get(i) * vectorB.get(i);
	        normA += Math.pow(vectorA.get(i), 2);
	        normB += Math.pow(vectorB.get(i), 2);
	    }   
		
		return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
	}
	

}
