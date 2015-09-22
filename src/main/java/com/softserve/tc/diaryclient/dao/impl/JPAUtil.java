package com.softserve.tc.diaryclient.dao.impl;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static final String PERSISTENCE_UNIT_NAME = "DiaryClientPersistence";

	private static EntityManagerFactory factory = null;
	static {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	public static EntityManagerFactory getFactory() {
		return factory;

	}

	public static void close(){
		factory.close();
	}


}