package com.comercio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comercio.dao.PriceDao;
import com.comercio.model.Price;

@Service
public class PriceServiceImpl implements PriceService {

	@Autowired
	PriceDao priceDao;

	@Override
	public Price obtenerListaPrecios(Price price) {
		//Como la logica de mayor prioridad y la fecha de aplicacion esta en la consulta SQL, no necesitamos crear la logica de ordenacion
		List<Price> priceList = priceDao.obtenerListaPrecios(price);
		return priceList.isEmpty() ? null : priceList.get(0);
	}
}
