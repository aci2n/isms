package isms.api;

import isms.dao.BaseDao;
import isms.services.ConnectionSupplier;

public abstract class BaseApi {

	protected <T extends BaseDao> T dao(T dao) {
		dao.setSupplier(new ConnectionSupplier());
		return dao;
	}

}
