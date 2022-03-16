package com.e.pood.service;

import com.e.pood.dao.domain.model.shop.Cart;
import com.e.pood.dao.domain.model.shop.CartJoined;
import com.e.pood.dao.repository.CartJoinedRepository;
import com.e.pood.dao.repository.CartRepository;
import com.e.pood.web.api.CartRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.Objects.isNull;

@Service
public class CartService {

	private final CartJoinedRepository cartJoinedRepository;
	private final CartRepository cartRepository;

	public CartService(CartJoinedRepository cartJoinedRepository, CartRepository cartRepository) {
		this.cartJoinedRepository = cartJoinedRepository;
		this.cartRepository = cartRepository;
	}

	public void addProductToCart(CartRequest cartRequest) {
		var cart = cartRepository.getCartByCartIdAndProductId(cartRequest.getCartId(), cartRequest.getProductId());

		if (isNull(cart)) {
			cartRepository.save(
					Cart.builder()
							.cartId(cartRequest.getCartId())
							.productId(cartRequest.getProductId())
							.productQuantity(1)
							.build());
		} else {
			cart.setProductQuantity(cart.getProductQuantity() + 1);
			cartRepository.save(cart);
		}
	}

	public void removeProductFromCart(CartRequest cartRequest) {
		var cart = cartRepository.getCartByCartIdAndProductId(cartRequest.getCartId(), cartRequest.getProductId());

		if (!isNull(cart)) {
			if (cart.getProductQuantity() - 1 == 0) {
				cartRepository.delete(cart);

			} else {
				cart.setProductQuantity(cart.getProductQuantity() - 1);
				cartRepository.save(cart);
			}
		}
	}

	public void deleteProductFromCart(CartRequest cartRequest) {
		var cart = cartRepository.getCartByCartIdAndProductId(cartRequest.getCartId(), cartRequest.getProductId());

		if (!isNull(cart)) {
			cartRepository.delete(cart);
		}
	}

	public Integer getNewCartId() {
		var currentMaxCartId = cartRepository.getMaxCartId();
		return isNull(currentMaxCartId) ? 1 : currentMaxCartId + 1;
	}

	public Collection<CartJoined> getCartById(Integer cartId) {
		return cartJoinedRepository.getAllByCartId(cartId);
	}
}
