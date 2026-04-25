package com.kingree.api_gateway.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

        @Bean
        SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
                http
                                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                                .cors(cors -> cors.configurationSource(corsConfigrationSource()))
                                .authorizeExchange(exchanges -> exchanges

                                                // Public endpoints
                                                .pathMatchers("/auth/**").permitAll()
                                                .pathMatchers("/api/notification/ws/**").permitAll()

                                                // Salon owner only
                                                .pathMatchers(
                                                                "/api/categories/salon-owner/**",
                                                                "/api/notifications/salon-owner/**",
                                                                "/api/service-offering/salon-owner/**",
                                                                "/api/salons/salon-owner/**")
                                                .hasRole("SALON_OWNER")

                                                // Customer, salon owner, admin
                                                .pathMatchers(
                                                                "/api/salons/**",
                                                                "/api/categories/**",
                                                                "/api/notifications/**",
                                                                "/api/bookings/**",
                                                                "/api/notifications/**",
                                                                "/api/payments/**",
                                                                "/api/service-offering/**",
                                                                "/api/users/**",
                                                                "/api/reviews/**")
                                                .hasAnyRole("CUSTOMER", "SALON_OWNER", "ADMIN")

                                                // All other requests must be authenticated
                                                .anyExchange().authenticated())
                                .oauth2ResourceServer(oAuth2 -> oAuth2
                                                .jwt(jwt -> jwt.jwtAuthenticationConverter(
                                                                grantAuthoritiesExtractor())));

                return http.build();
        }

        private CorsConfigurationSource corsConfigrationSource() {

                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:5170"));
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
                configuration.setExposedHeaders(Collections.singletonList("*"));
                configuration.setExposedHeaders(Collections.singletonList("Authorization"));
                configuration.setAllowCredentials(true);
                configuration.setMaxAge(3600L);

                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);

                return source;

        }

        private Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> grantAuthoritiesExtractor() {
                JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
                jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakConverter());

                return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
        }
}