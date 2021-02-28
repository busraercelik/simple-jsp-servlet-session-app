<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration Page</title>
</head>
<body>

	<% 
		// if the user already logged in, login.jsp page should redirect to welcome.jsp 
		Boolean isLoggedin = (Boolean) session.getAttribute("IS_LOGGED_IN"); 
		System.out.println("I logged in before : "+isLoggedin);
		
		if (isLoggedin != null && isLoggedin == true) {
			response.sendRedirect("welcome.jsp");
		}
	%>
	
	<form action="login" method="post">
		User name: <input type="text" name="username" /><br>
		Password: <input type="password" name="password" /><br><br>
		<input type="submit" value="login" />
	</form>
</body>
</html>