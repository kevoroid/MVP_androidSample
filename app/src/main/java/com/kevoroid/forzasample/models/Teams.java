package com.kevoroid.forzasample.models;

import com.google.gson.annotations.SerializedName;

public class Teams {

	@SerializedName("id")
	private Integer id;
	@SerializedName("gender")
	private String gender;
	@SerializedName("national")
	private Boolean national;
	@SerializedName("name")
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Boolean getNational() {
		return national;
	}

	public void setNational(Boolean national) {
		this.national = national;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
