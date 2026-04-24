package com.kingree.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator gateRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()

				.route("user-service", r -> r
						.path("/auth/**", "/api/admin/users/**", "/api/users/**", "/users/**")
						.uri("lb://user-service"))

				.route("booking-service", r -> r
						.path("/api/bookings/**")
						.uri("lb://booking-service"))

				.route("salon-service", r -> r
						.path("/api/salons/**")
						.uri("lb://salon-service"))

				.route("service-offering", r -> r
						.path("/api/service-offering/**")
						.uri("lb://service-offering"))

				.route("payment-service", r -> r
						.path("/api/payments/**")
						.uri("lb://payment-service"))

				.route("category-service", r -> r
						.path("/api/categories/**")
						.uri("lb://category-service"))

				.route("notifications", r -> r
						.path("/api/notifications/**")
						.uri("lb://notifications"))

				.route("reviews", r -> r
						.path("/api/reviews/**")
						.uri("lb://reviews"))

				.build();
	}

}
