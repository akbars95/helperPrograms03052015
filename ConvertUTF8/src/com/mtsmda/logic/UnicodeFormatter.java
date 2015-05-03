package com.mtsmda.logic;

public class UnicodeFormatter {

	public static String native2Ascii(String s, boolean unNative2Ascii) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char aChar = s.charAt(i);
			if (unNative2Ascii) {
				sb.append(aChar);
			} else {
				if ((aChar < 0x0020) || (aChar > 0x007e)) {
					sb.append('\\');
					sb.append('u');
					sb.append(toHex((aChar >> 12) & 0xF));
					sb.append(toHex((aChar >> 8) & 0xF));
					sb.append(toHex((aChar >> 4) & 0xF));
					sb.append(toHex(aChar & 0xF));
				} else {
					sb.append(aChar);
				}
			}
		}
		return sb.toString();
	}

	private static char toHex(int nibble) {
		final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9', 'A', 'B', 'C', 'D', 'E', 'F' };
		return hexDigit[nibble & 0xF];
	}

	public static String unNative2Ascii(String textIn) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < textIn.length(); i++) {
			char aChar = textIn.charAt(i);
			sb.append(aChar);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		// System.out.println(UnicodeFormatter.native2Ascii("Dima Мынзат"));
		System.out.println(UnicodeFormatter
				.unNative2Ascii("\u041C\u044B\u043D\u0437\u0430\u0442"));
		System.out.println(UnicodeFormatter.native2Ascii(
				"\u041C\u044B\u043D\u0437\u0430\u0442", true));
	}

}