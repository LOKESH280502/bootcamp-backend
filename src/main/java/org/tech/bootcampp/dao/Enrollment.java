package org.tech.bootcampp.dao;

 
import jakarta.persistence.*;

@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;   // who purchased
    private Long courseId;      // which course
    private String paymentId;   // Razorpay payment id
    private String orderId;     // Razorpay order id

    // Default Constructor
    public Enrollment() {
    }

    // All-Args Constructor
    public Enrollment(Long id, String userEmail, Long courseId, String paymentId, String orderId) {
        this.id = id;
        this.userEmail = userEmail;
        this.courseId = courseId;
        this.paymentId = paymentId;
        this.orderId = orderId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    // Optional: toString()
    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", userEmail='" + userEmail + '\'' +
                ", courseId=" + courseId +
                ", paymentId='" + paymentId + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
