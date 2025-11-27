package com.comercio.dao;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class AccessDaoTest {

	@InjectMocks
    private AccessDaoImpl accessDaoImpl;

	@BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSetDataSource() {
    	accessDaoImpl.setDataSource(Mockito.mock(DataSource.class));
    }

}
