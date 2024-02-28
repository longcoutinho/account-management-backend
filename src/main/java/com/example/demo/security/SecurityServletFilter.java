package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SecurityServletFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException {
        System.out.println(request.getRequestURI());
        if (request.getRequestURI().equals("/user/login")) {
            chain.doFilter(request, response); // (4)
            return;
        }
//        UsernamePasswordToken token = extractUsernameAndPasswordFrom(request);  // (1)
//        String token = extractUsernameAndPasswordFrom(request);  // (1)

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401.
        return;

//        if (notAuthenticated(token)) {  // (2)
//            // either no or wrong username/password
//            // unfortunately the HTTP status code is called "unauthorized", instead of "unauthenticated"
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401.
//            return;
//        }
//
//        if (notAuthorized(token, request)) { // (3)
//            // you are logged in, but don't have the proper rights
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // HTTP 403
//            return;
//        }

        // allow the HttpRequest to go to Spring's DispatcherServlet
        // and @RestControllers/@Controllers.
//        chain.doFilter(request, response); // (4)
    }

//    private UsernamePasswordToken extractUsernameAndPasswordFrom(HttpServletRequest request) {
//        // Either try and read in a Basic Auth HTTP Header, which comes in the form of user:password
//        // Or try and find form login request parameters or POST bodies, i.e. "username=me" & "password="myPass"
//        return checkVariousLoginOptions(request);
//    }
//
//    private boolean notAuthenticated(UsernamePasswordToken token) {
//        // compare the token with what you have in your database...or in-memory...or in LDAP...
//        return false;
//    }
//
//    private boolean notAuthorized(UsernamePasswordToken token, HttpServletRequest request) {
//        // check if currently authenticated user has the permission/role to access this request's /URI
//        // e.g. /admin needs a ROLE_ADMIN , /callcenter needs ROLE_CALLCENTER, etc.
//        return false;
//    }
}