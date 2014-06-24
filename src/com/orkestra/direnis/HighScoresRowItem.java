package com.orkestra.direnis;

public class HighScoresRowItem {

	private String name;
	private int score;
	private int number;

	public HighScoresRowItem(String name, int number, int score) {
		this.score = score;
		this.name = name;
		this.number = number;
		

	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
