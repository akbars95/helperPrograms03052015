package com.mtsmda.password_generator;

public class PasswordGeneratorTest {

	public static void main(String[] args) {
		Tester.test(PasswordGeneratorTest.class);
	}
	
//	private static void UPPER_CASE_LETTERS_EN_AND_SPECIAL_CHARACTERS() {
//		System.out.println(new PasswordGeneratorOtherImpl().passwordGeneratorCore(9,
//				PasswordGeneratorOtherImpl.UPPER_CASE_LETTERS_EN_AND_SPECIAL_CHARACTERS));
//	}
//	
//	private static void LOWER_CASE_LETTERS_EN_AND_SPECIAL_CHARACTERS() {
//		System.out.println(new PasswordGeneratorOtherImpl().passwordGeneratorCore(9,
//				PasswordGeneratorOtherImpl.LOWER_CASE_LETTERS_EN_AND_SPECIAL_CHARACTERS));
//	}
//	
//	private static void LOWER_AND_UPPER_CASE_LETTERS_EN_AND_SPECIAL_CHARACTERS() {
//		System.out.println(new PasswordGeneratorOtherImpl().passwordGeneratorCore(9,
//				PasswordGeneratorOtherImpl.LOWER_AND_UPPER_CASE_LETTERS_EN_AND_SPECIAL_CHARACTERS));
//	}
//	
//	private static void NUMBERS_AND_SPECIAL_CHARACTERS() {
//		System.out.println(new PasswordGeneratorOtherImpl().passwordGeneratorCore(9,
//				PasswordGeneratorOtherImpl.NUMBERS_AND_SPECIAL_CHARACTERS));
//	}
//	
//	private static void NUMBERS_AND_LOWER_EN_AND_SPECIAL_CHARACTERS() {
//		System.out.println(new PasswordGeneratorOtherImpl().passwordGeneratorCore(9,
//				PasswordGeneratorOtherImpl.NUMBERS_AND_LOWER_EN_AND_SPECIAL_CHARACTERS));
//	}
//	
//	private static void NUMBERS_AND_UPPER_EN_AND_SPECIAL_CHARACTERS() {
//		System.out.println(new PasswordGeneratorOtherImpl().passwordGeneratorCore(9,
//				PasswordGeneratorOtherImpl.NUMBERS_AND_UPPER_EN_AND_SPECIAL_CHARACTERS));
//	}
//	
	private static void NUMBERS_AND_UPPER_LOWER_EN_SPECIAL_CHARACTERS() {
		System.out.println(new PasswordGeneratorOtherImpl().passwordGeneratorCore(9,
				PasswordGeneratorOtherImpl.NUMBERS_AND_UPPER_LOWER_EN_SPECIAL_CHARACTERS));
	}
	
	private static void UPPER_CASE_LETTERS_RU_SPECIAL_CHARACTERS() {
		System.out.println(new PasswordGeneratorOtherImpl().passwordGeneratorCore(9,
				PasswordGeneratorOtherImpl.UPPER_CASE_LETTERS_RU_SPECIAL_CHARACTERS));
	}
	
	private static void LOWER_CASE_LETTERS_RU_SPECIAL_CHARACTERS() {
		System.out.println(new PasswordGeneratorOtherImpl().passwordGeneratorCore(9,
				PasswordGeneratorOtherImpl.LOWER_CASE_LETTERS_RU_SPECIAL_CHARACTERS));
	}
	
	private static void LOWER_AND_UPPER_CASE_LETTERS_RU_SPECIAL_CHARACTERS() {
		System.out.println(new PasswordGeneratorOtherImpl().passwordGeneratorCore(9,
				PasswordGeneratorOtherImpl.LOWER_AND_UPPER_CASE_LETTERS_RU_SPECIAL_CHARACTERS));
	}
	
