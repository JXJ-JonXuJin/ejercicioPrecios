package com.comercio.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PriceTest {

	private Price price;
	private Brand brand;
	Timestamp now;
	
	@BeforeEach
    public void setUp()
            throws Exception {
		brand = new Brand();
		now = new Timestamp(System.currentTimeMillis());
		price = new Price();
		price.setId(1L);
		price.setBrand(brand);
		price.setStartDate(now);
		price.setEndDate(now);
		price.setPriceList(1L);
		price.setProductId(1L);
		price.setPriority(1);
		price.setPrice(new BigDecimal(1));
		price.setCurrency("EUR");
		price.setFechaAplicacion(now);
	}
	
	@Test
	void testGetId() {
		assertEquals(1L, price.getId());
	}

	@Test
	void testGetBrand() {
		assertEquals(brand, price.getBrand());
	}

	@Test
	void testGetStartDate() {
		assertEquals(now, price.getStartDate());
	}

	@Test
	void testGetEndDate() {
		assertEquals(now, price.getEndDate());
	}

	@Test
	void testGetPriceList() {
		assertEquals(1L, price.getPriceList());
	}

	@Test
	void testGetProductId() {
		assertEquals(1L, price.getProductId());
	}

	@Test
	void testGetPriority() {
		assertEquals(1, price.getPriority());
	}

	@Test
	void testGetPrice() {
		assertEquals(new BigDecimal(1), price.getPrice());
	}

	@Test
	void testGetCurrency() {
		assertEquals("EUR", price.getCurrency());
	}

	@Test
	void testGetFechaAplicacion() {
		assertEquals(now, price.getFechaAplicacion());
	}

}
