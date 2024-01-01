package com.min.fashion.product.repository;

import com.min.fashion.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    // 필요한 메소드 추가
}

