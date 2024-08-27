package com.priyanshudev.productcatalog.proxyServer.services;

import com.priyanshudev.productcatalog.proxyServer.dtos.ProxyCreateProductDto;
import com.priyanshudev.productcatalog.proxyServer.dtos.ProxyProductDto;
import com.priyanshudev.productcatalog.proxyServer.exceptions.NotFoundException;
import com.priyanshudev.productcatalog.proxyServer.models.Category;
import com.priyanshudev.productcatalog.proxyServer.models.Product;
import jakarta.annotation.Nullable;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class proxyServiceImpl implements ProductService {
    private RestTemplateBuilder restTemplateBuilder;

    public proxyServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
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
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<ProxyProductDto> response = restTemplate.getForEntity(
                    "https://api.escuelajs.co/api/v1/products/{id}",
                    ProxyProductDto.class, id);
            productDto = response.getBody();
            
        } catch (HttpClientErrorException.BadRequest e) {
            throw new RestClientException("Bad Request: "+ e.getMessage());
        }
        catch (HttpClientErrorException.NotFound e) {
            System.out.println("Not Found: "+ e.getMessage());
            return null;
        }
        catch (HttpClientErrorException e) {
            System.out.println("Client Error: "+ e.getMessage());
            throw new RestClientException("Internal Server Error: "+ e.getMessage());
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
        Date date = null;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProxyProductDto[]> response = restTemplate.getForEntity(
                "https://api.escuelajs.co/api/v1/products",
                ProxyProductDto[].class
        );
        List<Product> products = new ArrayList<>();
        for (ProxyProductDto dto : response.getBody()) {
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
        product.setImageUrl(dto.getImages());
        product.setProductId(dto.getId());
        Category category = new Category();
        category.setId(Long.valueOf(dto.getCategory().getId()));
        category.setName(dto.getCategory().getName());
        category.setImage(dto.getCategory().getImage());
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
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProxyProductDto> response = restTemplate.postForEntity(
                "https://api.escuelajs.co/api/v1/products",
                product, ProxyProductDto.class);
        ProxyProductDto productDto = response.getBody();

        return convertProxyProductDtoToProduct(productDto);

    }

    @Override
    public Product updateProduct(Long id, ProxyProductDto product) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProxyProductDto> response =  requestForEntity(
                "https://api.escuelajs.co/api/v1/products/{id}",
                HttpMethod.PUT,
                product,
                ProxyProductDto.class,
                id
        );
        return convertProxyProductDtoToProduct(response.getBody());
    }

    @Override
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
    }

