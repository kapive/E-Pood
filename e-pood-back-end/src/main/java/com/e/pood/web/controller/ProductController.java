package com.e.pood.web.controller;

import com.e.pood.dao.domain.model.shop.Product;
import com.e.pood.service.ProductService;
import com.e.pood.service.WebShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/api/v1/product")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping()
	@ResponseBody
	public Collection<Product> getAllProducts() {
		return productService.getAllProducts();
	}
}
