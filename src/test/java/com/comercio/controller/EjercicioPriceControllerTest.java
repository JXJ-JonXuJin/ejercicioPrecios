package com.comercio.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

//Deshabilitar el arranque de la aplicación para lanzar el test entero, ya que el iniApplicationTest ya lanza SpringBoot
@SpringBootTest
@AutoConfigureMockMvc
class EjercicioPriceControllerTest {

	@Autowired
    private MockMvc mockMvc;
		
	@Test
	void testObtenerListaPrecios() throws Exception{
		//Obtenemos los datos basicos requeridos por el ejercicio
		
		//Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
		lanzarPeticion("Test 1", "2020-06-14-10.00.00");
		//Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	    lanzarPeticion("Test 2", "2020-06-14-16.00.00");
	    //Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
	    lanzarPeticion("Test 3", "2020-06-14-21.00.00");
	    //Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
	    lanzarPeticion("Test 4", "2020-06-15-10.00.00");
	    //Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
	    lanzarPeticion("Test 5", "2020-06-16-21.00.00");

	}

	private void lanzarPeticion(String test, String fecha) throws Exception {
	    MvcResult dato = mockMvc.perform(get("/prices/listEjercicio")
	    	.param("fechaAplicacion", fecha)
	    	.param("idProducto", "35455")
	    	.param("idCadena", "1"))
	    .andExpect(status().isOk())
	    .andReturn();

	    String body = dato.getResponse().getContentAsString();
	    System.out.println(test + " con fecha " + fecha + " : " + body);
	}
}
