package com.example.Todo.Service;

import com.example.Todo.Service.AddressEntries;
import org.springframework.web.client.RestTemplate;

public class AddressData {
    private RestTemplate restTemplate;
    public AddressData(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public AddressEntries fetchDataFromApi() {
        String apiUrl = "https://random-data-api.com/api/v2/addresses";
        AddressEntries addressEntries = restTemplate.getForObject(apiUrl, AddressEntries.class);
        return addressEntries;
    }
}

