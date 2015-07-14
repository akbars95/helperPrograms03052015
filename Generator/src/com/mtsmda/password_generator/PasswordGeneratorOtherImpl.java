package com.mtsmda.password_generator;

import java.util.ArrayList;
import java.util.List;

public class PasswordGeneratorOtherImpl implements PasswordGeneratorI {

	private static final String SPECIAL_CHARACTERS = "@#$%^&+=!.";

	public static final PassGenInterval ONLY_UPPER_CASE_LETTERS_EN_PGI_L = new PassGenInterval(
			0, 25);
	public static final PassGenInterval ONLY_LOWER_CASE_LETTERS_EN_PGI_L = new PassGenInterval(
			26, 51);
	public static final PassGenInterval ONLY_NUMBERS_PGI_L = new PassGenInterval(
			52, 61);
	public static final PassGenInterval ONLY_UPPER_CASE_LETTERS_RU_PGI_L = new PassGenInterval(
			62, 93);
	public static final PassGenInterval ONLY_LOWER_CASE_LETTERS_RU_PGI_L = new PassGenInterval(
			94, 125);
	public static final PassGenInterval SPECIAL_CHARACTERS_PGI_L = new PassGenInterval(
			126, 135);

	public static final Integer UPPER_CASE_LETTERS_EN_AND_SPECIAL_CHARACTERS = 14;
	public static final Integer LOWER_CASE_LETTERS_EN_AND_SPECIAL_CHARACTERS = 15;
	public static final Integer LOWER_AND_UPPER_CASE_LETTERS_EN_AND_SPECIAL_CHARACTERS = 16;
	public static final Integer NUMBERS_AND_SPECIAL_CHARACTERS = 17;
	public static final Integer NUMBERS_AND_LOWER_EN_AND_SPECIAL_CHARACTERS = 18;
	public static final Integer NUMBERS_AND_UPPER_EN_AND_SPECIAL_CHARACTERS = 19;
	public static final Integer NUMBERS_AND_UPPER_LOWER_EN_SPECIAL_CHARACTERS = 20;

	public static final Integer UPPER_CASE_LETTERS_RU_SPECIAL_CHARACTERS = 21;
	public static final Integer LOWER_CASE_LETTERS_RU_SPECIAL_CHARACTERS = 22;
	public static final Integer LOWER_AND_UPPER_CASE_LETTERS_RU_SPECIAL_CHARACTERS = 23;
	public static final Integer NUMBERS_AND_LOWER_RU_SPECIAL_CHARACTERS = 24;
	public static final Integer NUMBERS_AND_UPPER_RU_SPECIAL_CHARACTERS = 25;
	public static final Integer NUMBERS_AND_UPPER_LOWER_RU_SPECIAL_CHARACTERS = 26;

	private List<Character> currentCharacters;

	private List<PassGenInterval> passGenIntervalList;

	private static List<Character> characters = new ArrayList<>();

	private int topBorder = 0;

	static {
		fill(ONLY_UPPER_CASE_LETTERS_EN_PGI);
		fill(ONLY_LOWER_CASE_LETTERS_EN_PGI);
		fill(ONLY_NUMBERS_PGI);
		fill(ONLY_UPPER_CASE_LETTERS_RU_PGI);
		fill(ONLY_LOWER_CASE_LETTERS_RU_PGI);
		fill(SPECIAL_CHARACTERS);
	}

	private static void fill(PassGenInterval passGenInterval) {
		for (int i = passGenInterval.getBegin(); i <= passGenInterval.getEnd(); i++) {
			characters.add((char) i);
		}
	}

	private static void fill(String s) {
		for (int i = 0; i < s.length(); i++) {
			characters.add(s.charAt(i));
		}
	}

	public String passwordGeneratorCore(int countLetter, Integer restriction) {
		StringBuilder outPassword = new StringBuilder();

		if (countLetter <= 0) {
			countLetter = 10;
		}

		for (int i = 0; i < countLetter;) {
			ConditionReturnType condition = condition(restriction);
			if (condition.isbType()
					&& currentCharacters.size() > condition.getiType()) {
				outPassword.append(currentCharacters.get(condition.getiType()));
				i++;
			}
		}
		return outPassword.toString();
	}

