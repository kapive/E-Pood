package com.e.pood.service;

import com.e.pood.dao.domain.model.shop.Order;
import com.e.pood.dao.repository.OrderRepository;
import com.e.pood.web.api.OrderUpdateRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
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
