package com.business.businessObjects;

import java.io.Serializable;

public class PlacesKey implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String host;
	private String address;
	
	public PlacesKey() {}
	
	public PlacesKey(String host, String address) {
		this.host = host;
		this.address = address;
	}
	
	@Override
	public int hashCode() {
		return host.hashCode() + address.hashCode();
	}
	
	@Override
	public boolean equals(Object key) {
		return this.address.equals(((PlacesKey)key).getAddress()) 
				&& this.host.equals(((PlacesKey)key).getHost());
	}
	
	public String getHost() {
		return this.host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}
