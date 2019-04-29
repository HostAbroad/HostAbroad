package com.business.transfers;

import java.util.ArrayList;
import java.util.TreeSet;

import com.business.enums.InterestsEnum;
import com.business.enums.LanguagesEnum;

public class TUser{
	private String nickname;
	private String fullName;
	private String password;
	private String email;
	private String description;
	private String photo;
	private String gender;
	private String country;
	private double rating;
	private String birthday;
	private boolean host;
	private boolean traveler;
	private ArrayList<String> likes;
	private ArrayList<String> rates;
	private TreeSet<LanguagesEnum> languages;
	private TreeSet<InterestsEnum> interests;

	private ArrayList<String> matches;

	public TUser() {
	};
	
	//Full constructor
	public TUser(String nickname, String fullName, String password, String email,
			String description, String photo, String gender, String birthday, double rating, boolean host,
			boolean traveler,ArrayList<String> likes, ArrayList<String> rates,
			TreeSet<LanguagesEnum> languages, TreeSet<InterestsEnum> interests, ArrayList<String> matches,
			String country){

		this.nickname = nickname;
		this.fullName = fullName;
		this.password = password;
		this.email = email;
		this.description = description;
		this.photo = photo;
		this.gender = gender;
		this.rating = rating;
		this.birthday = birthday;
		this.host = host;
		this.traveler = traveler;
		this.likes = likes;
		this.rates = rates;
		this.languages = languages;
		this.interests = interests;
		this.matches = matches;
		this.country = country;
	}
	
	public TUser(String nickname, String fullName, String password, String email,
			String description, String photo, String gender, String birthday, double rating, boolean host,
			boolean traveler,ArrayList<String> likes, ArrayList<String> rates,
			TreeSet<LanguagesEnum> languages, ArrayList<String> matches){

		this.nickname = nickname;
		this.fullName = fullName;
		this.password = password;
		this.email = email;
		this.description = description;
		this.photo = photo;
		this.gender = gender;
		this.birthday = birthday;
		this.rating = rating;
		this.host = host;
		this.traveler = traveler;
		this.likes = likes;
		this.rates = rates;
		this.languages = languages;
		this.matches = matches;
	}
	
	public TUser(String nickname, String fullName, String email, String password, 
			double rating, String description, boolean host, boolean traveler,
			ArrayList<String> likes, ArrayList<String> rates, ArrayList<String> matches){

		this.nickname = nickname;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.rating = rating;
		this.description = description;
		this.host = host;
		this.traveler = traveler;
		this.likes = likes;
		this.rates = rates;
		this.matches = matches;
	}

	public TUser(String nickname, String fullName, String email, String password, double rating, String description,
					boolean host, boolean traveler, ArrayList<String> likes) {
		this.nickname = nickname;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.rating = rating;
		this.description = description;
		this.host = host;
		this.traveler = traveler;
		this.likes = likes;
	}

	public TUser(String nickname, String fullName, String email, String password, 
			double rating, String description,
			boolean host, boolean traveler) {
		this.nickname = nickname;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.rating = rating;
		this.description = description;
		this.host = host;
		this.traveler = traveler;
	}

	public TUser(String nickname, double rating, String description, boolean host) {
		this.nickname = nickname;
		this.rating = rating;
		this.description = description;
		this.host = host;
	}

	public TUser(String nickname, double rating, boolean traveler, String description) {
		this.nickname = nickname;
		this.rating = rating;
		this.description = description;
		this.traveler = traveler;
	}

	public TUser(String nickname, double rating, String description) {
		this.nickname = nickname;
		this.rating = rating;
		this.description = description;
	}

	public TUser(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public TUser(String nickname, double rating, String description, boolean host, 
			boolean traveler, String email, String password) {
		this.nickname = nickname;
		this.rating = rating;
		this.description = description;
		this.host = host;
		this.setTraveler(traveler);
		this.email = email;
		this.password = password;
	}

	public TUser(String nickname, double rating, String description, boolean host, 
			boolean traveler) {
		this.nickname = nickname;
		this.rating = rating;
		this.description = description;
		this.host = host;
		this.traveler = traveler;
	}

	public TUser(String nickname, String fullName, String email, String password) {
		this.nickname = nickname;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
	}
	
	public TUser(String nickname) {
		this.nickname = nickname;
	}
	
	public TUser(String nickname, TreeSet<InterestsEnum> interests) {
		this.nickname = nickname;
		this.interests = interests;
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

	public ArrayList<String> getLikes() {
		return likes;
	}

	public void setLikes(ArrayList<String> likes) {
		this.likes = likes;
	}

	public ArrayList<String> getRates() {
		return rates;
	}

	public void setRates(ArrayList<String> rates) {
		this.rates = rates;
	}
	
	public ArrayList<String> getMatches() {
		return matches;
	}

	public void setMatches(ArrayList<String> matches) {
		this.matches = matches;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public TreeSet<LanguagesEnum> getLanguages(){
		return this.languages;
	}
	
	public void getLanguages(TreeSet<LanguagesEnum> languages){
		this.languages = languages;
	}
	
	public TreeSet<InterestsEnum> getInterests(){
		return this.interests;
	}
	
	public void setInterests(TreeSet<InterestsEnum> interests) {
		this.interests = interests;
	}
	
	public String getBirthday() {
		return this.birthday;
	}
	
	public void setBrthday(String birthday) {
		this.birthday = birthday;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getCountry() {
		return this.country;
	}
}
