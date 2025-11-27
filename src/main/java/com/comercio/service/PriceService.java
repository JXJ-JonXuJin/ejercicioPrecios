package com.comercio.service;

import com.comercio.model.Price;

/**
 * Servicio para la gestion de precios.
 */
public interface PriceService {
	
	/**
	 * Obtiene una lista de precios segun los parametros proporcionados en el objeto Price.
	 * 
	 * @param price Objeto Price que contiene los criterios de busqueda.
	 * @return Lista de precios que cumplen con los criterios especificados.
	 */
	Price obtenerListaPrecios(Price price);

}
