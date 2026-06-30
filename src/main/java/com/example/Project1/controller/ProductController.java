package com.example.Project1.controller;

import com.example.Project1.service.ProductService;
import com.example.Project1.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")        // ← ADDED
public class ProductController {

    private ProductService productService;

    //public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
      //  this.productService = productService;
    //}
    public ProductController(@Qualifier("selfProductServices") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Product Service is running!";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }


    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
