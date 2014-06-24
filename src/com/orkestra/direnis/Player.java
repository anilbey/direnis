package com.orkestra.direnis;

import android.text.format.Time;

public class Player {

	public String []items = {"","",""};
	public double []itemPowers = {-200, -200, -200};
	public int []itemIndexes = {0,0,0};
	private String name;
	private int level;
	private int exp;
	private int point;
	static Time dateTime;
	static String current;
	static int medCount;
	private double itemPower = 0.6;
	public Player(int level, int point) {
		this.level = level;
		this.point = point;
	}
	
	public double getItemPower (){
		return itemPower;
	}
	public String getName() {
		return name;
	}

	public int getPoint() {
		return point;
	}

	public int getExp() {
		return exp;
	}

	public int getLevel() {
		return level;
	}

	public Time getDateTime() {
		return dateTime;
	}

	public int getMedCount() {
		return medCount;
	}

	public String getCurrent() {
		return current;
	}

	public void setItemPower (double itemPower){
		this.itemPower = itemPower;
	}

	public void setDateTime(Time dateTime) {
		this.dateTime = dateTime;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public void setMedCount(int medCount) {
		this.medCount = medCount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}
