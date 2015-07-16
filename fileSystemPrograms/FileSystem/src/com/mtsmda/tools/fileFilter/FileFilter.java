package com.mtsmda.tools.fileFilter;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class FileFilter implements FilenameFilter{
	
	private String extention;
	
	private void setExtention(String extention){
		if(extention != null && !extention.isEmpty()){
			if(extention.startsWith(".")){
				this.extention = extention;
			}
			else{
				this.extention = ".txt";
			}
		}
		else{
			this.extention = ".txt";
		}
	}
	
	private FileFilter(String extention) {
		this.setExtention(extention);
	}
	
	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(this.extention);
	}
	
	public static File[] getFiles(String extention, String dir) throws IOException{
		String listFiles[] = null;
		File returnListFiles[] = null;
		FileFilter filterFiles = new FileFilter(extention);
		String directory = dir;
		if(directory == null || directory.isEmpty()){
			return null;
		}
		File file = new File(directory);
		if(file.isDirectory()){
			listFiles = file.list(filterFiles);
			if(listFiles.length == 0){
				return null;
			}
			returnListFiles = new File[listFiles.length];
				for(int i = 0; i < listFiles.length; i++){
					File eachFile = new File(directory, listFiles[i]);
					returnListFiles[i] = eachFile;
				}
		}
		else{
			return null;
		}
		return returnListFiles;
	}
	
	public static void main(String[] args) throws Exception{

		File[] files = FileFilter.getFiles(".pdf", "c:\\Users\\c-DMITMINZ\\Downloads\\documents-export-2015-07-15\\");
		if(files != null){
			for(int i = 0;i < files.length; i++){
				File localFile = files[i];
				System.out.println(localFile.getCanonicalPath());
			}
		}
		else{
			System.out.println("Not found!");
		}
	}
	
}
