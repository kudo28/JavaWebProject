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
        <title></title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    </head>
    <body>
        <!--get data-->
        <%

            ResultSet rs = (ResultSet) request.getAttribute("kq");

        %>          
        <div style="padding-top: 30px; padding-bottom: 30px"> 
            <table border="1" style="text-align: center;">            
                <tr>
                    <th>Bill ID</th>
                    <th>Customer Name</th>
                    <th style="width:20%">Date</th>
                    <th style="width:10%">Total</th>
                    <th style="width:10%">Status</th>               
                    <th>View</th>
                </tr>

                <% while (rs.next()) {%>
                <tr>                
                    <td><%=rs.getString(1)%></td>
                    <td><%=rs.getString(2)%></td>
                    <%
                        String date = rs.getString(3).substring(0, 10);
                    %>
                    <td><%=date%></td>
                    <td><%=rs.getString(4)%></td>
                    <% String status = "Waiting";
                        if (rs.getString(5).equalsIgnoreCase("1")) {
                            status = "Done";
                        }
                    %>
                    <td style="color:red"><%=status%></td>               
                    <td ><a href=# class="btn btn-primary btn-md"> Detail  </a></td>
                </tr>
                <%}%>
            </table>


        </div>
    </body>
</html>
