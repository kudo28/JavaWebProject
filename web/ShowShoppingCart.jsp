<%@page import="Model.DBConnection"%>
<%@page import="java.sql.ResultSet"%>
<html>
    <head>        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    </head>
    <body style="text-align: center">
        <h1>Shopping Cart Details</h1>
        <table width=50% border="1" style="text-align: center; margin-left:25%">
            <tr>
                <th>ID</th>
                <th>Product Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
                <th>Remove</th>               
            </tr>
            <%
                int total = 0;
                DBConnection db = new DBConnection();
                java.util.Enumeration em = session.getAttributeNames();
                for (; em.hasMoreElements();) {
                    String id = em.nextElement().toString();
                    //get value from session object (see HttpSession)
                    if (id.equalsIgnoreCase("User")) {
                        continue;
                    }
                    String count = session.getAttribute(id).toString();
                    out.println("<tr>");
                    out.println("<td>" + id + "</td>");
                    String sql = "select * from Product where status=1 and pid=" + Integer.parseInt(id);
                    ResultSet rs = db.getData(sql);
                    while (rs.next()) {%>
            <td> <%= rs.getString(2)%></td>
            <td><input type="text" value="<%=count%>" name="<%=id%>"/></td>
            <td> <%= rs.getInt(4)%> </td>
            <td><%= (rs.getInt(4) * Integer.parseInt(count))%></td>
            <% total += rs.getInt(4) * Integer.parseInt(count);%>
            <td><a href=ProductController?service=removeFromCart&pid=<%=id%>>Remove</a></td>
            <%  }%>
        </tr>
        <% }%>
        <tr><td></td><td><a href='#'>Update</a></td><td></td><td>Total</td><td> <%=total%> </td>
            <td><a href=ProductController?service=removeFromCart&pid=all>Remove All</td></tr>


    </table>       
    <hr/>
    <div class="row">
        <div class="col-md-4"> </div>
        <div class="col-md-4 row">               
            <div class="col-md-6"> 
                <% String checkout = "";
                    if (total == 0) {
                        checkout = "disabled";
                    }
                %>
                <a href="ProductController?service=checkout"> <button class="btn btn-success btn-lg" <%=checkout%>>Check-out</button></a>
            </div>
            <div class="col-md-6"> 
                <a href="ProductController">  <button class="btn btn-primary btn-lg">  List Product</button></a>
            </div></div> 
        <div class="col-md-4"> </div>
    </div>

</body>
</html>