package com.comercio.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.comercio.constants.Constants;
import com.comercio.model.Brand;
import com.comercio.model.Price;

@Repository
public class PriceDaoImpl extends AccessDaoImpl implements PriceDao {

	private static final String SELECT_PRICE = "SELECT P.ID AS ID, P.BRAND_ID AS BRANDID, B.NAME AS NAME, P.START_DATE AS STARTDATE, P.END_DATE AS ENDDATE, P.PRICE_LIST AS PRICELIST, P.PRODUCT_ID AS PRODUCTID, P.PRIORITY AS PRIORITY, P.PRICE AS PRICE, P.CURR AS CURR ";
	private static final String FROM_PRICE = " FROM PRICES P ";
	private static final String INNER_BRAND = " INNER JOIN BRANDS B ON P.BRAND_ID = B.ID ";
	private static final String ORDER_PRICE = " ORDER BY P.PRIORITY DESC ";
	
	/**
	 * Mapeador de filas para convertir los resultados de la consulta en objetos Price.
	 */
    private RowMapper<Price> rwMap = new RowMapper<Price>() {
        public Price mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        	Price price = new Price();
        	price.setId(resultSet.getLong("ID"));
        	Brand brand = new Brand(resultSet.getLong("BRANDID"), resultSet.getString("NAME"));
        	
        	price.setBrand(brand);
        	price.setStartDate(resultSet.getTimestamp("STARTDATE"));
        	price.setEndDate(resultSet.getTimestamp("ENDDATE"));
        	price.setPriceList(resultSet.getLong("PRICELIST"));
        	price.setProductId(resultSet.getLong("PRODUCTID"));
        	price.setPriority(resultSet.getInt("PRIORITY"));
        	price.setPrice(resultSet.getBigDecimal("PRICE"));
        	price.setCurrency(resultSet.getString("CURR"));
        	
            return price;
        }
    };
    
	@Override
	public List<Price> obtenerListaPrecios(Price price) {
		StringBuilder query = new StringBuilder(SELECT_PRICE);
        query.append(FROM_PRICE);
        query.append(INNER_BRAND);
        
        Map<String, ?> mapaWhere = this.getWhereLikeMap(price);
        StringBuilder where = new StringBuilder(Constants.WHERE);
        where.append(mapaWhere.get(Constants.QUERY));
        query.append(where);
        query.append(ORDER_PRICE);
        
        List<?> params = (List<?>) mapaWhere.get(Constants.PARAMS);
        return this.jdbcTemplate.query(query.toString(), this.rwMap, params.toArray());
	}
	
	/**
	 * Construye el mapa de condiciones WHERE y sus parametros asociados para la consulta de precios.
	 *
	 * @param price El objeto Price que contiene los criterios de busqueda.
	 * @return Un mapa con la cadena de condiciones WHERE y la lista de parametros.
	 */
	private Map<String, Object> getWhereLikeMap(Price price) {

        StringBuilder where = new StringBuilder("");
        List<Object> params = new ArrayList<Object>();

        if (price != null && price.getBrand() != null && price.getBrand().getId() != null) {
			where.append(" AND P.BRAND_ID = ?");
            params.add(price.getBrand().getId());
        }
        if (price != null && price.getProductId() != null) {
            where.append(" AND P.PRODUCT_ID = ?");
            params.add(price.getProductId());
        }
        if (price != null && price.getFechaAplicacion() != null) {
        	where.append(" AND P.START_DATE <= ? ");
        	params.add(price.getFechaAplicacion());
        	where.append(" AND P.END_DATE >= ? ");
        	params.add(price.getFechaAplicacion());
        }
        // Cargamos las condiciones y parametros en un mapa para devolverlos
        Map<String, Object> mapWhere = new HashMap<String, Object>();
        mapWhere.put(Constants.QUERY, where);
        mapWhere.put(Constants.PARAMS, params);

        return mapWhere;
    }

}
