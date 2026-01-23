 package org.tech.bootcampp.service;

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

         User user = new User();
         user.setEmail(request.getEmail());
         user.setPassword(request.getPassword());
         user.setRole(request.getRole());

         userRepository.save(user);

         String token = jwtUtil.generateToken(
                 user.getEmail(),
                 user.getRole() 
         );

         return new LoginResponse(token, user.getRole());
     }
 }
