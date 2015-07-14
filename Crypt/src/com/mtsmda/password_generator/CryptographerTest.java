package com.mtsmda.password_generator;

public class CryptographerTest {
	
	public static void main(String[] args) {
		Tester.test(CryptographerTest.class);
	}
	
	private void random(){
		StringBuilder inBegin = new StringBuilder("A$k^B2a@R1S");
		StringBuilder in = new StringBuilder("A$k^B2a@R1S");
		StringBuilder out = null;

		Cryptographer c = new Cryptographer();
		CryptUser randomCrypt = c.randomCrypt(in, Cryptographer.METHOD_CODE);
		out = randomCrypt.getOutput();
		System.out.println(in);
		System.out.println(out);
		System.out.println("-----");
		for (int i = 0; i < out.length(); i++) {
			System.out.println("#" + i + " = " + out.charAt(i));
		}
		System.out.println("-----");
		randomCrypt = c.randomCrypt(new StringBuilder(out),
				Cryptographer.METHOD_DECODE);
		out = randomCrypt.getOutput();
		System.out.println(out);
		System.out.println(inBegin);
		System.out.println(inBegin.toString().equals(out.toString()));
	}
	
	private void defaultM(){
		StringBuilder in = new StringBuilder("A$k^B2a@R1S");
		StringBuilder out = null;

		Cryptographer c = new Cryptographer();
		CryptUser defaultCrypt = c.defaultCrypt(in, Cryptographer.METHOD_CODE);
		out = defaultCrypt.getOutput();
		System.out.println(in);
		System.out.println(out);
		System.out.println("-----");
		for (int i = 0; i < out.length(); i++) {
			System.out.println("#" + i + " = " + out.charAt(i));
		}
		System.out.println("-----");
		defaultCrypt = c.defaultCrypt(new StringBuilder(out),
				Cryptographer.METHOD_DECODE);
		out = defaultCrypt.getOutput();
		System.out.println(out);
		System.out.println(in);
		System.out.println(in.toString().equals(out.toString()));
	}
	
}