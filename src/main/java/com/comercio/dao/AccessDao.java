package com.comercio.dao;

import javax.sql.DataSource;

/**
 * Access DAO interface.
 */
public interface AccessDao {

	/**
     * Obtener el DataSource.
     *
     * @param dataSource el DataSource
     */
    public void setDataSource(DataSource dataSource);
}
