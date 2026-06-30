package com.example.Project1.service;
import com.example.Project1.models.Product;

import java.util.List;

    public interface ProductService {

        public Product getProductById(Long id);

        public List<Product> getProducts();

        public Product createProduct(Product product);

        public Product updateProduct(Long id, Product product);

        public void deleteProduct(Long id);

        public Product replaceProduct(Long id, Product product);

        public List<Product> getByCategory(String category);


    }

