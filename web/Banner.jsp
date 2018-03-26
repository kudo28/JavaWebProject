<%-- 
    Document   : Banner
    Created on : Mar 6, 2018, 2:55:54 PM
    Author     : Kudo
--%>

<%@page import="Model.DBConnection"%>
<%@page import="java.sql.ResultSet"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Banner</title>
    </head>
    <body>        
        <div class="row">                
            <div class="col-md-8" style = "text-align: center">
                <h4>RollNumber &nbsp;Full Name </h4>
            </div>
            <div class="col-md-4" style="float:right">
                <h4>Welcome: username</h4>
            </div>
        </div>
        <div class="row"  style = "text-align: center">
            <div class="col-md-8 row">

                <div class="col-md-4"><a href="">Login</a></div>
                <div class="col-md-4"><a href="">Register</a> </div>
                <div class="col-md-4"><a href="">Log out</a> </div>

            </div>
            <div class="col-md-4 row" >
                <div class="col-md-6"> <h4><a href= "ShowShoppingCart.jsp"><img src="image/cart.png" height="50" width="70"/></a> </div>
                <div class="col-md-6">
                    <%
                        int total = 0;
                        int totalCount = 0;
                        DBConnection db = new DBConnection();
                        java.util.Enumeration em = session.getAttributeNames();
                        for (; em.hasMoreElements();) {
                            String id = em.nextElement().toString();
                            //get value from session object (see HttpSession)
                            String count = session.getAttribute(id).toString();
                            String sql = "select * from Product where status=1 and pid=" + Integer.parseInt(id);
                            ResultSet rs = db.getData(sql);

                            while (rs.next()) {
                                total += rs.getInt(4) * Integer.parseInt(count);
                                totalCount += Integer.parseInt(count);
                            }
                        }
                        out.println("<div>Total Number: " + totalCount + "</div>");
                        out.println("Total Cost: " + total);
                    %>

                </div>
            </div>
        </div>
    </body>
</html>
