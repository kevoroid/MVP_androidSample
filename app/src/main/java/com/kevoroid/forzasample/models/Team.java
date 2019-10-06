package com.kevoroid.forzasample.models;

import com.google.gson.annotations.SerializedName;

public class Team {

	@SerializedName("id")
	private Integer id;
	@SerializedName("name")
	private String name;
	@SerializedName("gender")
	private String gender;
	@SerializedName("national")
	private Boolean national;
	@SerializedName("description")
	private String description;
	@SerializedName("badge_url")
	private String badgeUrl;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBadgeUrl() {
		return badgeUrl;
	}

	public void setBadgeUrl(String badgeUrl) {
		this.badgeUrl = badgeUrl;
	}
}
