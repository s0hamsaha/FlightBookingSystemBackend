package com.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.apigateway.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private RouteValidator routeValidator;

	@Autowired
	private JwtService jwtService;

	public AuthenticationFilter() {
		super(Config.class);
	}

	public static class Config {

	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			if (routeValidator.isSecured.test(exchange.getRequest())) {
				System.out.println("==================================================");
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("Missing Authorization Header");
				}
				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if (authHeader != null && authHeader.startsWith("Bearer ")) {
					authHeader = authHeader.substring(7);
				}
				try {
					Jws<Claims> jws =  jwtService.validateToken(authHeader);
					String role = jws.getBody().get("role", String.class); 
					if (!routeValidator.isRoleAllowed(exchange.getRequest().getURI().getPath(), role)) {
                        throw new RuntimeException("Unauthorized Access: Insufficient Role");
                    }

				} catch (Exception e) {
					System.out.println("Invalid Access...!");
					throw new RuntimeException("Unauthorized Access");
				}
			}
			return chain.filter(exchange);
		});
	}
}
