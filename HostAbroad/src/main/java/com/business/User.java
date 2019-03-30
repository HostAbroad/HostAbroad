package com.business;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table
public class User {

    @Id
    private String nickname;
    @Version
    private int version;
    private String fullName;
    private String email;
    private String password;
    private double rating;
    private String description;
    private boolean host;
    private boolean traveler;
    @OneToOne (mappedBy = "user")
	private Host hostEntity;

    public User() {};

    public User(String nickname, String fullName, String email, String password, double rating, String description, boolean host, boolean traveler) {
        this.nickname = nickname;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.rating = rating;
        this.description = description;
        this.host = host;
        this.traveler = traveler;
    }



    public User(String nickname, double rating, String description, boolean host, boolean traveler) {
        this.nickname = nickname;
        this.rating = rating;
        this.description = description;
        this.host = host;
        this.traveler = traveler;
    }
    
    public User(String nickname, String fullName, double rating, String description,
    		String email, String passwd, boolean host, boolean traveler) {
        this.nickname = nickname;
        this.fullName = fullName;
        this.email = email;
        this.password = passwd;
        this.rating = rating;
        this.description = description;
        this.host = host;
        this.traveler = traveler;
    }

    public User(String nickname, double rating, String description) {
        this.nickname = nickname;
        this.rating = rating;
        this.description = description;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String nickname, String fullName, String email, String password, 
    			double rating, String description, boolean host, boolean traveler, Host hostEntity) {
        this.nickname = nickname;
        this.fullName = fullName;
        this.rating = rating;
        this.description = description;
        this.host = host;
        this.traveler = traveler;
        this.email = email;
        this.password = password;
        this.hostEntity = hostEntity;
    }
    
    public User(String nickname, double rating, String description, boolean host, boolean traveler, String email,
            String password) {
    this.nickname = nickname;
    this.rating = rating;
    this.description = description;
    this.host = host;
    this.traveler = traveler;
    this.email = email;
    this.password = password;
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

    public void setTraveler(boolean traveler) {
        this.traveler = traveler;
    }

    public boolean getTraveler() {
        return this.traveler;
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
    
    public void setVersion(int version) {
    	this.version = version;
    }
    
    public int getVersion() {
    	return this.version;
    }
    
    public void setHostEntity(Host host) {
    	this.hostEntity = host;
    }
    
    public Host getHostEntity() {
    	return this.hostEntity;
    }
}

