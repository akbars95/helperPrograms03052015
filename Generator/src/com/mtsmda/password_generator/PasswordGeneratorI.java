package com.mtsmda.password_generator;

public interface PasswordGeneratorI {

	public static final Integer ONLY_UPPER_CASE_LETTERS_EN = 0;
	public static final Integer ONLY_LOWER_CASE_LETTERS_EN = 1;
	public static final Integer LOWER_AND_UPPER_CASE_LETTERS_EN = 2;
	public static final Integer ONLY_NUMBERS = 3;
	public static final Integer NUMBERS_AND_LOWER_EN = 4;
	public static final Integer NUMBERS_AND_UPPER_EN = 5;
	public static final Integer NUMBERS_AND_UPPER_LOWER_EN = 6;

	public static final Integer ONLY_UPPER_CASE_LETTERS_RU = 8;
	public static final Integer ONLY_LOWER_CASE_LETTERS_RU = 9;
	public static final Integer LOWER_AND_UPPER_CASE_LETTERS_RU = 10;

	public static final Integer NUMBERS_AND_LOWER_RU = 11;
	public static final Integer NUMBERS_AND_UPPER_RU = 12;
	public static final Integer NUMBERS_AND_UPPER_LOWER_RU = 13;

	public static final PassGenInterval ONLY_UPPER_CASE_LETTERS_EN_PGI = new PassGenInterval(
			65, 90);
	public static final PassGenInterval ONLY_LOWER_CASE_LETTERS_EN_PGI = new PassGenInterval(
			97, 122);
	public static final PassGenInterval ONLY_NUMBERS_PGI = new PassGenInterval(
			48, 57);
	public static final PassGenInterval ONLY_UPPER_CASE_LETTERS_RU_PGI = new PassGenInterval(
			1040, 1071);
	public static final PassGenInterval ONLY_LOWER_CASE_LETTERS_RU_PGI = new PassGenInterval(
			1072, 1103);

	public String passwordGeneratorCore(int countLetter, Integer restriction);

	public ConditionReturnType condition(Integer restriction);

}