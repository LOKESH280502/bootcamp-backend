package org.tech.bootcampp.service;

//import org.tech.bootcampp.dto.LoginRequest;
import org.tech.bootcampp.dto.LoginResponse;
import org.tech.bootcampp.dto.RegisterRequest;

public interface AuthService {

//    void register(RegisterRequest request);
//
//    String login(LoginRequest request);
    
//    String register(RegisterRequest request);
//
//    String login(String email, String password);
	 LoginResponse register(RegisterRequest request);
	    LoginResponse login(String email, String password);
}