	private static void NUMBERS_AND_LOWER_RU_SPECIAL_CHARACTERS() {
		System.out.println(new PasswordGeneratorOtherImpl().passwordGeneratorCore(9,
				PasswordGeneratorOtherImpl.NUMBERS_AND_LOWER_RU_SPECIAL_CHARACTERS));
	}
	
	private static void NUMBERS_AND_UPPER_RU_SPECIAL_CHARACTERS() {
		System.out.println(new PasswordGeneratorOtherImpl().passwordGeneratorCore(9,
				PasswordGeneratorOtherImpl.NUMBERS_AND_UPPER_RU_SPECIAL_CHARACTERS));
	}
	
	private static void NUMBERS_AND_UPPER_LOWER_RU_SPECIAL_CHARACTERS() {
		System.out.println(new PasswordGeneratorOtherImpl().passwordGeneratorCore(25,
				PasswordGeneratorOtherImpl.NUMBERS_AND_UPPER_LOWER_RU_SPECIAL_CHARACTERS));
	}
	

	private static void onlyUpperCaseLettersEN() {
		System.out.println(new PasswordGenerator().passwordGeneratorCore(9,
				PasswordGenerator.ONLY_UPPER_CASE_LETTERS_EN));
	}

	private static void onlyLowerCaseLettersEN() {
		System.out.println(new PasswordGenerator().passwordGeneratorCore(9,
				PasswordGenerator.ONLY_LOWER_CASE_LETTERS_EN));
	}

	private static void lowerAndUpperCaseLettersEN() {
		System.out.println(new PasswordGenerator().passwordGeneratorCore(9,
				PasswordGenerator.LOWER_AND_UPPER_CASE_LETTERS_EN));
	}

	private static void onlyNumbers() {
		System.out.println(new PasswordGenerator().passwordGeneratorCore(9,
				PasswordGenerator.ONLY_NUMBERS));
	}

	private static void numbersAndLowerEN() {
		System.out.println(new PasswordGenerator().passwordGeneratorCore(9,
				PasswordGenerator.NUMBERS_AND_LOWER_EN));
	}

	private static void numbersAndUpperEN() {
		System.out.println(new PasswordGenerator().passwordGeneratorCore(9,
				PasswordGenerator.NUMBERS_AND_UPPER_EN));
	}

	private static void numbersAndUpperLowerEN() {
		System.out.println(new PasswordGenerator().passwordGeneratorCore(9,
				PasswordGenerator.NUMBERS_AND_UPPER_LOWER_EN));
	}

	private static void ONLY_UPPER_CASE_LETTERS_RU() {
		System.out.println(new PasswordGenerator().passwordGeneratorCore(9,
				PasswordGenerator.ONLY_UPPER_CASE_LETTERS_RU));
	}

	private static void ONLY_LOWER_CASE_LETTERS_RU() {
		System.out.println(new PasswordGenerator().passwordGeneratorCore(9,
				PasswordGenerator.ONLY_LOWER_CASE_LETTERS_RU));
	}

	private static void LOWER_AND_UPPER_CASE_LETTERS_RU() {
		System.out.println(new PasswordGenerator().passwordGeneratorCore(9,
				PasswordGenerator.LOWER_AND_UPPER_CASE_LETTERS_RU));
	}

	private static void NUMBERS_AND_LOWER_RU() {
		System.out.println(new PasswordGenerator().passwordGeneratorCore(9,
				PasswordGenerator.NUMBERS_AND_LOWER_RU));
	}

	private static void NUMBERS_AND_UPPER_RU() {
		System.out.println(new PasswordGenerator().passwordGeneratorCore(9,
				PasswordGenerator.NUMBERS_AND_UPPER_RU));
	}

	private static void NUMBERS_AND_UPPER_LOWER_RU() {
		System.out.println(new PasswordGenerator().passwordGeneratorCore(9,
				PasswordGenerator.NUMBERS_AND_UPPER_LOWER_RU));
	}

}