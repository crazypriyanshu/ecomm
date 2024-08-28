package com.priyanshudev.productcatalog.proxyServer.Client.fakeStoreApi;

import com.priyanshudev.productcatalog.proxyServer.exceptions.NotFoundException;
import com.priyanshudev.productcatalog.proxyServer.models.Category;
import com.priyanshudev.productcatalog.proxyServer.models.Product;
import jakarta.annotation.Nullable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<ProxyProductDto> getAllProducts() throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProxyProductDto[]> response = restTemplate.getForEntity(
                "https://api.escuelajs.co/api/v1/products",
                ProxyProductDto[].class
        );
        return Arrays.asList(response.getBody());
    }

    public ProxyProductDto getSingleProduct(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProxyProductDto> response = null;
        try {
            response = restTemplate.getForEntity(
                    "https://api.escuelajs.co/api/v1/products/{id}",
                    ProxyProductDto.class, id);

        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException("SOmething is wrong from FakeStore client");
        }

         return response.getBody();
    }

    public ProxyProductDto addNewProduct(ProxyCreateProductDto product) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProxyProductDto> response = restTemplate.postForEntity(
                "https://api.escuelajs.co/api/v1/products",
                product, ProxyProductDto.class);
        ProxyProductDto productDto = response.getBody();

        return productDto;

    }

    public ProxyProductDto updateProduct(Long id, ProxyProductDto product) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProxyProductDto> response =  requestForEntity(
                "https://api.escuelajs.co/api/v1/products/{id}",
                HttpMethod.PUT,
                product,
                ProxyProductDto.class,
                id
        );
        return response.getBody();
    }

    public boolean deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        try {
            restTemplate.delete("http://api.escuelajs.co/api/v1/products/{id}", id);
            return true;
        } catch (RestClientException e) {
            System.err.println("Error occurred while deleting the product with id "+id+" "+e.getMessage());
            return false;
        }
    }




    public <T> ResponseEntity<T> requestForEntity(String url,HttpMethod method, @Nullable Object request,
                                                  Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return (restTemplate.execute(url, method, requestCallback, responseExtractor, uriVariables));

    }


    }
