package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;


@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static final String REST_URI = "http://localhost:8080/FirstGlassfish";
	static final String SERVICE_NAME = "/user-webservice/insert-user";


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		URI service_Uri = UriBuilder.fromUri(REST_URI).build();
		WebTarget web_target = client.target(service_Uri);
		
		Response odpoved = web_target.path(SERVICE_NAME).request("text/plain").accept("text/plain").post(Entity.text(request.getParameter("username")));
		String text_odpoved = odpoved.readEntity(String.class);
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset=\"UTF-8\"><title>Webová aplikace</title></head><body>");
		out.println("<h2>Webová aplikace</h2>");
		out.println("<p>" + text_odpoved + "</p>");
		out.println("</body></html>");
	}

}
