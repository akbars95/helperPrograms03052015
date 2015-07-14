package com.mtsmda.password_generator;

import java.util.ArrayList;
import java.util.List;

public class PasswordGenerator implements PasswordGeneratorI{

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
	
	//@#$%^&+=!.

	private static final PassGenInterval ONLY_UPPER_CASE_LETTERS_EN_PGI = new PassGenInterval(
			65, 90);
	private static final PassGenInterval ONLY_LOWER_CASE_LETTERS_EN_PGI = new PassGenInterval(
			97, 122);
	private static final PassGenInterval ONLY_NUMBERS_PGI = new PassGenInterval(
			48, 57);
	private static final PassGenInterval ONLY_UPPER_CASE_LETTERS_RU_PGI = new PassGenInterval(
			1040, 1071);
	private static final PassGenInterval ONLY_LOWER_CASE_LETTERS_RU_PGI = new PassGenInterval(
			1072, 1103);

	public String passwordGeneratorCore(int countLetter, Integer restriction) {
		StringBuilder outPassword = new StringBuilder();

		if (countLetter <= 0) {
			countLetter = 10;
		}

		for (int i = 0; i < countLetter;) {
			ConditionReturnType condition = condition(restriction);
			if (condition.isbType()) {
				outPassword.append((char) condition.getiType());
				i++;
			}
		}
		return outPassword.toString();
	}

