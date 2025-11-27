package com.comercio.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comercio.model.Brand;
import com.comercio.model.Price;
import com.comercio.service.PriceService;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private static final Logger log = LogManager.getLogger(PriceController.class);
    
	@Autowired
	PriceService priceService;
	
	/**
	 * Metodo para obtener la lista de precios segun los parametros proporcionados.
	 *
	 * @param fechaAplicacion La fecha de aplicacion en formato yyyy-MM-dd-HH.mm.ss
	 * @param idProducto      El ID del producto
	 * @param idCadena        El ID de la cadena
	 * @return Lista de precios que coinciden con los criterios
	 */
	@GetMapping("/list")
	public Price obtenerListaPrecios(@RequestParam String fechaAplicacion, @RequestParam Long idProducto, @RequestParam Long idCadena) { 
		log.info("llamada {}: {}", "obtenerListaPrecios", "Obtencion de la lista de precios");
		Price precioFinal = null;
		try {
			//Formateamos la fecha segun el patron del ejercicio
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss"); 
			Date fechaFormateada = sdf.parse(fechaAplicacion);
			Timestamp time = new Timestamp(fechaFormateada.getTime());
			//Crear objetos con los parametros
			Price price = new Price();
			price.setFechaAplicacion(time);
			price.setProductId(idProducto);
			Brand brand = new Brand();
			brand.setId(idCadena);
			price.setBrand(brand);
			//Llamar al servicio para obtener la lista de precios
			precioFinal = priceService.obtenerListaPrecios(price);
		} catch (DataAccessException  e) {
			log.error("Error al buscar en bbdd: {}", e.getMessage());
		} catch (ParseException e) {
			log.error("Error de formato de fecha: {}", e.getMessage());
		}
		
        return precioFinal;
	}
	
	/**
	 * Metodo para obtener la lista segun  ejercicio.
	 *
	 * @param fechaAplicacion La fecha de aplicacion en formato yyyy-MM-dd-HH.mm.ss
	 * @param idProducto      El ID del producto
	 * @param idCadena        El ID de la cadena
	 * @return Lista de precios que coinciden con los criterios
	 */
	@GetMapping("/listEjercicio")
	public Map<String, Object> obtenerListaPreciosEjercicio(@RequestParam String fechaAplicacion, @RequestParam Long idProducto, @RequestParam Long idCadena) {		
		Map<String, Object> map = new HashMap<>();
		try {
			//Formateamos la fecha segun el patron del ejercicio
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
			Date fechaFormateada = sdf.parse(fechaAplicacion);
			//Obtener la lista de precios llamando al metodo anterior
			Price precioFinal = obtenerListaPrecios(fechaAplicacion, idProducto, idCadena);
			//mapear los datos necesarios segun ejercicio
			if (precioFinal != null) {
				map.put("productId", precioFinal.getProductId());
				map.put("brandId", precioFinal.getBrand().getId());
				map.put("brandName", precioFinal.getBrand().getName());
				map.put("priceList", precioFinal.getPriceList());
				map.put("fechaAplicacion", sdf.format(fechaFormateada));
				map.put("price", precioFinal.getPrice());
	        }
		} catch (ParseException e) {
			log.error("Error de formato de fecha: {}", e.getMessage());
		}
		
        return map;
	}
}
