package com.e.pood.dao.repository;

import com.e.pood.dao.domain.model.shop.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional()
public interface CartRepository extends JpaRepository<Cart, Integer> {
	@Query("SELECT MAX(c.cartId) FROM Cart c")
	Integer getMaxCartId();

	Cart getCartByCartIdAndProductId(Integer cartId, Integer productId);
}
