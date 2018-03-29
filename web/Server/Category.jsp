<%-- 
    Document   : Category
    Created on : Feb 21, 2018, 9:05:06 PM
    Author     : Kudo
--%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category List</title>
    </head>
    <body >
        <%
            String title = (String) request.getAttribute("title");
            ResultSet rs = (ResultSet) request.getAttribute("kq");
        %>
        <div style="text-align: center">
            <h2><%=title%></h2>
            <table border="1" style="margin-left: 30%">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Status</th>
                    <th>Update</th>
                    <th>Delete</th>
                    <th>Change status</th>
                </tr>
                <% while (rs.next()) {%>
                <tr>
                    <td><%=rs.getString(1)%></td>
                    <td><%=rs.getString(2)%></td>               
                    <%String status = rs.getInt(3) == 1 ? "Active" : "Inactive";%>
                    <td><%=status%> </td>
                    <td><a href=CategoryController?service=preUpdate&catid=<%=rs.getString(1)%>>Update</a></td>
                    <td><a href=CategoryController?service=delete&catid=<%=rs.getString(1)%>>Delete</a></td>
                    <td><a href=CategoryController?service=changestatus&catid=<%=rs.getString(1)%>> Change Status </a></td>
                </tr>
                <%}%>
            </table>
            <hr/>
            <a href="BillController">   <button class="btn btn-primary btn-md"> Back to Admin page </button> </a>
        </div>
    </body>
</html>