	public ConditionReturnType condition(Integer restriction) {
		ConditionReturnType conditionReturnType = new ConditionReturnType();
		define(restriction);

		fillCurrentCharacters(passGenIntervalList);
		setTopBorder();

		Double double1 = Math.random() * topBorder;
		int currentInt = double1.intValue();

		if (currentCharacters.size() > currentInt) {
			conditionReturnType.setbType(true);
			conditionReturnType.setiType(currentInt);
			return conditionReturnType;
		}
		return conditionReturnType;
	}

	public List<Character> getCurrentCharacters() {
		if (currentCharacters == null) {
			currentCharacters = new ArrayList<Character>();
		}
		return currentCharacters;
	}

	public void setCurrentCharacters(List<Character> currentCharacters) {
		this.currentCharacters = currentCharacters;
	}

	private void fillCurrentCharacters(List<PassGenInterval> passGenIntervals) {
		if (getCurrentCharacters().isEmpty()) {
			for (PassGenInterval passGenInterval : passGenIntervals) {
				for (int i = passGenInterval.getBegin(); i <= passGenInterval
						.getEnd(); i++) {
					getCurrentCharacters().add(characters.get(i));
				}
			}
		}
	}

	private void fillPassGenIntervalList(
			List<PassGenInterval> passGenIntervalList,
			PassGenInterval... genIntervals) {
		if (passGenIntervalList.isEmpty()) {
			for (PassGenInterval passGenInterval : genIntervals) {
				passGenIntervalList.add(passGenInterval);
			}
		}
	}

	private void setTopBorder() {
		this.topBorder = currentCharacters.size();
	}

	public int getTopBorder() {
		return topBorder;
	}

	public void setTopBorder(int topBorder) {
		this.topBorder = topBorder;
	}

