package com.e.pood.dao.repository;

import com.e.pood.dao.domain.model.shop.CartJoined;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional()
public interface CartJoinedRepository extends JpaRepository<CartJoined, Integer> {

	Collection<CartJoined> getAllByCartId(Integer cartId);
}
