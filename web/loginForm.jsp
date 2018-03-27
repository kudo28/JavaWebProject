<%-- 
    Document   : loginForm
    Created on : Mar 26, 2018, 10:26:08 PM
    Author     : Kudo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>Login Page</title>
    </head>
    <%
        String error = (String) request.getAttribute("error");
        if (error == null) {
            error = "Please login to continue";
        }
    %>
    <body style="background-image: url('image/mountain.jpg')">
        <div style="margin:150px;text-align: center; padding:2% ; border-radius: 70px; border:1px solid green; margin:100px 400px 0px 400px">
            <h6> <%=error%></h6>        
            <form action="UserController" method="post">
                Login ID :  <input type= "text" name="loginID"><br/><br/>
                Password: <input type="password" name="password"><br/><br/>
                <input type="submit" value="Login" class="btn btn-primary btn-md"> &nbsp; 
                <input type="reset" value="Reset" class="btn btn-warning btn-md">
            </form>
        </div>
    </body>
</html>
