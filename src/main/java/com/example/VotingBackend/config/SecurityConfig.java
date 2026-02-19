package com.example.VotingBackend.config;

import com.example.VotingBackend.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserService userService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userService = userService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // ❌ Disable CSRF (we use JWT)
        http.csrf(csrf -> csrf.disable());

        // ⭐ GLOBAL CORS CONFIG (FINAL)
        http.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration corsConfig = new CorsConfiguration();

            // Allow Localhost + Vercel domains
            corsConfig.setAllowedOriginPatterns(java.util.List.of(
                    "http://localhost:4200",
                    "https://*.vercel.app"
            ));

            corsConfig.setAllowedMethods(java.util.List.of(
                    "GET", "POST", "PUT", "DELETE", "OPTIONS"
            ));

            corsConfig.setAllowedHeaders(java.util.List.of("*"));
            corsConfig.setExposedHeaders(java.util.List.of("Authorization"));

            // JWT uses headers → credentials not needed
            corsConfig.setAllowCredentials(false);

            return corsConfig;
        }));


        // ⭐ SECURITY RULES
        http.authorizeHttpRequests(auth -> auth

                // VERY IMPORTANT → allow browser preflight requests
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // PUBLIC APIs (no login required)
                .requestMatchers(
                        "/api/auth/**",
                        "/api/candidates",
                        "/api/parties",
                        "/api/results/**"
                ).permitAll()

                // 🔒 All other APIs require JWT
                .anyRequest().authenticated()
        );

        // Stateless session (JWT)
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // Authentication provider
        http.authenticationProvider(authenticationProvider());

        // Add JWT filter before Spring login filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ⭐ Authentication Provider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(userService.getPasswordEncoder());
        return provider;
    }

    // ⭐ Authentication Manager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}