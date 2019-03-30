package com.business;

public class TUser {
	private String nickname;
	private String fullName;
	private String password;
	private double rating;
	private String description;
	private boolean host;
	private boolean traveler;
	private String email;
	private String passwd;
	
	public TUser() {};
	
	public TUser(String nickname, String fullName, String email, String password, double rating, String description, boolean host,boolean traveler){
		this.nickname = nickname;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.rating = rating;
		this.description = description;
		this.host = host;
		this.setTraveler = traveler;
	}
	
	public TUser(String nickname, double rating, String description, boolean host){
		this.nickname = nickname;
		this.rating = rating;
		this.description = description;
		this.host = host;
		this.traveler = traveler;
	}
	
	public TUser(String nickname, double rating, boolean traveler, String description){
		this.nickname = nickname;
		this.rating = rating;
		this.description = description;
		this.traveler = traveler;
	}
	
	
	public TUser(String nickname, double rating, String description){
		this.nickname = nickname;
		this.rating = rating;
		this.description = description;
	}
	
	public TUser(String email, String password) {
		this.email = email;
		this.passwd = password;
	}
	
	public TUser(String nickname, double rating, String description, boolean host,boolean traveler, String email, String password){
		this.nickname = nickname;
		this.rating = rating;
		this.description = description;
		this.host = host;
		this.setTraveler(traveler);
		this.email = email;
		this.passwd = password;
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
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.passwd = password;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.passwd;
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
		return traveler;
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
}
