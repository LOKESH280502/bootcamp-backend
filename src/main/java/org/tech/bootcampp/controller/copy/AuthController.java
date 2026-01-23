package org.tech.bootcampp.controller.copy;

import org.springframework.web.bind.annotation.*;
import org.tech.bootcampp.dto.RegisterRequest;
import org.tech.bootcampp.dto.LoginRequest;
import org.tech.bootcampp.dto.LoginResponse;
import org.tech.bootcampp.service.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173") // frontend
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public LoginResponse register(@RequestBody RegisterRequest request) {
        return service.register(request);
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
