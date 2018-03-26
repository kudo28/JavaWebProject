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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category List</title>
    </head>
    <body>
        <%
            String title = (String) request.getAttribute("title");
            ResultSet rs = (ResultSet) request.getAttribute("kq");
        %>
        <table border="1">
            <caption><%=title%></caption>
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
    </body>
</html>
