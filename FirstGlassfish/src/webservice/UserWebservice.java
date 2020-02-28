package webservice;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ejb.UserEJB;
import entity.User;

@Path("/user-webservice")
public class UserWebservice {

	@EJB
    UserEJB ejb;
	
    @GET
    @Path("/get-users")
    @Produces(MediaType.TEXT_XML)
    public String getUsers() {
    	
    	List<User> users = ejb.getAll();
    	
    	StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\"?>");
		sb.append("<users>");
		
		for (User u : users) {
			sb.append("<user id=\"" + u.getId() + "\">");
			sb.append(u.getUsername());
			sb.append("</user>");
		}
		
		sb.append("</users>");
		
		return sb.toString();
    }

    @POST
    @Path("/insert-user")
    @Consumes("text/plain")
    @Produces("text/plain")
    public String addUser(String username) {
    	User user = new User();
    	user.setUsername(username);
    	ejb.saveUser(user);
    	return "Uživatel byl úspěšně vložen";
    }

}