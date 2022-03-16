package com.e.pood.web.controller;

import com.e.pood.dao.domain.model.shop.Order;
import com.e.pood.service.OrderService;
import com.e.pood.service.WebShopService;
import com.e.pood.web.api.OrderUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/api/v1/order")
public class OrderController {

	private static final String API_NEW_ORDER = "/new";
	private static final String API_UPDATE_ORDER = "/update";

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping(value = "/:{orderId}")
	@ResponseBody
	public Order getOrderById(@PathVariable Integer orderId) {
		return orderService.getOrderById(orderId);
	}

	@PostMapping(value = API_NEW_ORDER)
	public void createOrder(@RequestParam Integer cartId) {
		orderService.createOrder(cartId);
	}

	@PostMapping(value = API_UPDATE_ORDER)
	public Order updateOrder(@RequestBody OrderUpdateRequest orderUpdateRequest) {
		return orderService.updateOrderStatus(orderUpdateRequest);
	}
}
