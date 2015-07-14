package com.mtsmda.password_generator;

public class Cryptographer {
	public static final Boolean METHOD_CODE = true;
	public static final Boolean METHOD_DECODE = false;
	private static final String SEPARATOR = "__";

	public CryptUser defaultCrypt(StringBuilder in, boolean isCodemethod) {
		return cryptCore(in, isCodemethod, 9, MethodCrypt.DEFAULT);
	}

	public CryptUser randomCrypt(StringBuilder in, boolean isCodemethod) {
		Double double1 = new Double(Math.random() * 1000);
		return cryptCore(in, isCodemethod, double1.intValue(),
				MethodCrypt.RANDOM);
	}
	
	public CryptUser randomEachCrypt(StringBuilder in, boolean isCodemethod) {
		return cryptCore(in, isCodemethod, -1,
				MethodCrypt.EACH_RANDOM);
	}

	private CryptUser cryptCore(StringBuilder in, boolean isCodemethod,
			Integer shift, MethodCrypt methodCrypt) {
		CryptUser cryptUser = new CryptUser();
		if (in == null && in.length() == 0) {
			return cryptUser;
		}
		if (in.length() < 10) {
			throw new RuntimeException(
					"Min length password should be 10 symbols!");
		}

		if (methodCrypt.equals(MethodCrypt.RANDOM) && !isCodemethod) {
			int index = in.lastIndexOf(SEPARATOR);
			String substring = in.substring(index).replace(SEPARATOR, "");
			shift = Integer.parseInt(substring);
			in.delete(index, in.length());
		}

		StringBuilder out = new StringBuilder();

		if (!isCodemethod) {
			deleteMethodCrypt(methodCrypt, in, isCodemethod);
		}

		for (int i = 0; i < in.length(); i++) {
			if(methodCrypt.equals(MethodCrypt.EACH_RANDOM) && isCodemethod){
				Double d = Math.random() * 1000;
				shift = d.intValue();
			}
			char currentChar = in.charAt(i);
			int currentCharCode = (int) currentChar;
			if (isCodemethod) {
				currentCharCode += shift;
			} else {
				currentCharCode -= shift;
			}
			out.append((char) currentCharCode);
			cryptUser.getRandomKeys();
		}

		cryptUser.setOutput(out);
		if (isCodemethod) {
			insertMethodCrypt(methodCrypt, cryptUser, isCodemethod);
		}

		if (methodCrypt.equals(MethodCrypt.RANDOM) && isCodemethod) {
			cryptUser.setRandomKeys(new StringBuilder(shift));
			cryptUser.getOutput().append(SEPARATOR).append(shift);
		}
		return cryptUser;
	}

	private void insertMethodCrypt(MethodCrypt methodCrypt,
			CryptUser cryptUser, boolean isCodemethod) {
		coreMethodCrypt(methodCrypt, cryptUser, isCodemethod);
	}

	private void deleteMethodCrypt(MethodCrypt methodCrypt, StringBuilder in,
			boolean isCodemethod) {
		CryptUser cryptUser = new CryptUser(in);
		coreMethodCrypt(methodCrypt, cryptUser, isCodemethod);
	}

	private void coreMethodCrypt(MethodCrypt methodCrypt, CryptUser cryptUser,
			boolean isCodemethod) {
		int[] indexes = new int[] { 5, 7, 9 };
		for (int i = 0; i < methodCrypt.getName().length(); i++) {
			if (isCodemethod) {
				cryptUser.getOutput().insert(indexes[i],
						methodCrypt.getName().charAt(i));
			} else {
				cryptUser.getOutput().deleteCharAt(indexes[i] - i);
			}
		}
	}

	public static void main(String[] args) {

	}

	public enum MethodCrypt {
		DEFAULT("def"), RANDOM("rdm"), EACH_RANDOM("erd");

		private String name;

		private MethodCrypt(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}
}