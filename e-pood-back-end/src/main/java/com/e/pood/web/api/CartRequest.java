package com.e.pood.web.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {

	@JsonProperty("cartId")
	Integer cartId;

	@JsonProperty("productId")
	Integer productId;
}
