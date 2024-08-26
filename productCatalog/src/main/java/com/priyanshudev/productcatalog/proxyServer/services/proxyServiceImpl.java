package com.priyanshudev.productcatalog.proxyServer.services;

import com.priyanshudev.productcatalog.proxyServer.dtos.ProxyCreateProductDto;
import com.priyanshudev.productcatalog.proxyServer.dtos.ProxyProductDto;
import com.priyanshudev.productcatalog.proxyServer.models.Category;
import com.priyanshudev.productcatalog.proxyServer.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class proxyServiceImpl implements ProductService {
    private RestTemplateBuilder restTemplateBuilder;

    public proxyServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }


    /*
    * Return a product object with all the details of fetched product
    * Id of category will be null, but name would be correct
    * */
    public Product getSingleProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        // getForEntity - (url, returnType, paramsInUrl) ->
        ResponseEntity<ProxyProductDto> response = restTemplate.getForEntity("https://api.escuelajs.co/api/v1/products/{id}", ProxyProductDto.class, id);
        ProxyProductDto productDto = response.getBody();
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImages());
        product.setProductId(productDto.getId());
        product.setCreatedAt(productDto.getCreationAt());
        product.setLastUpdatedAt(productDto.getUpdatedAt());
        Category category = new Category();
        category.setName(productDto.getCategory().getName());
        category.setId(productDto.getCategory().getId());
        category.setCreatedAt(productDto.getCategory().getCreationAt());
        category.setLastUpdatedAt(productDto.getCategory().getUpdatedAt());
        category.setImage(productDto.getCategory().getImage());
        product.setCategory(category);
        return product;
    }


    @Override
    public ProxyCreateProductDto addNewProduct(ProxyCreateProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProxyCreateProductDto> response = restTemplate.postForEntity("https://api.escuelajs.co/api/v1/products", product, ProxyCreateProductDto.class);
        ProxyCreateProductDto productDto = response.getBody();
        Product newProduct = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImages(productDto.getImages());
        product.setId(productDto.getId());
        product.setCategoryName(productDto.getCategoryName());
        return product;

    }


}
