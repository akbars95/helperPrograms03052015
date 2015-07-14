package com.mtsmda.password_generator;

public class CryptUser {

	private StringBuilder output;
	private StringBuilder randomKeys;

	public CryptUser() {
		super();
	}

	public CryptUser(StringBuilder output) {
		this();
		this.output = output;
	}

	public StringBuilder getOutput() {
		if (output == null) {
			output = new StringBuilder();
		}
		return output;
	}

	public void setOutput(StringBuilder output) {
		this.output = output;
	}

	public StringBuilder getRandomKeys() {
		if (randomKeys == null) {
			randomKeys = new StringBuilder();
		}
		return randomKeys;
	}

	public void setRandomKeys(StringBuilder randomKeys) {
		this.randomKeys = randomKeys;
	}

}