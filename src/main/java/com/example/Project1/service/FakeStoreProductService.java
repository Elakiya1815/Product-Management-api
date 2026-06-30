package com.example.Project1.service;
import com.example.Project1.dtos.FakeStoreProductDto;
import com.example.Project1.exceptions.ProductNotFoundException;
import com.example.Project1.models.Product;
import com.example.Project1.models.Rating;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
    @Service("fakeStoreProductService")
    public class FakeStoreProductService implements ProductService {

        private RestTemplate restTemplate;

        private String apiUrl = "https://fakestoreapi.com/products";

        public FakeStoreProductService(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }


        @Override
        public Product getProductById(Long id) {
            String url = apiUrl + "/" + id;
            try{
                FakeStoreProductDto dto = restTemplate.getForObject(url, FakeStoreProductDto.class);
                if(dto == null){
                    throw new ProductNotFoundException("Product with id " + id + " not found.");
                }
                Product product = mapDtoToProduct(dto);
                return product;
            }catch (Exception e){
                throw new ProductNotFoundException("Product with id " + id + " not found.");
            }

        }

        @Override
        public List<Product> getProducts() {
            try{
                FakeStoreProductDto dtos[] =  restTemplate.getForObject(apiUrl, FakeStoreProductDto[].class);
                if(dtos == null || dtos.length ==0){
                    return List.of();
                }
                List<Product> products =  Arrays.stream(dtos).map(dto -> mapDtoToProduct(dto)).collect(Collectors.toList());
                return products;
            }
            catch (Exception e){
                throw new ProductNotFoundException("Products not found.");
            }


        }
        @Override
        public Product createProduct(Product product) {
            return null;
        }

        @Override
        public Product updateProduct(Long id, Product product) {
            return null;
        }

        @Override
        public void deleteProduct(Long id) {

        }

        @Override
        public Product replaceProduct(Long id, Product product) {
            return null;
        }

        @Override
        public List<Product> getByCategory(String category) {
            return List.of();
        }

        private Product mapDtoToProduct(FakeStoreProductDto dto) {

            Product product = new Product();

            product.setTitle(dto.getTitle());
            product.setPrice(dto.getPrice());
            product.setDescription(dto.getDescription());
            product.setCategory(dto.getCategory());
            product.setImageUrl(dto.getImage());

            if(dto.getRating() != null) {

                Rating rating = new Rating();

                rating.setRate(dto.getRating().getRate());
                rating.setCount(dto.getRating().getCount());

                product.setRating(rating);
            }

            return product;
        }

}
