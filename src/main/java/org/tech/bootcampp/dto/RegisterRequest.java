package org.tech.bootcampp.dto;

 

public class RegisterRequest {
    public String name;
    public String email;
    public String password;
    public String role;
    public String avatar;
    private String publisherCode;
 // getter and setter
 public String getPublisherCode() { return publisherCode; }
 public void setPublisherCode(String publisherCode) { this.publisherCode = publisherCode; }
    
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	 
    
}
