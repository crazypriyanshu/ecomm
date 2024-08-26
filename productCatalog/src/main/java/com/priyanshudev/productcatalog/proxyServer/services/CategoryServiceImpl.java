package com.priyanshudev.productcatalog.proxyServer.services;


public class CategoryServiceImpl implements CategoryService {

    @Override
    public String getAllCategories() {
        return "getting all categories";
    }

    @Override
    public String getCategoryById(int id) {
        return "getting category by id: " + id;
    }
}
