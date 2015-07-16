package com.mtsmda.tools.fileSystem;

public class ClassExForFindClassWhereReside {
	
	public static void main(String[] args) {
		System.out.println(ClassExForFindClassWhereReside.class.getResource("").getFile());
	}
	
}