package com.apigateway.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {
	public static final List<String> openApiEndpoints = List.of("/auth/registerUser", "/auth/login", "/eureka",
			"/search/**", "/flights/swagger-ui/**", "/search/swagger-ui/**", "/booking/swagger-ui/**",
			"/checkin/swagger-ui/**", "/flights/v3/api-docs", "/search/v3/api-docs", "/booking/v3/api-docs",
			"/checkin/v3/api-docs","/flights");
	/*
	 * private static final Map<String, List<String>> allowedRoles = new
	 * HashMap<>();
	 * 
	 * static { allowedRoles.put("/flights/list", List.of("ADMIN"));//ok7
	 * allowedRoles.put("/booking", List.of("ADMIN"));//ok
	 * 
	 * allowedRoles.put("/booking/getByBookingId/2", List.of("USER","ADMIN"));
	 * 
	 * allowedRoles.put("/checkin/getCheckinDetails", List.of("ADMIN","USER"));//ok
	 * 
	 * allowedRoles.put("/checkin/getCheckinDetailsByCheckInId/2",
	 * List.of("USER","ADMIN"));
	 * 
	 * allowedRoles.put("/auth/getToken", List.of("ADMIN", "USER"));
	 * allowedRoles.put("/eureka", List.of("ADMIN", "USER"));
	 * 
	 * }
	 */

	public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream()
			.noneMatch(uri -> request.getURI().getPath().contains(uri));

	public boolean isRoleAllowed(String endpoint, String role) {
		if (role.equals("ADMIN")) {
			return true;
		} else if (role.equals("USER")) {
			return (endpoint.startsWith("/booking/getByBookingId")
					|| endpoint.startsWith("/checkin/getCheckinDetailsByCheckInId")||endpoint.startsWith("/flights")||endpoint.startsWith("/booking/getBookingsByEmail")
					|| endpoint.startsWith("/booking/newbooking")||endpoint.startsWith("/booking/getByBookingId")||endpoint.startsWith("/checkin/createCheckin")
					|| endpoint.startsWith("/checkin/getCheckinDetailsByPassengerId"));
		}
		return false;
	}
}
