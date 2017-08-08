package com.rit.projKPT;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileRead {

//	public static void main(String[] args) {
//
//		List<File> op = new ArrayList<File>();
//	    List<File> fileList =new ArrayList<>();
//	     final File folder = new File("C:/Users/srini/Desktop/KPT/news");
//	     FileRead fr = new FileRead();
//	     op = fr.listFilesForFolder(folder, fileList);
	
//	     System.out.println(op);
//	     
//	}

	    public List<File> listFilesForFolder(final File folder,List<File> fileList) {
	        File[] filesInFolder = folder.listFiles();
	        if (filesInFolder != null) {
	            for (final File fileEntry : filesInFolder) {
	                if (fileEntry.isDirectory()) {
	                    System.out.println("DIR : "+fileEntry.getName());
	                listFilesForFolder(fileEntry,fileList);
	            } else {
	                //System.out.println("FILE : "+fileEntry.getName());
	                fileList.add(fileEntry);
	                //System.out.println(fileList);
	                //return fileList;
	            }
	         }
	        }
			return fileList;
	        
	     }

}
