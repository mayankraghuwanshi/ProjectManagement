package com.mayank.ProjectManagement.Security;

public class SecurityConstants {
    public static final String SIGN_UP_URL = "/api/user/**";
    public static final String H2_CONSOLE   = "/h2-console/**";
    public static final String SECRET = "SECRET";
    public static final String TOKEN_PREFIX = "bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRE_TIME = 10_00;

}
