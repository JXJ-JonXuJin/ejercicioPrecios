package com.comercio.dao;

import java.util.List;

import com.comercio.model.Price;

public interface PriceDao {

	/**
	 * Obtiene una lista de precios segun los parametros proporcionados en el objeto Price.
	 * 
	 * @param price Objeto Price que contiene los criterios de busqueda.
	 * @return Lista de precios que cumplen con los criterios especificados.
	 */
	List<Price> obtenerListaPrecios(Price price);

}
