package org.tech.bootcampp.controller.copy;

 
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import java.security.Principal;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tech.bootcampp.dao.Course;
import org.tech.bootcampp.dao.Enrollment;
import org.tech.bootcampp.repository.CourseRepository;
import org.tech.bootcampp.repository.EnrollmentRepository;
 

@RestController
@RequestMapping("/payment")
@CrossOrigin
public class PaymentController {

    private final CourseRepository courseRepo;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public PaymentController(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }
    @GetMapping("/enrollment/check/{courseId}")
    public boolean checkEnrollment(@PathVariable Long courseId, Principal principal) {
        return enrollmentRepository.existsByUserEmailAndCourseId(principal.getName(), courseId);
    }


    @PostMapping("/create-order/{courseId}")
    public ResponseEntity<?> createOrder(@PathVariable Long courseId) throws Exception {

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        RazorpayClient client = new RazorpayClient(
                "rzp_test_SBf4t4NhPxWXz3",
                "o17hm0TgJ7QN38EUNKbbbawb"
        );

        JSONObject options = new JSONObject();
        options.put("amount", course.getPrice() * 100);
        options.put("currency", "INR");
        options.put("receipt", "course_" + courseId);

        Order order = client.orders.create(options);

        return ResponseEntity.ok(order.toString());
    }
    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String, String> payload,
                                           Principal principal) {
     

        String paymentId = payload.get("paymentId");
        String orderId = payload.get("orderId");
        Long courseId = Long.parseLong(payload.get("courseId"));

        String userEmail = principal.getName();

        Enrollment enrollment = new Enrollment();
        enrollment.setUserEmail(userEmail);
        enrollment.setCourseId(courseId);
        enrollment.setPaymentId(paymentId);
        enrollment.setOrderId(orderId);
        System.out.println("EMAIL: " + userEmail);
        System.out.println("COURSE ID: " + courseId);

        enrollmentRepository.save(enrollment);

        return ResponseEntity.ok("Enrollment Successful");
    }
    
    
    @PostMapping("/verify-payment")
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String, String> data) {

        try {
            String paymentId = data.get("razorpay_payment_id");
            String orderId = data.get("razorpay_order_id");
            String signature = data.get("razorpay_signature");

            JSONObject options = new JSONObject();
            options.put("razorpay_payment_id", paymentId);
            options.put("razorpay_order_id", orderId);
            options.put("razorpay_signature", signature);

            boolean isValid = com.razorpay.Utils.verifyPaymentSignature(
                    options,
                    "RAZORPAY_SECRET"
            );

            if (isValid) {
                // TODO: Save enrollment here
                return ResponseEntity.ok("Payment verified & enrollment successful");
            }

            return ResponseEntity.status(400).body("Payment verification failed");

        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}

