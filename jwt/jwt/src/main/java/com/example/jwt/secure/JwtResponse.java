package com.example.jwt.secure;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private static final Long jti = -2550185165626007488L;
    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public static Long getJti() {
        return jti;
    }

    public String getToken() {
        return token;
    }
}
