<%-- 
    Document   : Customer
    Created on : Feb 21, 2018, 8:11:54 PM
    Author     : Kudo
--%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer List</title>
    </head>
    <body>
        <%
            String title = (String) request.getAttribute("title");
            ResultSet rs = (ResultSet) request.getAttribute("kq");
        %>
        <form name = "CustomerStatus">
            <div>
                <input type="radio" name="status" value="2" id="2" onclick="handleClick(this);" >
                All
                <input type="radio" name="status" value="1" id="1" onclick="handleClick(this);">
                Active
                <input type="radio"  name="status" value="0" id= "0" onclick="handleClick(this);">
                Inactive
            </div>      
            <label>Search name: </label><input type ="text" name="searchParams" value =""> 
            <input type="submit" value="Search">
            <input type="hidden" name="service" value="listCustomer">
        </form>
        <script>
            var currentValue = "all";
            <% String statusX = (String) request.getAttribute("status");%>
            radiobtn = document.getElementById(<%=statusX%>);
            radiobtn.checked = true;
            function handleClick(status) {
                currentValue = status.value;
                document.location.href = "/JavaWebExample1/CustomerController?status=" + currentValue + "&service=listCustomer";
            }
        </script>
        <table border="1">
            <caption><%=title%></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Phone</th>
                <th>Email</th>
                <th>UserName</th>
                <th>Status</th>
                <th>Change Status </th>
                <th>Update</th>
                <th>Delete</th>
                <th>Reset Password </th>
            </tr>

            <% while (rs.next()) {%>
            <tr>
                <td><%=rs.getString(1)%></td>
                <td><%=rs.getString(2)%></td>
                <td><%=rs.getString(3)%></td>
                <td><%=rs.getString(4)%></td>
                <td><%=rs.getString(5)%></td>
                <%String status = rs.getInt(7) == 1 ? "Active" : "Inactive";%>
                <td><%=status%> </td>
                <% String activeCmd = rs.getInt(7) == 0 ? "Activate" : "Deactivate";%>
                <td><a href=CustomerController?service=<%=activeCmd%>&cid=<%=rs.getString(1)%>> <%=activeCmd%> </a></td>
                <td><a href=CustomerController?service=preUpdate&cid=<%=rs.getString(1)%>>Update</a></td>
                <td><a href=CustomerController?service=delete&cid=<%=rs.getString(1)%>>Delete</a></td>
                <td><a href=CustomerController?service=resetPassword&cid=<%= rs.getInt(1)%>>Reset Password</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
