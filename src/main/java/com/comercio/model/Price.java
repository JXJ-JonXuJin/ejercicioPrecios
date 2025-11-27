package com.comercio.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase Price.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {
	
	private Long id;
	private Brand brand;
	private Timestamp startDate;
	private Timestamp endDate;
	private Long priceList;
	private Long productId;
	private Integer priority;
	private BigDecimal price;
	private String currency;
	private Timestamp fechaAplicacion;
	
}