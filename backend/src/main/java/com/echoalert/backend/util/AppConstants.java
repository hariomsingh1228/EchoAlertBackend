package com.echoalert.backend.util;

public final class AppConstants {

    private AppConstants() {
        // Prevent object creation
    }

    // Application Info
    public static final String APP_NAME = "EchoAlert";
    public static final String APP_VERSION = "1.0.0";

    // Default Pagination
    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 10;

    // Sorting
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_SORT_DIRECTION = "asc";

    // User Roles
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";

    // Alert Status
    public static final String ALERT_ACTIVE = "ACTIVE";
    public static final String ALERT_CLOSED = "CLOSED";

    // Notification Status
    public static final String NOTIFICATION_PENDING = "PENDING";
    public static final String NOTIFICATION_SENT = "SENT";
    public static final String NOTIFICATION_FAILED = "FAILED";

    // Priorities
    public static final String PRIORITY_LOW = "LOW";
    public static final String PRIORITY_MEDIUM = "MEDIUM";
    public static final String PRIORITY_HIGH = "HIGH";
    public static final String PRIORITY_CRITICAL = "CRITICAL";

    // API Base Path
    public static final String API_BASE = "/api";
}