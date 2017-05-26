package isms.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("hello")
public class TestApi {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test(@PathParam("hi") long id) {
		return Long.toString(id);
	}
}
