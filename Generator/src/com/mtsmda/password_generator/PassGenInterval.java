package com.mtsmda.password_generator;

public class PassGenInterval {

	private int begin;
	private int end;

	public PassGenInterval(int begin, int end) {
		setBegin(begin);
		setEnd(end);
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + begin;
		result = prime * result + end;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PassGenInterval other = (PassGenInterval) obj;
		if (begin != other.begin)
			return false;
		if (end != other.end)
			return false;
		return true;
	}

}