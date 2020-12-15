package com.example.demo.service;

import com.example.demo.model.SiteDTO;
import com.example.demo.model.SitesDTO;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Component
public class StackExchangeClient {
    HttpClient httpClient = HttpClientBuilder.create().build();
    ClientHttpRequestFactory factory =  new HttpComponentsClientHttpRequestFactory(httpClient);
    private RestTemplate restTemplate = new RestTemplate(factory);

    public List<SiteDTO> getSites() {
        String url = "https://api.stackexchange.com/2.2/sites?filter=!Fn4IB7S7T4v-QOAN.yAAPtdnZX";
        SitesDTO responce = null;
        try {
            responce = restTemplate.getForObject(new URI(url), SitesDTO.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return responce.getItems();
    }

}
