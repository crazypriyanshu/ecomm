These codes were updated as a part of upgrading the service:

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



`catch (HttpClientErrorException.BadRequest e) {
System.out.println("Bad Request: "+ e.getMessage());
throw new RestClientException("Bad Request: "+ e.getMessage());
}`


`catch (HttpClientErrorException e) {
System.out.println("Client Error: "+ e.getMessage());
throw new RestClientException("Internal Server Error: "+ e.getMessage());
}`