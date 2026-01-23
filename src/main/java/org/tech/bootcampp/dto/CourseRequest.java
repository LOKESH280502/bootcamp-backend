package org.tech.bootcampp.dto;

 

public class CourseRequest {

    private String title;
    private String description;
    private String duration;
    private double price;
    private String minimumSkill;
    private boolean scholarshipAvailable;
    private String image;

    // Getters & Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMinimumSkill() {
        return minimumSkill;
    }

    public void setMinimumSkill(String minimumSkill) {
        this.minimumSkill = minimumSkill;
    }

    public boolean isScholarshipAvailable() {
        return scholarshipAvailable;
    }

    public void setScholarshipAvailable(boolean scholarshipAvailable) {
        this.scholarshipAvailable = scholarshipAvailable;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
