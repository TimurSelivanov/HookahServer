package com.infernolounge.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CrmService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;
    private String bearerToken;

//    @Value("${crmBaseUrl}")
    private String crmBaseUrl = "https://infernolounge3.hookah.work/api/login";
//    @Value("${username}")
    private String username = "ninon11197@vinthao.com";
//    @Value("${password}")
    private String password = "GaaKfAXb";

    public String authenticateAndGetBearerToken() {
        System.out.println("Authentication method called");

        // Set up the Basic Authentication header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(username, password);
        // Set up request as the HttpEntity
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);
        // Make a POST request to the CRM API to authenticate
        ResponseEntity<String> response = restTemplate.exchange(
                crmBaseUrl,
                HttpMethod.POST,
                request,
                String.class);
        //check response status
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Got 200 response from the crm");
            // Get response body
            String responseBody = response.getBody();
            // Parse Bearer token from the response body
            String bearerToken = parseBearerTokenFromJson(responseBody);
            return bearerToken;
        } else {
            throw new RuntimeException("Authentication failed. Status code: " + response.getStatusCode());
        }
    }

    private String parseBearerTokenFromJson(String responseBody) {
        Map<String, Object> jsonMap = null;
        try {
            jsonMap = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Token can not be obtained");
        }
        return (String) jsonMap.get("auth_token");
    }

    public String getBearerToken() {
        if(bearerToken == null) {
            return bearerToken = authenticateAndGetBearerToken();
        } else {
            return bearerToken;
        }
    }
}
