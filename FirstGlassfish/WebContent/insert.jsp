<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ejb.UserEJB" %>
<%@ page import="entity.User" %>
<%@ page import="javax.naming.Context" %>
<%@ page import="javax.naming.InitialContext" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spojení s databází</title>
</head>
<body>
	
	<%
		request.setCharacterEncoding("UTF-8");
	
		final Context ctx = new InitialContext();
		UserEJB ejb = (UserEJB)ctx.lookup("java:global/FirstGlassfish/UserEJB");
		
		User user = new User();
		user.setUsername(request.getParameter("username"));
		ejb.saveUser(user);
		
		out.println("Byl úšpěšně vložen uživatel: " + user.getUsername());
			
	%>
	<jsp:include page="index.jsp"></jsp:include>
</body>
</html>