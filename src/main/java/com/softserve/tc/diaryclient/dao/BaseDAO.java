package com.softserve.tc.diaryclient.dao;

import java.util.List;

public interface BaseDAO<T> {

	void create(T object);

	T find(String id);

	void update(T object);

	void delete(T object);

	List<T> getAll();
}
