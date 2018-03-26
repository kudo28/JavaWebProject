<%@page import="Model.DBConnection"%>
<%@page import="java.sql.ResultSet"%>
<html>
    <body>
        <h1>Shopping Cart Details</h1>
        <table width=50% border="1" style="text-align: center">
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
                    if (id.equalsIgnoreCase("username") || id.equalsIgnoreCase("password")) {
                        continue;
                    }
                    String count = session.getAttribute(id).toString();
                    out.println("<tr>");
                    out.println("<td>" + id + "</td>");
                    String sql = "select * from Product where status=1 and pid=" + Integer.parseInt(id);
                    ResultSet rs = db.getData(sql);
                    while (rs.next()) {
                        out.println("<td>" + rs.getString(2) + "</td>");
                        out.println("<td>" + count + "</td>");
                        out.println("<td>" + rs.getInt(4) + "</td>");
                        out.println("<td>" + (rs.getInt(4) * Integer.parseInt(count)) + "</td>");
                        total += rs.getInt(4) * Integer.parseInt(count);
                        out.println("<td><a href=ProductController?service=removeFromCart&pid=" + id + ">Remove</td>");
                    }
                    out.println("</tr>");
                }
                out.println("<tr><td></td><td><a href=''>Update</a></td><td></td><td>Total</td><td>" + total + "</td><td><a href=ProductController?service=removeFromCart&pid=all>Remove All</td></tr>");

            %>
        </table>       


        <h2><a href="ProductController?service=checkout">Check-out</h2>
        <h2><a href="ProductController">List Product</h2>

    </body>
</html>