package com.example.ocs2.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
public class ResourceServerProxy {
    public static final String AUTHORIZATION = "Authorization";
    @Autowired
    TokenManager tokenManager;

    //a method to call demo endpoint
    public String callDemo() {
        String token;
        if(checkExpired(TokenManager.available_token)){

        }
                tokenManager.getAccessToken();
        String url = "http://localhost:9091/user";

        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION, "Bearer " + token);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        var response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        return response.getBody();
    }

    public boolean checkExpired(String access_token) {
        DecodedJWT decodedJWT = JWT.decode(access_token);
        if (decodedJWT.getExpiresAt().before(new Date())) {
            return true;
        }
        return false;
    }
}
