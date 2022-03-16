package com.e.pood.dao.domain.model.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.e.pood.ApplicationDatabaseNames.ORDER;

@Table(name = ORDER)
@Entity(name = "Order")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer orderId;

	@NotNull
	@Column(name = "cart_id")
	private Integer cartId;

	@NotNull
	@Column(name = "order_status")
	private String orderStatus;
}
