 package org.tech.bootcampp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tech.bootcampp.dao.User;
import org.tech.bootcampp.dto.LoginResponse;
import org.tech.bootcampp.dto.RegisterRequest;
import org.tech.bootcampp.repository.UserRepository;
import org.tech.bootcampp.security.JwtUtil;

@Service
 public class AuthServiceImpl implements AuthService {

     private final UserRepository userRepository ;
     private final JwtUtil jwtUtil;
     @Value("${publisher.secret.code}")  // ✅ reads from application.properties
     private String publisherSecretCode;
     public AuthServiceImpl(UserRepository userRepository, JwtUtil jwtUtil) {
         this.userRepository = userRepository;
         this.jwtUtil = jwtUtil;
     }

     @Override
     public LoginResponse login(String email, String password) {

         User user = userRepository.findByEmail(email)
                 .orElseThrow(() -> new RuntimeException("User not found"));

         if (!user.getPassword().equals(password)) {
             throw new RuntimeException("Invalid credentials");
         }

         String token = jwtUtil.generateToken(
                 user.getEmail(),
                 user.getRole() 
         );

         return new LoginResponse(token, user.getRole() );
     }

     @Override
     public LoginResponse register(RegisterRequest request) {
    	  System.out.println("ROLE: " + request.getRole());
    	    System.out.println("CODE RECEIVED: " + request.getPublisherCode()); // ✅
    	    System.out.println("SECRET CODE: " + publisherSecretCode); 
    	 String role = "user"; // default always user
         if ("publisher".equalsIgnoreCase(request.getRole())) {
             if (request.getPublisherCode() == null ||
                 !request.getPublisherCode().equals(publisherSecretCode)) {
                 throw new RuntimeException("Invalid publisher code");
             }
             role = "publisher";
         }
         User user = new User();
         user.setEmail(request.getEmail());
         user.setName(request.getName());
         user.setAvatar(request.getAvatar());
         user.setAvatar(request.getAvatar());
         user.setPassword(request.getPassword());
         user.setRole(role);

         userRepository.save(user);

         String token = jwtUtil.generateToken(
                 user.getEmail(),
                 user.getRole() 
         );

         return new LoginResponse(token, user.getRole());
     }
 }
