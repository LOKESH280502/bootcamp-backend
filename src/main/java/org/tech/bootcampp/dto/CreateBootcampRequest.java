package org.tech.bootcampp.dto;

 
public class CreateBootcampRequest {
    private String name;
    private String email;
    private String description;
    private String website;
    private String careers;
    private String address;
    private String photo;
    // getters & setters
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getCareers() {
		return careers;
	}
	public void setCareers(String careers) {
		this.careers = careers;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
    
    
}
