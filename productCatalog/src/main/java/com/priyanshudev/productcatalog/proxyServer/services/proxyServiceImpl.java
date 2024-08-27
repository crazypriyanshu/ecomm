package com.priyanshudev.productcatalog.proxyServer.services;

import com.priyanshudev.productcatalog.proxyServer.dtos.ProxyCreateProductDto;
import com.priyanshudev.productcatalog.proxyServer.dtos.ProxyProductDto;
import com.priyanshudev.productcatalog.proxyServer.models.Category;
import com.priyanshudev.productcatalog.proxyServer.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        Date date = null;
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
    public List<Product> getAllProducts() {
        Date date = null;
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<List> response = restTemplate.getForEntity(
                "https://api.escuelajs.co/api/v1/products",
                List.class
        );
        List<Product> products = new ArrayList<>();
        for (Object obj: response.getBody()) {
            HashMap<String, Object> hm = (HashMap<String, Object>) obj;

            Product product = new Product();
            // Id is coming as Integer, but we need to cast to Long as our Product class id is a Long
            product.setId(Long.valueOf((Integer)hm.get("id")));
            //title is a String
            product.setTitle(hm.get("title").toString());
            // description is String
            product.setDescription(hm.get("description").toString());
            //lets check id
            Object idObj = hm.get("id");
            if (idObj instanceof Integer) {
                product.setId(Long.valueOf((Integer) idObj));
            } else if (idObj instanceof Long) {
                product.setId((Long) idObj);
                product.setProductId((Long) idObj);
            }
            // Handling imageUrl conversion
            Object imageUrlObj = hm.get("images");
            System.out.println("instance of imageUrl: " + imageUrlObj.getClass().getName());
            if (imageUrlObj instanceof String) {
                // Convert single String to String[]
                product.setImageUrl(new String[]{(String) imageUrlObj});
            } else if (imageUrlObj instanceof ArrayList) {
                // Convert List to String[]
                ArrayList<String> imageUrlList = (ArrayList<String>) imageUrlObj;
                product.setImageUrl(imageUrlList.toArray(new String[0]));
            }
            // Handling Date Conversions :
            String createdAt = (String) hm.get("creationAt");
            String updatedAt = (String) hm.get("updatedAt");
            OffsetDateTime odt = OffsetDateTime.parse(createdAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            OffsetDateTime odt2 = OffsetDateTime.parse(updatedAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            date = Date.from(odt.toInstant());
            product.setCreatedAt(date);
            date = Date.from(odt2.toInstant());
            product.setLastUpdatedAt(date);

            HashMap<String, Object> categoryObj = (HashMap<String, Object>) hm.get("category");
            Category category = new Category();
            category.setId(Long.valueOf((Integer)categoryObj.get("id")));
            category.setName(categoryObj.get("name").toString());
            category.setImage(categoryObj.get("image").toString());
            String createdAtCategory = (String) categoryObj.get("creationAt");
            OffsetDateTime odtCategory = OffsetDateTime.parse(createdAtCategory, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            String updatedAtCategory = (String) categoryObj.get("updatedAt");
            OffsetDateTime odtCategory1 = OffsetDateTime.parse(updatedAtCategory, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            date = Date.from(odtCategory.toInstant());
            category.setCreatedAt(date);
            date = Date.from(odtCategory1.toInstant());
            category.setLastUpdatedAt(date);


//            category.setName(hm.get("category").toString());
//            category.setId(productDto.getCategory().getId());
//            category.setCreatedAt(productDto.getCategory().getCreationAt());
//            category.setLastUpdatedAt(productDto.getCategory().getUpdatedAt());
//            category.setImage(productDto.getCategory().getImage());
            product.setCategory(category);
            products.add(product);
        }

        return products;
    }

    private Product convertProxyProductDtoToProduct(ProxyProductDto proxyProductDto) {
        Product product = new Product();
        product.setId(proxyProductDto.getId());
        product.setTitle(proxyProductDto.getTitle());
        product.setDescription(proxyProductDto.getDescription());
        product.setPrice(proxyProductDto.getPrice());
        product.setImageUrl(proxyProductDto.getImages());
        product.setProductId(proxyProductDto.getId());
        product.setCreatedAt(proxyProductDto.getCreationAt());
        product.setLastUpdatedAt(proxyProductDto.getUpdatedAt());
        Category category = new Category();
        category.setName(proxyProductDto.getCategory().getName());
        category.setId(proxyProductDto.getCategory().getId());
        category.setCreatedAt(proxyProductDto.getCategory().getCreationAt());
        category.setLastUpdatedAt(proxyProductDto.getCategory().getUpdatedAt());
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
