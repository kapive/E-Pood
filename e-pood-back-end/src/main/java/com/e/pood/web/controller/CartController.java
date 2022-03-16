package com.e.pood.web.controller;

import com.e.pood.service.CartService;
import com.e.pood.web.api.CartRequest;
import com.e.pood.web.api.CartResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/api/v1/cart")
public class CartController {
	private static final String API_ADD_PRODUCT_TO_CART = "/add";
	private static final String API_REMOVE_PRODUCT_FROM_CART = "/remove";
	private static final String API_DELETE_PRODUCT_FROM_CART = "/delete";
	private static final String API_GET_NEW_CART_ID = "/new";

	private final CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping(value = API_GET_NEW_CART_ID)
	@ResponseBody
	public Integer getNewCartId() {
		return cartService.getNewCartId();
	}

	@PostMapping(value = API_ADD_PRODUCT_TO_CART)
	public void addProductToCart(@RequestBody CartRequest cartRequest) {
		cartService.addProductToCart(cartRequest);
	}

	@PostMapping(value = API_REMOVE_PRODUCT_FROM_CART)
	public void removeProductFromCart(@RequestBody CartRequest cartRequest) {
		cartService.removeProductFromCart(cartRequest);
	}

	@PostMapping(value = API_DELETE_PRODUCT_FROM_CART)
	public void deleteProductFromCart(@RequestBody CartRequest cartRequest) {
		cartService.deleteProductFromCart(cartRequest);
	}

	@GetMapping()
	@ResponseBody
	public Collection<CartResponse> getCartById(@RequestParam Integer cartId) {
		return cartService.getCartById(cartId).stream()
				.map(cart ->
						CartResponse
								.builder()
								.product(cart.getProduct())
								.quantity(cart.getProductQuantity())
								.build())
				.collect(Collectors.toList());
	}
}
