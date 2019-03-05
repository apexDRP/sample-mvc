<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Simple MVC Example</title>
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
<%@ page import="java.util.ArrayList"%>
<%@ page import="mvc.model.User"%>
<%
	if (request.getAttribute("Message") != null) {
		out.print("<script>alert('" + request.getAttribute("Message") + "')</script>");
	}
%>
	<div class="account_wrapper">
        <div class="loginbox">
            <img src="resources/img/avatar.png" class="avatar">
            <h1 class="login_header">Login Here</h1>
            <form action="LoginServlet" method="post">
                <p>Username</p>
                <input type="text" name="username" placeholder="Enter Username">
                <p>Password</p>
                <input type="password" name="password" placeholder="Enter Password" style="width: 100%">
                <input type="submit" name="" value="Login">
            </form>
     	</div>
    	<div class="loginbox">
       		<form action="UserListServlet" method="post">
            <input type="submit" name="" value="List Users">
            <%	
            	if (request.getAttribute("ArrayList") != null){
            		ArrayList<User> users = (ArrayList) request.getAttribute("ArrayList");
            		
            		for (User user : users){
            			out.print(user.getUsername());
            			out.print("</br>");
            		}
            	}
            %>
     		</form>
    	</div>
    </div>
</body>
</html>