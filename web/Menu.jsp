<%-- 
    Document   : Menu
    Created on : Mar 6, 2018, 2:59:01 PM
    Author     : Kudo
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
    </head>
    <body>

        <%          
            ResultSet rs = (ResultSet) request.getAttribute("ckq");
        %>
        <div style="padding: 20px">
            <a href="ProductController"> <h5>All</h5></a>    
            <% while (rs.next()) {%>        
            <a href="ProductController?catID=<%=rs.getString(1)%>"> <h5><%=rs.getString(2)%></h5></a>    
            <%}%>
        </div>
    </body>
</html>
