package com.e.pood.service;

import com.e.pood.dao.domain.model.shop.Cart;
import com.e.pood.dao.domain.model.shop.CartJoined;
import com.e.pood.dao.domain.model.shop.Order;
import com.e.pood.dao.domain.model.shop.Product;
import com.e.pood.dao.repository.CartJoinedRepository;
import com.e.pood.dao.repository.CartRepository;
import com.e.pood.dao.repository.OrderRepository;
import com.e.pood.dao.repository.ProductRepository;
import com.e.pood.web.api.CartRequest;
import com.e.pood.web.api.OrderUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.Objects.isNull;

@Service
public class WebShopService {

	private final ProductRepository productRepository;
	private final OrderRepository orderRepository;
	private final CartJoinedRepository cartJoinedRepository;
	private final CartRepository cartRepository;

	public WebShopService(ProductRepository productRepository,
	                      OrderRepository orderRepository,
	                      CartRepository cartRepository,
	                      CartJoinedRepository cartJoinedRepository) {
		this.productRepository = productRepository;
		this.orderRepository = orderRepository;
		this.cartRepository = cartRepository;
		this.cartJoinedRepository = cartJoinedRepository;
	}

	public Collection<Product> getAllProducts() {
		return productRepository.findAll();
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

	public Order getOrderById(Integer orderId) {
		return orderRepository.findById(orderId).orElse(null);
	}

	public void createOrder(Integer cartId) {
		orderRepository.save(
				Order.builder()
				.cartId(cartId)
				.orderStatus("REGISTERED")
				.build()
		);
	}

	public Order updateOrderStatus(OrderUpdateRequest updateRequest) {
		var order = orderRepository.findById(updateRequest.getOrderId());

		if (order.isPresent()) {
			var updatedOrder = order.get();
			updatedOrder.setOrderStatus(updateRequest.getOrderStatus());
			orderRepository.save(updatedOrder);

			return updatedOrder;
		}

		return null;
	}
}
