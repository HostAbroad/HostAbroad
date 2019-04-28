package com.business.businessObjects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.business.transfers.TUser;

@Entity
@Table
public class UserHA {

    @Id
    private String nickname;
    @Version
    private int version;
    private String fullName;
    private String email;
    private int password;
    private double rating;
    private String description;
    private String photo;
    private String gender; //
    private boolean host;
    private boolean traveler;
    @OneToOne (mappedBy = "user")
	private Host hostEntity;
    @OneToOne (mappedBy = "user")
	private Traveler travelerEntity;
    @OneToMany(mappedBy = "userSender")
	private Collection<Likes> likes;
    @OneToMany(mappedBy = "userSender")
	private Collection<Rating> rates;
    @OneToMany(mappedBy = "userSender")
	private Collection<Matches> matches;
    @OneToMany(mappedBy = "user")
   	private List<Language> languages; 
    @OneToMany(mappedBy = "user")
   	private List<Interest> interests;

    public UserHA() {};
    
    //full constructor
    
    public UserHA(String nickname, String fullName, String email, int password, 
			double rating, String description, String photo, String gender, boolean host,
			boolean traveler, Host hostEntity, Traveler travelerEntity, 
			Collection<Likes> likes, Collection<Matches> matches,Collection<Rating> rate,
			List<Language> languages, List<Interest> interests) {
    this.nickname = nickname;
    this.fullName = fullName;
    this.rating = rating;
    this.description = description;
    this.gender = gender;
    this.photo = photo;
    this.host = host;
    this.traveler = traveler;
    this.email = email;
    this.password = password;
    this.hostEntity = hostEntity;
    this.travelerEntity = travelerEntity;
    this.likes = likes;
    this.matches = matches;
    this.rates = rate;
    this.languages = languages;
    this.interests = interests;
    }
    
    public UserHA(String nickname, String fullName, String email, int password, 
			double rating, String description, boolean host, boolean traveler, 
			Host hostEntity, Traveler travelerEntity, Collection<Likes> likes, Collection<Matches> matches,Collection<Rating> rate) {
    this.nickname = nickname;
    this.fullName = fullName;
    this.rating = rating;
    this.description = description;
    this.host = host;
    this.traveler = traveler;
    this.email = email;
    this.password = password;
    this.hostEntity = hostEntity;
    this.travelerEntity = travelerEntity;
    this.likes = likes;
    this.matches = matches;
    this.rates = rate;
    }
    

    public UserHA(String nickname, String fullName, String email, int password, 
			double rating, String description, boolean host, boolean traveler, 
			Host hostEntity, Traveler travelerEntity, Collection<Likes> likes, Collection<Rating> rates) {
    this.nickname = nickname;
    this.fullName = fullName;
    this.rating = rating;
    this.description = description;
    this.host = host;
    this.traveler = traveler;
    this.email = email;
    this.password = password;
    this.hostEntity = hostEntity;
    this.travelerEntity = travelerEntity;
    this.likes = likes;
    this.rates = rates;
    }
    
    public UserHA(String nickname, String fullName, String email, int password, 
			double rating, String description, boolean host, boolean traveler, 
			Host hostEntity, Traveler travelerEntity, Collection<Likes> likes) {
    this.nickname = nickname;
    this.fullName = fullName;
    this.rating = rating;
    this.description = description;
    this.host = host;
    this.traveler = traveler;
    this.email = email;
    this.password = password;
    this.hostEntity = hostEntity;
    this.travelerEntity = travelerEntity;
    this.likes = likes;
    }
   
    public UserHA(String nickname, String fullName, String email, int password, 
			double rating, String description, boolean host, boolean traveler, 
			Host hostEntity, Traveler travelerEntity) {
    this.nickname = nickname;
    this.fullName = fullName;
    this.rating = rating;
    this.description = description;
    this.host = host;
    this.traveler = traveler;
    this.email = email;
    this.password = password;
    this.hostEntity = hostEntity;
    this.travelerEntity = travelerEntity;
}

    public UserHA(String nickname, String fullName, String email, int password, 
    		double rating, String description, boolean host, boolean traveler) {
        this.nickname = nickname;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.rating = rating;
        this.description = description;
        this.host = host;
        this.traveler = traveler;
    }

    public UserHA(String nickname, double rating, String description, boolean host, 
    		boolean traveler) {
        this.nickname = nickname;
        this.rating = rating;
        this.description = description;
        this.host = host;
        this.traveler = traveler;
    }
    
    public UserHA(String nickname, String fullName, double rating, String description,
    		String email, int passwd, boolean host, boolean traveler) {
        this.nickname = nickname;
        this.fullName = fullName;
        this.email = email;
        this.password = passwd;
        this.rating = rating;
        this.description = description;
        this.host = host;
        this.traveler = traveler;
    }

    public UserHA(String nickname, double rating, String description) {
        this.nickname = nickname;
        this.rating = rating;
        this.description = description;
    }

    public UserHA(String nickname, String fullName, String email, int password, 
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
    
    public UserHA(String nickname, double rating, String description, boolean host, boolean traveler, String email,
            int password) {
    this.nickname = nickname;
    this.rating = rating;
    this.description = description;
    this.host = host;
    this.traveler = traveler;
    this.email = email;
    this.password = password;
}
    
    public UserHA(String nickname, String fullName, String email, int password) {
    	this.nickname = nickname;
    	this.fullName = fullName;
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
    
    public double calculateRating() {
    	double result = 0;
    	
        for(Rating rate : this.rates) 
        	result += rate.getRate();
        
        result = result/this.rates.size();
        return result;
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

    public void setPassword(int password) {
        this.password = password;
    }

    public Integer getPassword() {
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
    
    public void setTravelerEntity(Traveler traveler) {
    	this.travelerEntity = traveler;
    }
    
    public Traveler getTravelerEntity() {
    	return this.travelerEntity;
    }
    
    public void setLikes(Collection<Likes> likes) {
    	this.likes = likes;
    }
    
    public Collection<Likes> getLikes(){
    	return likes;
    }

    
    public void setRates(Collection<Rating> rates) {
    	this.rates = rates;
    }
    
    public Collection<Rating> getRates(){
    	return rates;
    }
    
    public void addRate(Rating rating) {
    	this.rates.add(rating);
    }
    
    public void updateRating() {
    	this.setRating(this.calculateRating());
    }
      
    public void setMatches(Collection<Matches> matches) {
    	this.matches = matches;
    }
    
    public Collection<Matches> getMatches(){
    	return matches;
    }
    
    public TUser toTransfer() {
    	return new TUser(this.nickname, this.fullName, this.email, 
    			((Integer)this.password).toString(), this.rating, this.description, this.host, this.traveler);
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
	
	public List<Language> getLanguages(){
		return this.languages;
	}
	
	public void getLanguages(ArrayList<Language> languages){
		this.languages = languages;
	}
	
	public List<Interest> getInterests(){
		return this.interests;
	}
	
	public void setInterests(List<Interest> interests) {
		this.interests = interests;
	}
}

