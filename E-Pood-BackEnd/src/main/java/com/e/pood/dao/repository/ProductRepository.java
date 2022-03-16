package com.e.pood.dao.repository;

import com.e.pood.dao.domain.model.shop.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional()
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
