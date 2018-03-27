<%-- 
    Document   : Banner
    Created on : Mar 6, 2018, 2:55:54 PM
    Author     : Kudo
--%>

<%@page import="Entity.Customer"%>
<%@page import="Model.DBConnection"%>
<%@page import="java.sql.ResultSet"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <title>Banner</title>
    </head>
    <body >        
        <%
            Customer customer = (Customer) session.getAttribute("User");
            if(customer == null){
                customer = new Customer(-1, "unknown","000", "000", "unknown", "000", 1);
            }
        %>
        <div class="row">                
            <div class="col-md-8" style = "text-align: center">
                <h4>Roll Number: <%=customer.getID()%></h4>
                <h4> Fullname: <%=customer.getName()%> </h4>
            </div>
            <div class="col-md-4">
                <h4 style="color:#3333ff"> <img src='image/welcome.png' width="200px"> <%=customer.getUsername()%></h4>
            </div>
        </div>
        <div class="row"  style = "text-align: center">
            <div class="col-md-8 row">
                <%if(customer.getID()==-1){%>
                <div class="col-md-6" style="hidden"><a href="UserController?service=login" class="btn btn-primary btn-md">Login</a></div>               
                <div class="col-md-6"><a href="Client/CustomerForm.html" class="btn btn-success btn-md">Register</a> </div>
            <%} else {%>
             <div class="col-md-12"><a href="UserController?service=logout" class="btn btn-danger btn-md">Log out</a> </div>
             <%}%>
            </div>
            <div class="col-md-4 row" >              
            </div>
        </div>
    </body>
</html>
