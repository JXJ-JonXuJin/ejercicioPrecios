package com.comercio.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BrandTest {

	private Brand brand;
	
	@BeforeEach
    public void setUp()
            throws Exception {
		brand = new Brand(1L, "ZARA");
		brand = new Brand();
		brand.setId(1L);
		brand.setName("ZARA");
	}
	
	@Test
	void testGetId() {
		assertEquals(1L, brand.getId());
	}

	@Test
	void testGetName() {
		assertEquals("ZARA", brand.getName());
	}

}
