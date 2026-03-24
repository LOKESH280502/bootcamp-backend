package org.tech.bootcampp.controller.copy;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.tech.bootcampp.dto.RegisterRequest;
import org.tech.bootcampp.repository.UserRepository;
import org.tech.bootcampp.dao.User;
import org.tech.bootcampp.dto.LoginRequest;
import org.tech.bootcampp.dto.LoginResponse;
import org.tech.bootcampp.service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173") // frontend
public class AuthController {

    private final AuthService service;
    private final UserRepository userRepository;
    public AuthController(AuthService service, UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository; // ✅ inject via constructor

    }

    @PostMapping("/register")
    public LoginResponse register(@RequestBody RegisterRequest request) {
        return service.register(request);
    }

@GetMapping("/me")
public ResponseEntity<?> getMe() {  // ✅ remove Principal parameter
    // Get email directly from SecurityContext
    String email = SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();

    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));

    return ResponseEntity.ok(user);
}

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
    	 System.out.println("LOGIN HIT: " + request.getEmail());
        return service.login(
                request.getEmail(),
                request.getPassword()
        );
    }
}