	private void define(Integer restriction) {
		if (passGenIntervalList == null) {
			passGenIntervalList = new ArrayList<PassGenInterval>();

			if (restriction.equals(ONLY_UPPER_CASE_LETTERS_EN)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_UPPER_CASE_LETTERS_EN_PGI_L);
			} else if (restriction.equals(ONLY_LOWER_CASE_LETTERS_EN)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_LOWER_CASE_LETTERS_EN_PGI_L);
			} else if (restriction.equals(LOWER_AND_UPPER_CASE_LETTERS_EN)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_UPPER_CASE_LETTERS_EN_PGI_L,
						ONLY_LOWER_CASE_LETTERS_EN_PGI_L);
			} else if (restriction.equals(ONLY_NUMBERS)) {
				fillPassGenIntervalList(passGenIntervalList, ONLY_NUMBERS_PGI_L);
			} else if (restriction.equals(NUMBERS_AND_LOWER_EN)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_NUMBERS_PGI_L, ONLY_LOWER_CASE_LETTERS_EN_PGI_L);
			} else if (restriction.equals(NUMBERS_AND_UPPER_EN)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_NUMBERS_PGI_L, ONLY_UPPER_CASE_LETTERS_EN_PGI_L);
			} else if (restriction.equals(NUMBERS_AND_UPPER_LOWER_EN)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_NUMBERS_PGI_L, ONLY_UPPER_CASE_LETTERS_EN_PGI_L,
						ONLY_LOWER_CASE_LETTERS_EN_PGI_L);
			} else if (restriction.equals(ONLY_UPPER_CASE_LETTERS_RU)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_UPPER_CASE_LETTERS_RU_PGI_L);
			} else if (restriction.equals(ONLY_LOWER_CASE_LETTERS_RU)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_LOWER_CASE_LETTERS_RU_PGI_L);
			} else if (restriction.equals(LOWER_AND_UPPER_CASE_LETTERS_RU)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_UPPER_CASE_LETTERS_RU_PGI_L,
						ONLY_LOWER_CASE_LETTERS_RU_PGI_L);
			} else if (restriction.equals(NUMBERS_AND_LOWER_RU)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_NUMBERS_PGI_L, ONLY_LOWER_CASE_LETTERS_RU_PGI_L);
			} else if (restriction.equals(NUMBERS_AND_UPPER_RU)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_NUMBERS_PGI_L, ONLY_UPPER_CASE_LETTERS_RU_PGI_L);
			} else if (restriction.equals(NUMBERS_AND_UPPER_LOWER_RU)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_NUMBERS_PGI_L, ONLY_UPPER_CASE_LETTERS_RU_PGI_L,
						ONLY_LOWER_CASE_LETTERS_RU_PGI_L);
			} else if (restriction
					.equals(UPPER_CASE_LETTERS_EN_AND_SPECIAL_CHARACTERS)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_UPPER_CASE_LETTERS_EN_PGI_L,
						SPECIAL_CHARACTERS_PGI_L);
			} else if (restriction
					.equals(LOWER_CASE_LETTERS_EN_AND_SPECIAL_CHARACTERS)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_LOWER_CASE_LETTERS_EN_PGI_L,
						SPECIAL_CHARACTERS_PGI_L);
			} else if (restriction
					.equals(LOWER_AND_UPPER_CASE_LETTERS_EN_AND_SPECIAL_CHARACTERS)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_LOWER_CASE_LETTERS_EN_PGI_L,
						ONLY_UPPER_CASE_LETTERS_EN_PGI_L,
						SPECIAL_CHARACTERS_PGI_L);
			} else if (restriction.equals(NUMBERS_AND_SPECIAL_CHARACTERS)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_NUMBERS_PGI_L, SPECIAL_CHARACTERS_PGI_L);
			} else if (restriction
					.equals(NUMBERS_AND_LOWER_EN_AND_SPECIAL_CHARACTERS)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_NUMBERS_PGI_L, ONLY_LOWER_CASE_LETTERS_EN_PGI_L,
						SPECIAL_CHARACTERS_PGI_L);
			} else if (restriction
					.equals(NUMBERS_AND_UPPER_EN_AND_SPECIAL_CHARACTERS)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_NUMBERS_PGI_L, ONLY_UPPER_CASE_LETTERS_EN_PGI_L,
						SPECIAL_CHARACTERS_PGI_L);
			} else if (restriction
					.equals(NUMBERS_AND_UPPER_LOWER_EN_SPECIAL_CHARACTERS)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_NUMBERS_PGI_L, ONLY_UPPER_CASE_LETTERS_EN_PGI_L,
						ONLY_LOWER_CASE_LETTERS_EN_PGI_L,
						SPECIAL_CHARACTERS_PGI_L);
			} else if (restriction
					.equals(UPPER_CASE_LETTERS_RU_SPECIAL_CHARACTERS)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_UPPER_CASE_LETTERS_RU_PGI_L,
						SPECIAL_CHARACTERS_PGI_L);
			} else if (restriction
					.equals(LOWER_CASE_LETTERS_RU_SPECIAL_CHARACTERS)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_LOWER_CASE_LETTERS_RU_PGI_L,
						SPECIAL_CHARACTERS_PGI_L);
			} else if (restriction
					.equals(LOWER_AND_UPPER_CASE_LETTERS_RU_SPECIAL_CHARACTERS)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_LOWER_CASE_LETTERS_RU_PGI_L,
						ONLY_UPPER_CASE_LETTERS_RU_PGI_L,
						SPECIAL_CHARACTERS_PGI_L);
			} else if (restriction
					.equals(NUMBERS_AND_LOWER_RU_SPECIAL_CHARACTERS)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_NUMBERS_PGI_L, ONLY_LOWER_CASE_LETTERS_RU_PGI_L,
						SPECIAL_CHARACTERS_PGI_L);
			} else if (restriction
					.equals(NUMBERS_AND_UPPER_RU_SPECIAL_CHARACTERS)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_NUMBERS_PGI_L, ONLY_UPPER_CASE_LETTERS_RU_PGI_L,
						SPECIAL_CHARACTERS_PGI_L);
			} else if (restriction
					.equals(NUMBERS_AND_UPPER_LOWER_RU_SPECIAL_CHARACTERS)) {
				fillPassGenIntervalList(passGenIntervalList,
						ONLY_NUMBERS_PGI_L, ONLY_UPPER_CASE_LETTERS_RU_PGI_L,
						ONLY_LOWER_CASE_LETTERS_RU_PGI_L,
						SPECIAL_CHARACTERS_PGI_L);
			}
		}
	}

	// public static void main(String[] args) {
	// // char a = 'А';
	// // System.out.println((int) a);
	// // for (int i = 0; i < 256; i++) {
	// // System.out.println(i + " = " + (char) i);
	// // }
	//
	// // String s = "@#$%^&+=!.";
	// // for(int i = 0; i < s.length(); i++){
	// // char c = s.charAt(i);
	// // System.out.println(c + " = " + (int) c);
	// // }
	//
	// // System.out.println(characters.size());
	//
	// }

}