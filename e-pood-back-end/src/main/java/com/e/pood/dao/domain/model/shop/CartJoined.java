package com.e.pood.dao.domain.model.shop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.e.pood.ApplicationDatabaseNames.CART;

@Table(name = CART)
@Entity(name = "CartJoined")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartJoined {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "cart_id")
	private Integer cartId;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@JoinColumn(name = "product_id")
	private Product product;

	@NotNull
	@Column(name = "product_quantity")
	private Integer productQuantity;

}
