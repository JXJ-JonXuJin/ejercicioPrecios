package com.comercio.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;

import com.comercio.model.Brand;
import com.comercio.model.Price;
import com.comercio.service.PriceService;

class PriceControllerTest {

	@InjectMocks
    private PriceController pricesController;
	@Mock
    private PriceService priceService;
	
	private Price price;
	@BeforeEach
    public void setUp()
            throws Exception {
        MockitoAnnotations.openMocks(this);
        Brand brand = new Brand(1L, "ZARA");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime format;
        
        price = new Price();
    	price.setId(1L);
    	price.setProductId(35455L);
    	price.setBrand(brand);
    	format = LocalDateTime.parse("2020-06-14-00.00.00", formatter);
    	price.setStartDate(Timestamp.valueOf(format));
    	format = LocalDateTime.parse("2020-12-31-23.59.59", formatter);
    	price.setEndDate(Timestamp.valueOf(format));
    	price.setPriceList(1l);
    	price.setPriority(0);
    	price.setPrice(new BigDecimal("35.50"));
    	price.setCurrency("EUR");
    }
	
	@Test
	void testObtenerListaPrecios() {
		Mockito.when(priceService.obtenerListaPrecios(Mockito.any(Price.class))).thenReturn(price);
		Price resultado = pricesController.obtenerListaPrecios("2020-06-14-16.00.00", 35455L, 1L);
		assertEquals(price.getId(), resultado.getId());
	}
	
	@Test
	void testObtenerListaPreciosDataAccessException() {
		when(priceService.obtenerListaPrecios(any())).thenThrow(new DataAccessException("fallo BD") {});
		pricesController.obtenerListaPrecios("2020-06-14-16.00.00", 35455L, 1L);
	}
	
	@Test
	void testObtenerListaPreciosParseException() {
		pricesController.obtenerListaPrecios("fallo Parser", 35455L, 1L);
	}

}
