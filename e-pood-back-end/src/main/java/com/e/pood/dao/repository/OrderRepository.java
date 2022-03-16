package com.e.pood.dao.repository;
import com.e.pood.dao.domain.model.shop.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional()
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
