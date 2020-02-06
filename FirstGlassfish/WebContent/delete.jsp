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
		final Context ctx = new InitialContext();
		UserEJB ejb = (UserEJB)ctx.lookup("java:global/FirstGlassfish/UserEJB");
		
		String[] ids = request.getParameterValues("delete");
		for (int i = 0; i < ids.length; i++){
			ejb.deleteUser(Integer.parseInt(ids[i]));
		}
		
		
		out.println("Vybraní uživatelé byli smazáni.");
			
	%>
	<jsp:include page="index.jsp"></jsp:include>
</body>
</html>