package com.e.pood.test;

import com.e.pood.service.WebShopService;

import com.e.pood.web.api.CartRequest;
import com.e.pood.web.api.OrderUpdateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Objects.isNull;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebShopUnitTest {

	@Autowired
	private WebShopService webShopService;

	@Test
	public void getAllProducts() {
		var products = webShopService.getAllProducts();
		assertThat(products.size() == 10);
	}

	@Test
	public void addProductToCart() {
		var cartRequest = CartRequest.builder().cartId(1).productId(1).build();

		webShopService.addProductToCart(cartRequest);

		var cartProducts = webShopService.getCartById(1);

		assertThat(cartProducts.size() == 1);
		assertThat(cartProducts.stream().findFirst().isPresent());

		var cartProduct = cartProducts.stream().findFirst().get();

		assertThat(cartProduct.getProduct().getProductId() == 1);
		assertThat(cartProduct.getProductQuantity() == 1);
	}

	@Test
	public void removeProductFromCart() {
		var cartRequest = CartRequest.builder().cartId(1).productId(1).build();
		webShopService.addProductToCart(cartRequest);

		webShopService.removeProductFromCart(cartRequest);

		var cartProducts = webShopService.getCartById(1);

		assertThat(cartProducts.size() == 1);
		assertThat(cartProducts.stream().findFirst().isPresent());

		var cartProduct = cartProducts.stream().findFirst().get();

		assertThat(cartProduct.getProduct().getProductId() == 1);
		assertThat(cartProduct.getProductQuantity() == 1);

		webShopService.removeProductFromCart(cartRequest);

		cartProducts = webShopService.getCartById(1);

		assertThat(cartProducts.size() == 0);
	}

	@Test
	public void deleteProductFromCart() {
		var cartRequest1 = CartRequest.builder().cartId(1).productId(1).build();
		var cartRequest2 = CartRequest.builder().cartId(1).productId(2).build();

		webShopService.addProductToCart(cartRequest1);
		webShopService.addProductToCart(cartRequest2);

		webShopService.deleteProductFromCart(cartRequest1);

		var cartProducts = webShopService.getCartById(1);

		assertThat(cartProducts.size() == 1);
		assertThat(cartProducts.stream().findFirst().isPresent());

		var cartProduct = cartProducts.stream().findFirst().get();

		assertThat(cartProduct.getProduct().getProductId() == 2);

		webShopService.deleteProductFromCart(cartRequest2);

		cartProducts = webShopService.getCartById(1);

		assertThat(cartProducts.size() == 0);
	}

	@Test
	public void getNewCartId() {
		var cartId = webShopService.getNewCartId();

		assertThat(cartId == 1);

		var cartRequest = CartRequest.builder().cartId(1).productId(1).build();
		webShopService.addProductToCart(cartRequest);

		cartId = webShopService.getNewCartId();

		assertThat(cartId == 2);

		webShopService.deleteProductFromCart(cartRequest);

		cartId = webShopService.getNewCartId();

		assertThat(cartId == 1);
	}

	@Test
	public void createOrder() {
		var cartRequest1 = CartRequest.builder().cartId(1).productId(1).build();
		var cartRequest2 = CartRequest.builder().cartId(1).productId(2).build();

		webShopService.addProductToCart(cartRequest1);
		webShopService.addProductToCart(cartRequest2);

		webShopService.createOrder(1);

		var order = webShopService.getOrderById(1);

		assertThat(!isNull(order));
		assertThat(order.getCartId() == 1);
		assertThat(order.getOrderStatus().equals("REGISTERED"));
	}

	@Test
	public void updateOrderStatus() {
		var newStatus = "RETURNED";
		webShopService.updateOrderStatus(OrderUpdateRequest.builder().orderId(1).orderStatus(newStatus).build());

		var order = webShopService.getOrderById(1);

		assertThat(!isNull(order));
		assertThat(order.getCartId() == 1);
		assertThat(order.getOrderStatus().equals("RETURNED"));
	}
}
