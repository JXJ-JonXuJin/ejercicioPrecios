package com.comercio.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.comercio.model.Brand;
import com.comercio.model.Price;

class PriceDaoTest {

	@Mock
    private JdbcTemplate jdbcTemplate;
	
	@InjectMocks
    private PriceDaoImpl priceDao;
	
	private List<Price> list;
	private Price price;
	
	@BeforeEach
    public void setUp()
            throws Exception {
        MockitoAnnotations.openMocks(this);
        list = new ArrayList<>();
        Brand brand = new Brand(1L, "ZARA");
        
        String[] startList = {"2020-06-14-00.00.00", "2020-06-14-15.00.00", "2020-06-15-00.00.00", "2020-06-15-16.00.00"};
        String[] endList = {"2020-12-31-23.59.59", "2020-06-14-18.30.00", "2020-06-15-11.00.00", "2020-12-31-23.59.59"};
        BigDecimal[] priceList = {BigDecimal.valueOf(35.50), BigDecimal.valueOf(25.45), BigDecimal.valueOf(30.50), BigDecimal.valueOf(38.95)};
        int[] priorities = {0, 1, 1, 1};

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
        LocalDateTime format;
        
        for (int x = 0; x < 4; x++) {
        	Price price = new Price();
        	price.setId(1L);
        	price.setProductId(35455L);
        	price.setBrand(brand);
        	format = LocalDateTime.parse(startList[x], formatter);
        	price.setStartDate(Timestamp.valueOf(format));
        	format = LocalDateTime.parse(endList[x], formatter);
        	price.setEndDate(Timestamp.valueOf(format));
        	price.setPriceList(Long.valueOf(x + 1));
        	price.setPriority(priorities[x]);
        	price.setPrice(priceList[x]);
        	price.setCurrency("EUR");
        	list.add(price);
        }
        
        price = new Price();
    	price.setId(1L);
        price.setBrand(brand);
        price.setProductId(35455L);
        format = LocalDateTime.parse("2020-06-14-16.00.00", formatter);
    	price.setFechaAplicacion(Timestamp.valueOf(format));
    }
	
	@Test
	void testObtenerListaPrecios() {
		when(jdbcTemplate.query(anyString(), any(RowMapper.class), any(Object[].class))).thenReturn(list);
		List<Price> resultado = priceDao.obtenerListaPrecios(price);
		assertEquals(resultado.get(0).getId(), price.getId());
	}
}
