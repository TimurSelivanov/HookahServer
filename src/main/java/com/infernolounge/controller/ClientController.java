package com.infernolounge.controller;

import com.infernolounge.service.CrmService;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientController {

    private final CrmService crmService;
    private final RestTemplate restTemplate;

    @Value("${crmBaseUrl}")
    private String crmBaseUrl;

    public ClientController(CrmService crmService, RestTemplate restTemplate) {
        this.crmService = crmService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/clients")
    public ResponseEntity<String> getClients(@RequestParam(defaultValue = "1") int page,
                                             @RequestParam(defaultValue = "20") int perPage) {
        //get the header token
        //TODO make secure implementation of token storing
        String bearerToken = crmService.getBearerToken();
        System.out.println("Token passed " + bearerToken);
        //create httpHeaders entity
        HttpHeaders httpHeaders = new HttpHeaders();
        //set token
        httpHeaders.setBearerAuth(bearerToken);

        //create requestEntity and pass httpHeaders in
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        //make GET request to CRM API to get clients list
        ResponseEntity<String> response = restTemplate.exchange(crmBaseUrl + "/api/clients?page=" + page + "&per-page=" + perPage,
                HttpMethod.GET, requestEntity, String.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.status(response.getStatusCode()).body("Error getting clients");
        }
    }
}
