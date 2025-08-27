package com.merkana.web.util;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;

public final class HttpUtils {

    private HttpUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getClientIp(ServletRequest request) {
        if (!(request instanceof HttpServletRequest httpRequest)) {
            return null;
        }

        String forwardedFor = httpRequest.getHeader(CustomHeaders.X_FORWARDED_FOR);

        String clientIp;
        if (forwardedFor != null && !forwardedFor.isEmpty()) {
            clientIp = forwardedFor.split(",")[0].trim();
        } else {
            clientIp = httpRequest.getRemoteAddr();
        }

        return clientIp;
    }

    public static String getHeader(ServletRequest request, String headerName) {
        if (!(request instanceof HttpServletRequest httpRequest)) {
            return null;
        }

        return httpRequest.getHeader(headerName);
    }

}
