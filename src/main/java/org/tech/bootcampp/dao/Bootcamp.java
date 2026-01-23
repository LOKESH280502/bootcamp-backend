package org.tech.bootcampp.dao;
 
import jakarta.persistence.*;

@Entity
@Table(name = "bootcamps")
public class Bootcamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String website;

    private String address;

    /**
     * Stored as comma-separated values
     * Example: "react development,java development"
     */
    private String careers;

    private String photo;

    private Boolean housing;

    private Boolean jobAssistance;

    private Boolean jobGuarantee;

    private Boolean acceptGi;

    // -------------------- getters & setters --------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCareers() {
        return careers;
    }

    public void setCareers(String careers) {
        this.careers = careers;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getHousing() {
        return housing;
    }

    public void setHousing(Boolean housing) {
        this.housing = housing;
    }

    public Boolean getJobAssistance() {
        return jobAssistance;
    }

    public void setJobAssistance(Boolean jobAssistance) {
        this.jobAssistance = jobAssistance;
    }

    public Boolean getJobGuarantee() {
        return jobGuarantee;
    }

    public void setJobGuarantee(Boolean jobGuarantee) {
        this.jobGuarantee = jobGuarantee;
    }

    public Boolean getAcceptGi() {
        return acceptGi;
    }

    public void setAcceptGi(Boolean acceptGi) {
        this.acceptGi = acceptGi;
    }
}
