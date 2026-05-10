package com.echoalert.backend.util;

public final class JwtConstants {

    private JwtConstants() {
        // Prevent object creation
    }

    // Secret Key (minimum 32 chars recommended)
    public static final String SECRET_KEY =
            "echoalertsupersecurejwtsecretkey12345";

    // Token Expiry Times
    public static final long ACCESS_TOKEN_EXPIRATION =
            1000L * 60 * 60 * 24; // 24 Hours

    public static final long REFRESH_TOKEN_EXPIRATION =
            1000L * 60 * 60 * 24 * 7; // 7 Days

    // Header Info
    public static final String HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";

    // Claims Keys
    public static final String ROLE = "role";
    public static final String EMAIL = "email";
}