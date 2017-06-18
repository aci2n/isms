package isms.api;

import isms.dao.BaseDao;
import isms.services.ConnectionSupplier;

public abstract class BaseApi {

	protected <T extends BaseDao> T dao(T dao) {
		dao.setSupplier(new ConnectionSupplier());
		return dao;
	}

	/**
	 * If we ever add authentication (not really relevant to our prototype),
	 * make this return the actual ID.
	 * 
	 * @return String
	 */
	protected String user() {
		return "avalon";
	}
}
