package com.example.expenseitapi.entities;

public class JwtResponse {

    private final String jwtToken;

	public String getJwtToken() {
		return jwtToken;
	}

	public JwtResponse(String pJwtToken) {
        this.jwtToken = pJwtToken;
    }
}
