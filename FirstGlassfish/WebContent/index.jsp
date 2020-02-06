<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="ejb.UserEJB" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.User" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spojení s databází</title>
</head>
<body>
	<h2>Vložit nového uživatele</h2>
	<form action="insert.jsp" method="post">
		Zadejte jméno:<input type="text" name="username"/><br>
		<input type="submit" value="Vložit uživatele">	
	</form>
	
	<h2>Výpis uživatelů</h2>
	<form action="delete.jsp">
		<%
		final Context ctx = new InitialContext();
		UserEJB ejb = (UserEJB)ctx.lookup("java:global/FirstGlassfish/UserEJB");
		
		List<User> users = ejb.getAll();
		for (int i = 0; i < users.size(); i++){
			%>
			<input type="checkbox" name="delete" value="<% out.print(users.get(i).getId()); %>">
			<% out.println(users.get(i).getUsername());	%>
			<br>
			<%
		}		
		%>
		<input type="submit" value="Smazat vybrané">
	</form>
</body>
</html>