<%-- 
    Document   : Product
    Created on : Feb 6, 2018, 3:39:47 PM
    Author     : HP
--%>



<%@page import="Model.DBConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product List</title>
    </head>
    <body>
        <!--get data-->
        <%

            ResultSet rs = (ResultSet) request.getAttribute("pkq");

        %>          
        <div style="padding-top: 20px"> 
        <table border="1" >            
            <tr>
                <th>ID</th>
                <th>Product Name</th>
                <th>Producer Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Image</th>
                <th>Description</th>
                <th>Update</th>
                <th>Delete</th>
                <th>Selection </th>
            </tr>

            <% while (rs.next()) {%>
            <tr>
                <td><%=rs.getString(4)%></td>
                <td><%=rs.getString(5)%></td>
                <td><%=rs.getString(2)%></td>
                <td><%=rs.getString(6)%></td>
                <td><%=rs.getString(7)%></td>
                <td><img height="50" width="70" src="<%=rs.getString(8)%> "></td>
                <td><%=rs.getString(9)%></td>
                <td><a href=ProductController?service=preUpdate&pid=<%=rs.getString(4)%>&cid=<%=rs.getString(1)%>>Update</a></td>
                <td><a href=ProductController?service=delete&pid=<%=rs.getString(4)%>&cid=<%=rs.getString(1)%>>Delete</a></td>
                <td><a href=ProductController?service=addToCart&pid=<%=rs.getString(4)%>>Add to Cart </a></td>
            </tr>
            <%}%>
        </table>
    
       
        </div>
    </body>
</html>
