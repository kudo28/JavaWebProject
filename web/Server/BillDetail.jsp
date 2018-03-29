<%-- 
    Document   : BillDetail
    Created on : Mar 27, 2018, 10:24:21 PM
    Author     : Kudo
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

        <title>Update Bill</title>
    </head>
    <body>
        <%
            ResultSet brs = (ResultSet) request.getAttribute("bkq");
            ResultSet prs = (ResultSet) request.getAttribute("pkq");
            while (brs.next()) {
        %>
        <div style="margin-left: 30%">
            <h2>Update a bill </h2>
            <form action="./BillController" method="get" >
                <%
                    String wcheck = "", dcheck = "";
                    int status = brs.getInt(3);
                    if (status == 0) {
                        wcheck = "selected";
                    } else {
                        dcheck = "selected";
                    }

                %>
                <p> Bill ID:  <input type="text" name="bid" id="bid" value="<%=brs.getString(1)%>"  disabled ></p>           
                <p> Date Created :  <input type="text" name="date" id="date"  value="<%=brs.getString(2)%>" disabled></p>
                <p> Customer ID :  <input type="text" name="cid" id="cid" value="<%=brs.getString(4)%>"> </p>
                <p> Total :  <input type="number" name="total" id="total"  value="<%=brs.getString(5)%>" disabled></p>
                <p> Receiver Name :  <input type="text" name="rname" id="rname" value="<%=brs.getString(6)%>"></p>
                <p> Receiver Phone :  <input type="text" name="rphone" id="rphone" value="<%=brs.getString(7)%>"></p>
                <p> Receiver Mail :  <input type="text" name="rmail" id="rmail" value="<%=brs.getString(8)%>"></p>
                <p>Status : <select name="status">
                        <option value="0" <%=wcheck%>> Waiting</option>
                        <option value="1" <%=dcheck%>> Done</option>
                    </select></p>
                <p> Description :  <input type="text" name="description" id="description" value="<%=brs.getString(9)%>"></p>           
                <table border="1" style="text-align: center">            
                    <tr>
                        <th>Product ID</th>
                        <th>Product Name</th>               
                        <th>Quantity</th>               
                    </tr>

                    <% while (prs.next()) {%>
                    <tr>
                        <td><%=prs.getString(1)%></td>
                        <td><%=prs.getString(2)%></td>
                        <td><%=prs.getString(3)%></td>

                    </tr>
                    <%}%>
                    <p><input type="submit" value="Update" class="btn btn-primary btn-lg">
                        <input type="reset" value="Reset" class="btn btn-warning btn-lg"></p>
                    <input type="hidden" name="service" value="updateBill">
                    <input type="hidden" name="bid" value="<%=brs.getString(1)%>">

                    </form>     

                    </div>
                    <%}%>

                    </body>
                    </html>
