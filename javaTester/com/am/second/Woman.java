package com.am.second;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

public class Woman {
	
	
	//variable (Global)
	private String name = "Ngoc";
	private String address = "Ngọc Phường, Hà Nội";
	//default
	String dob = "10/10/2000";
	
	//protected
	protected String sex = "female";
	
	//public
	public int weight = 40;
	
	//Method
	private String getWomanName() {
		return this.name;
	}
	
	public String getDob() {
		return this.dob;
	}
	
	protected String getSex() {
		return this.sex;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
}
