package com.qa.ims.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {

	List<T> readAll() throws SQLException;

	T create(T t) throws SQLException;

	T update(T t) throws SQLException;

	int delete(long id) throws SQLException;

	T modelFromResultSet(ResultSet resultSet) throws SQLException;
}
