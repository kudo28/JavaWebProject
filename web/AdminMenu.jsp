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
        <div style="padding: 20px">
            <a href="CategoryController"> <h5>Category Manager</h5></a>    <hr/>
            <a href="ProductController?service=manageProduct"> <h5>Product Manager</h5></a>    <hr/>
            <a href="BillController"> <h5>Bill Manager</h5></a>    <hr/>
            
        </div>
    </body>
</html>
