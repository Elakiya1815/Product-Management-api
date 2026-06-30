package com.example.Project1.service;

import com.example.Project1.exceptions.ProductNotFoundException;
import com.example.Project1.models.Product;
import com.example.Project1.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service("selfProductServices")
    public class SelfProductServices implements ProductService {

        private ProductRepository ProductRepository;

        public SelfProductServices(ProductRepository productRepository) {
            this.ProductRepository = productRepository;
        }

        @Override
        public Product getProductById(Long id) {
            // Custom implementation to fetch product by ID
            Product product = ProductRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found."));
            return product;
        }

        @Override
        public List<Product> getProducts() {
            return ProductRepository.findAll();
        }

        @Override
        public Product createProduct(Product product) {
            return ProductRepository.save(product);
        }

        @Override
        public Product updateProduct(Long id, Product product) {
            Product product1 = getProductById(id); // Ensure product exists
            if (product.getPrice() != null) {
                product1.setPrice(product.getPrice());
            }
            if (product.getTitle() != null) {
                product1.setTitle(product.getTitle());
            }
            if(product.getDescription() != null){
                product1.setDescription(product.getDescription());
            }
            if(product.getImageUrl() != null){
                product1.setImageUrl(product.getImageUrl());
            }


            return ProductRepository.save(product1);

        }

        @Override
        public void deleteProduct(Long id) {
            ProductRepository.deleteById(id);
        }




        @Override
        public Product replaceProduct(Long id, Product product) {
            return null;
        }
        @Override
        public List<Product> getByCategory(String category) {
            return List.of();
        }

    }


