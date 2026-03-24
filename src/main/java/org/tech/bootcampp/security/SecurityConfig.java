package org.tech.bootcampp.security;
 

import org.springframework.context.annotation.*;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }
    
  
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .csrf(AbstractHttpConfigurer::disable)
            .cors(Customizer.withDefaults())
//            .httpBasic(AbstractHttpConfigurer::disable)
//            .formLogin(AbstractHttpConfigurer::disable)
            // 🔥 THIS IS THE MISSING LINE
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .securityContext(sc -> sc.requireExplicitSave(false))
            .httpBasic(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .logout(AbstractHttpConfigurer::disable)

            .authorizeHttpRequests(auth -> auth
            	    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            	    .requestMatchers("/auth/**").permitAll()
            	    .requestMatchers(HttpMethod.GET, "/api/v1/bootcamps/**").permitAll()
            	    .requestMatchers(HttpMethod.GET, "/api/v1/bootcamps/*/courses").permitAll()
            	    .requestMatchers(HttpMethod.POST, "/api/v1/bootcamps/*/courses").hasRole("PUBLISHER")
            	    .requestMatchers(HttpMethod.PUT, "/api/v1/bootcamps/*/courses/**").hasRole("PUBLISHER")
            	    .requestMatchers(HttpMethod.DELETE, "/api/v1/bootcamps/*/courses/**").hasRole("PUBLISHER")

            	    .requestMatchers(HttpMethod.POST, "/api/v1/bootcamps/**").hasRole("PUBLISHER")
            	    .requestMatchers(HttpMethod.PUT, "/api/v1/bootcamps/**").hasRole("PUBLISHER")
            	    .requestMatchers(HttpMethod.DELETE, "/api/v1/bootcamps/**").hasRole("PUBLISHER")
            	    .requestMatchers("/enrollment/**").authenticated()
            	    .anyRequest().authenticated()
            	)

//            .addFilterBefore(jwtFilter, org.springframework.security.web.authentication.AnonymousAuthenticationFilter.class);
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
        
    }

}
