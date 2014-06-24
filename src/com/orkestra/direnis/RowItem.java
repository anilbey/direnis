package com.orkestra.direnis;

public class RowItem {
	private int imageId;
	private String title;
	private String desc;
	private double power;
	private int value;

	public RowItem(int imageId, String title, String desc, double power,
			int value) {
		this.imageId = imageId;
		this.title = title;
		this.desc = desc;
		this.power = power;
		this.value = value;
	}

	public double getPower() {
		return power;
	}

	public int getValue() {
		return value;
	}

	public int getImageId() {
		return imageId;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return title + "\n" + desc;
	}
}