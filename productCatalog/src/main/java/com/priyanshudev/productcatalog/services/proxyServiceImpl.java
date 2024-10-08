package com.priyanshudev.productcatalog.services;

import com.priyanshudev.productcatalog.proxyServer.Client.fakeStoreApi.FakeStoreClient;
import com.priyanshudev.productcatalog.proxyServer.Client.fakeStoreApi.ProxyCreateProductDto;
import com.priyanshudev.productcatalog.proxyServer.Client.fakeStoreApi.ProxyProductDto;
import com.priyanshudev.productcatalog.exceptions.NotFoundException;
import com.priyanshudev.productcatalog.models.Category;
import com.priyanshudev.productcatalog.models.Product;
import jakarta.annotation.Nullable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class proxyServiceImpl implements ProductService {
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;

    public proxyServiceImpl(RestTemplateBuilder restTemplateBuilder,
                            FakeStoreClient fakeStoreClient) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
    }

    public <T> ResponseEntity<T> requestForEntity(String url,HttpMethod method, @Nullable Object request,
                                               Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return (restTemplate.execute(url, method, requestCallback, responseExtractor, uriVariables));

    }

    /*
    * Return a product object with all the details of fetched product
    * Id of category will be null, but name would be correct
    * */
    public Optional<Product> getSingleProduct(Long id) throws NotFoundException {
        ProxyProductDto productDto = null;
        try {
            productDto = fakeStoreClient.getSingleProduct(id);
            
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RestClientException(e.getMessage());
        }

        if (productDto == null) {
            return Optional.empty();
        }
        return Optional.of(convertProxyProductDtoToProduct(productDto));
        
    }

    @Override
    public List<Product> getAllProducts() throws NotFoundException {
        List<ProxyProductDto> response = fakeStoreClient.getAllProducts();
        List<Product> products = new ArrayList<>();
        for (ProxyProductDto dto : response) {
            products.add(convertProxyProductDtoToProduct(dto));
        }
        return products;
    }

    private Product convertProxyProductDtoToProduct(ProxyProductDto dto) throws NotFoundException {
        if (dto == null) {
            throw new NotFoundException(" No valid data to convert ProxyProduct to Our Product");
        }
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPrice(product.getPrice());
//        product.setImageUrl(dto.getImages());
        Category category = new Category();
        category.setId(Long.valueOf(dto.getCategory().getId()));
        category.setName(dto.getCategory().getName());
        String categoryCreationDate = dto.getCategory().getCreationAt();
        String categoryUpdatedDate = dto.getCategory().getUpdatedAt();
        LocalDateTime localDateTimeCategoryCreationAt = LocalDateTime.parse(categoryCreationDate.substring(0, categoryCreationDate.length() - 1), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime localDateTimeCategoryUpdateAt = LocalDateTime.parse(categoryUpdatedDate.substring(0, categoryUpdatedDate.length()-1), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        category.setCreationAt(LocalDate.from(localDateTimeCategoryCreationAt));
        category.setUpdatedAt(LocalDate.from(localDateTimeCategoryUpdateAt));
        product.setCategory(category);
        return product;
    }


    @Override
    public Product addNewProduct(ProxyCreateProductDto product) throws NotFoundException {

        ProxyProductDto productDto = fakeStoreClient.addNewProduct(product);
        return convertProxyProductDtoToProduct(productDto);

    }

    @Override
    public Product updateProduct(Long id, ProxyProductDto product) throws NotFoundException {
        ProxyProductDto updatedProduct = fakeStoreClient.updateProduct(id, product);
        return convertProxyProductDtoToProduct(updatedProduct);
    }

    @Override
    public boolean deleteProduct(Long id) {
        boolean isDeleted = false;
        try {
            isDeleted = fakeStoreClient.deleteProduct(id);

        } catch (RestClientException e) {
            System.err.println("Error occurred while deleting the product with id "+id+" "+e.getMessage());
        }
        return isDeleted;
    }
    }

