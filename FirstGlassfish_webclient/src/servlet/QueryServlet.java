package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.glassfish.jersey.client.ClientConfig;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


@WebServlet("/QueryServlet")
public class QueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static final String REST_URI = "http://localhost:8080/FirstGlassfish";
	static final String SERVICE_NAME = "/user-webservice/get-users";
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		URI service_Uri = UriBuilder.fromUri(REST_URI).build();
		WebTarget web_target = client.target(service_Uri);
		
		out.println("<html><head><meta charset=\"UTF-8\"><title>Webová aplikace</title></head><body>");
		out.println("<h2>Webová aplikace</h2>");
		out.println("<p>Výpis uživatelů</p>");
		
		InputStream	vysledek = web_target.path(SERVICE_NAME).request().accept(MediaType.TEXT_XML).get(InputStream.class);

		
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(vysledek);
			Node koren = doc.getDocumentElement();
			koren.normalize();
			
			NodeList users = koren.getChildNodes();
			
			for (int i = 0; i < users.getLength(); i++) {
				Node uzel = users.item(i);
				if (uzel.getNodeType() == Node.ELEMENT_NODE && uzel.getNodeName().equals("user")) {
					Element user = (Element) uzel;
					int id = Integer.parseInt(user.getAttribute("id"));
					String username = user.getTextContent();
					out.println("<p>" + username + "</p>");
				}
			}
		}
		catch (ParserConfigurationException | SAXException ex) {
			out.println("<p>Data ze vzdáleného serveru nelze rozpoznat.</p>");
		}
		
		out.println("</body></html>");
		
		
		
	}

}
