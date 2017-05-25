package isms.api;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import isms.common.IOStream;
import isms.common.ObjectMapperUnchecked;
import isms.dao.BaseDao;

public abstract class ApiServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private IOStream io;
	protected ObjectMapperUnchecked mapper;
	protected int status;

	protected ApiServlet() {
		this.mapper = new ObjectMapperUnchecked();
		this.io = new IOStream();
		this.status = HttpServletResponse.SC_OK;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response(response, get(request.getParameterMap()));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response(response, post(io.read(request.getInputStream())));
	}

	protected Object get(Map<String, String[]> map) {
		return methodNotAllowed("GET");
	}

	protected Object post(String data) {
		return methodNotAllowed("POST");
	}

	private String methodNotAllowed(String method) {
		status = HttpServletResponse.SC_METHOD_NOT_ALLOWED;
		return method + " is not allowed.";
	}

	private void response(HttpServletResponse response, Object value) throws IOException {
		response.setStatus(status);
		if (value != null) {
			response.setContentType("application/json");
			byte[] data = mapper.writeValueAsBytes(value);
			io.write(response.getOutputStream(), data);
		}
	}

	protected <T extends BaseDao> T inject(T dao) {
		dao.setSupplier(new ConnectionSupplier());
		return dao;
	}

}
