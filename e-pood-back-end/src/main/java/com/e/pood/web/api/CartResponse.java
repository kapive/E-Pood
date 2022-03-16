package com.e.pood.web.api;

import com.e.pood.dao.domain.model.shop.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {

	@JsonProperty("quantity")
	Integer quantity;

	@JsonProperty("product")
	Product product;
}
