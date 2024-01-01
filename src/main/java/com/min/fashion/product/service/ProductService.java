package com.min.fashion.product.service;

import com.min.fashion.product.dto.ProductDTO;
import com.min.fashion.product.entity.ProductEntity;
import com.min.fashion.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void addProduct(ProductDTO productDTO) {
        ProductEntity product = convertToEntity(productDTO);
        productRepository.save(product);
    }

    private ProductEntity convertToEntity(ProductDTO productDTO) {
        ProductEntity entity = new ProductEntity();
        entity.setName(productDTO.getName());
        entity.setDescription(productDTO.getDescription());
        entity.setPrice(productDTO.getPrice());
        entity.setImageUrl(productDTO.getImageUrl());
        return entity;
    }
}
