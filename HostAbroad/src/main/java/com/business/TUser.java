package com.business;

public class TUser {
	private String nickname;
	private String fullName;
	private String password;
	private String email;
	private double rating;
	private String description;
	private boolean host;
	private boolean traveler;
	
	public TUser() {};
	
	public TUser(String nickname, String fullName, String email, String password, double rating, String description, boolean host,boolean traveler){
		this.nickname = nickname;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.rating = rating;
		this.description = description;
		this.host = host;
		this.setTraveler(traveler);
	}
	
	public TUser(String nickname, double rating, String description, boolean host){
		this.nickname = nickname;
		this.rating = rating;
		this.description = description;
		this.host = host;
	}
	
	public TUser(String nickname, double rating, boolean traveler, String description){
		this.nickname = nickname;
		this.rating = rating;
		this.description = description;
		this.setTraveler(traveler);
	}
	
	
	public TUser(String nickname, double rating, String description){
		this.nickname = nickname;
		this.rating = rating;
		this.description = description;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getNickname() {
		return this.nickname;
	}
	
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public double getRating() {
		return this.rating;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setHost(boolean host) {
		this.host = host;
	}
	
	public boolean getHost() {
		return this.host;
	}

	public boolean getTraveler() {
		return this.traveler;
	}

	public void setTraveler(boolean traveler) {
		this.traveler = traveler;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getFullName() {
		return this.fullName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
}
