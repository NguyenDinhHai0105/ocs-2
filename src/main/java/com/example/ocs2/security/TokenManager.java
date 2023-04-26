package com.example.ocs2.security;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
public class TokenManager {

    private RestTemplate restTemplate;
    public static String available_token;

    public String getAccessToken() {

        String url = "http://localhost:9090/realms/ocs-spring-application/protocol/openid-connect/token";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", "admin");
        map.add("password", "1");
        map.add("client_id", "ocs-keycloak");
        map.add("grant_type", "client-credentials");
        map.add("client_secret", "tU8vyo3XcolsKFAFfizJemdjfJVD6Y73");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, httpHeaders);
        ResponseEntity<String> response = null;
        response = restTemplate.postForEntity(url, request, String.class);
        String responseBody = response.getBody();
        JSONObject jsonObject = new JSONObject(responseBody);
        String access_token = jsonObject.getString("access_token");
        available_token = access_token;
        return access_token;
    }
}