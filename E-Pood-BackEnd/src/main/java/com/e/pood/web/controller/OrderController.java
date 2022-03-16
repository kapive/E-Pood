package com.e.pood.web.controller;

import com.e.pood.dao.domain.model.shop.Order;
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

	private final WebShopService webShopService;

	public OrderController(WebShopService webShopService) {
		this.webShopService = webShopService;
	}

	@GetMapping(value = "/{orderId}")
	@ResponseBody
	public Order getOrderById(@PathVariable Integer orderId) {
		return webShopService.getOrderById(orderId);
	}

	@PostMapping(value = API_NEW_ORDER)
	public void createOrder(@RequestParam Integer cartId) {
		webShopService.createOrder(cartId);
	}

	@PostMapping(value = API_UPDATE_ORDER)
	public Order updateOrder(@RequestBody OrderUpdateRequest orderUpdateRequest) {
		return webShopService.updateOrderStatus(orderUpdateRequest);
	}
}
