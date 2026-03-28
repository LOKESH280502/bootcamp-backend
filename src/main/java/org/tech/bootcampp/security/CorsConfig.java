package org.tech.bootcampp.security;

 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.*;

import java.util.List;

@Configuration
public class CorsConfig {

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//
//        CorsConfiguration config = new CorsConfiguration();
 
//        config.setAllowedOrigins(List.of("http://localhost:5173"));
 
//        config.setAllowedMethods(List.of(
//                "GET", "POST", "PUT", "DELETE", "OPTIONS"
//        ));
 
//        config.setAllowedHeaders(List.of(
//                "Authorization", "Content-Type"
//        ));
 
////        config.setExposedHeaders(List.of("Authorization"));
//
//        config.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source =
//                new UrlBasedCorsConfigurationSource();
//
//        source.registerCorsConfiguration("/**", config);
//
//        return source;
//    }
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowedOrigins(List.of(
	        "http://localhost:5173",
	        "http://localhost:5174",
	        "https://bootcamp-frontend-rho.vercel.app/"  // ← add after you get Vercel URL
	    ));
	    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    config.setAllowedHeaders(List.of(
              "Authorization", "Content-Type"
      ));
//	    config.setAllowedHeaders(List.of("*"));
	    config.setAllowCredentials(true);

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", config);
	    return source;
	}
}