	public ConditionReturnType condition(Integer restriction) {
		int topBorder = 0;
		ConditionReturnType conditionReturnType = new ConditionReturnType();
		List<PassGenInterval> passGenIntervalList = new ArrayList<PassGenInterval>();

		if (restriction.equals(ONLY_UPPER_CASE_LETTERS_EN)) {
			passGenIntervalList.add(ONLY_UPPER_CASE_LETTERS_EN_PGI);
		} else if (restriction.equals(ONLY_LOWER_CASE_LETTERS_EN)) {
			passGenIntervalList.add(ONLY_LOWER_CASE_LETTERS_EN_PGI);
		} else if (restriction.equals(LOWER_AND_UPPER_CASE_LETTERS_EN)) {
			passGenIntervalList.add(ONLY_UPPER_CASE_LETTERS_EN_PGI);
			passGenIntervalList.add(ONLY_LOWER_CASE_LETTERS_EN_PGI);
		} else if (restriction.equals(ONLY_NUMBERS)) {
			passGenIntervalList.add(ONLY_NUMBERS_PGI);
		} else if (restriction.equals(NUMBERS_AND_LOWER_EN)) {
			passGenIntervalList.add(ONLY_NUMBERS_PGI);
			passGenIntervalList.add(ONLY_LOWER_CASE_LETTERS_EN_PGI);
		} else if (restriction.equals(NUMBERS_AND_UPPER_EN)) {
			passGenIntervalList.add(ONLY_NUMBERS_PGI);
			passGenIntervalList.add(ONLY_UPPER_CASE_LETTERS_EN_PGI);
		} else if (restriction.equals(NUMBERS_AND_UPPER_LOWER_EN)) {
			passGenIntervalList.add(ONLY_NUMBERS_PGI);
			passGenIntervalList.add(ONLY_UPPER_CASE_LETTERS_EN_PGI);
			passGenIntervalList.add(ONLY_LOWER_CASE_LETTERS_EN_PGI);
		} else if (restriction.equals(ONLY_UPPER_CASE_LETTERS_RU)) {
			passGenIntervalList.add(ONLY_UPPER_CASE_LETTERS_RU_PGI);
		} else if (restriction.equals(ONLY_LOWER_CASE_LETTERS_RU)) {
			passGenIntervalList.add(ONLY_LOWER_CASE_LETTERS_RU_PGI);
		} else if (restriction.equals(LOWER_AND_UPPER_CASE_LETTERS_RU)) {
			passGenIntervalList.add(ONLY_UPPER_CASE_LETTERS_RU_PGI);
			passGenIntervalList.add(ONLY_LOWER_CASE_LETTERS_RU_PGI);
		} else if (restriction.equals(NUMBERS_AND_LOWER_RU)) {
			passGenIntervalList.add(ONLY_NUMBERS_PGI);
			passGenIntervalList.add(ONLY_LOWER_CASE_LETTERS_RU_PGI);
		} else if (restriction.equals(NUMBERS_AND_UPPER_RU)) {
			passGenIntervalList.add(ONLY_NUMBERS_PGI);
			passGenIntervalList.add(ONLY_UPPER_CASE_LETTERS_RU_PGI);
		} else if (restriction.equals(NUMBERS_AND_UPPER_LOWER_RU)) {
			passGenIntervalList.add(ONLY_NUMBERS_PGI);
			passGenIntervalList.add(ONLY_UPPER_CASE_LETTERS_RU_PGI);
			passGenIntervalList.add(ONLY_LOWER_CASE_LETTERS_RU_PGI);
		}

		for (PassGenInterval passGenInterval : passGenIntervalList) {
			if (topBorder < passGenInterval.getEnd()) {
				topBorder = passGenInterval.getEnd();
			}
		}

		Double double1 = Math.random() * (topBorder + 1);
		int currentInt = double1.intValue();
		// if (restriction.equals(ONLY_LOWER_CASE_LETTERS_EN)
		// || restriction.equals(ONLY_UPPER_CASE_LETTERS_EN)
		// || restriction.equals(ONLY_NUMBERS_EN)) {
		if (passGenIntervalList.size() == 1) {
			if (currentInt >= passGenIntervalList.get(0).getBegin()
					&& currentInt <= passGenIntervalList.get(0).getEnd()) {
				conditionReturnType.setbType(true);
				conditionReturnType.setiType(currentInt);
				return conditionReturnType;
			}
		} else /*
				 * if (restriction.equals(LOWER_AND_UPPER_CASE_LETTERS_EN) ||
				 * restriction.equals(NUMBERS_AND_LOWER_EN) ||
				 * restriction.equals(NUMBERS_AND_UPPER_EN))
				 */
		if (passGenIntervalList.size() == 2) {
			if ((currentInt >= passGenIntervalList.get(0).getBegin() && currentInt <= passGenIntervalList
					.get(0).getEnd())
					|| (currentInt >= passGenIntervalList.get(1).getBegin() && currentInt <= passGenIntervalList
							.get(1).getEnd())) {
				conditionReturnType.setbType(true);
				conditionReturnType.setiType(currentInt);
				return conditionReturnType;
			}
		} else /*
				 * if (restriction.equals(LOWER_AND_UPPER_CASE_LETTERS_EN) ||
				 * restriction.equals(NUMBERS_AND_LOWER_EN) ||
				 * restriction.equals(NUMBERS_AND_UPPER_EN))
				 */
		if (passGenIntervalList.size() == 3) {
			if ((currentInt >= passGenIntervalList.get(0).getBegin() && currentInt <= passGenIntervalList
					.get(0).getEnd())
					|| (currentInt >= passGenIntervalList.get(1).getBegin() && currentInt <= passGenIntervalList
							.get(1).getEnd())
					|| (currentInt >= passGenIntervalList.get(2).getBegin() && currentInt <= passGenIntervalList
							.get(2).getEnd())) {
				conditionReturnType.setbType(true);
				conditionReturnType.setiType(currentInt);
				return conditionReturnType;
			}
		}
		return conditionReturnType;
	}

	public static void main(String[] args) {
//		char a = 'А';
//		System.out.println((int) a);
//		for (int i = 0; i < 256; i++) {
//			System.out.println(i + " = " + (char) i);
//		}
		
		String s = "@#$%^&+=!.";
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			System.out.println(c + " = " + (int) c);
		}

	}

}