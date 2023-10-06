package com.platform.configs;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true) // Enable @Secured annotation
@RequiredArgsConstructor
@ConditionalOnProperty(name = "keycloak.enabled", havingValue = "true", matchIfMissing = true)
public class KeycloakConfig {
    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity = httpSecurity.cors().and().csrf().disable();

        // Set session management
        httpSecurity = httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

        // Set unauthorized req exception handle
        httpSecurity = httpSecurity.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }).and();

        // Auth server
        httpSecurity.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthConverter);

        // set permission on endpoints
//        httpSecurity.authorizeHttpRequests()
//                // Allow swagger request
//                .requestMatchers("/v2/api-docs", "v3/api-docs/**", "/swagger-ui/**", "/swagger-ui", "/swagger-ui.html", "/swagger-resources/**", "/actuator/**")
//                .permitAll()
//                // req authenticate for apis
//                .requestMatchers("/**")
//                .authenticated();

        // Return
        return httpSecurity.build();
    }
}