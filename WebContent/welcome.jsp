<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>

	<%
		System.out.print("checking if user already logged in..");
		Boolean isLoggedin = (Boolean)session.getAttribute("IS_LOGGED_IN");
		if (isLoggedin==null) {
			System.out.print("user not logged in..");
			response.sendRedirect("login.jsp");
		}else{
			System.out.print("user already logged in..");
		}
	%>

	<form action="logout" method="get">
		<input style="float: right;" type="submit" value="Logout"><br><br>
	</form>
	
	<jsp:useBean id="user" class="bsr.model.User" scope="session"></jsp:useBean>
	Welcome back, <jsp:getProperty property="username" name="user"/>!
	
	<form action="" method="post">
		<label for="myNote">Enter your note..</label><br>
		<textarea id="myNote" rows="4" cols="50"></textarea><br><br>
		<input type="submit" value="Save">
	</form>
	
</body>
</html>