package com.e.pood.dao.domain.model.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.e.pood.ApplicationDatabaseNames.CART;
import static javax.persistence.FetchType.LAZY;

@Table(name = CART)
@Entity(name = "Cart")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "cart_id")
	private Integer cartId;

	@NotNull
	@Column(name = "product_id")
	private Integer productId;

	@NotNull
	@Column(name = "product_quantity")
	private Integer productQuantity;
}
