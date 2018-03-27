/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Bill;
import Entity.BillDetail;
import Entity.Customer;
import Model.BillDAO;
import Model.BillDetailDAO;
import Model.DBConnection;
import Model.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kudo
 */
public class ProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DBConnection db = new DBConnection();
        ProductDAO dao = new ProductDAO(db);
        HttpSession session = request.getSession();
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("service");
            // null pointer exception
            if (service == null) {
                service = "listProduct";
            }
            if (service.equals("listProduct")) {
                //change model
//                String sql = "select * from Product";
                //   + " where a.status=1 and b.status=1";
                String catID = request.getParameter("catID");
                String sql = "select * from Category";
                String productID = request.getParameter("pid");
                String psql = "";
                if (productID != null) {
                    psql = " AND b.pid=" + productID;
                }
                ResultSet crs = db.getData(sql);
                if (catID == null || catID.equalsIgnoreCase("") || catID.equalsIgnoreCase("all")) {
                    sql = "select * from category as a inner join Product as b on a.catID=b.catid "
                            + " where a.status=1 and b.status=1";
                } else {
                    sql = "select * from category as a inner join Product as b on a.catID=b.catid "
                            + " where a.status=1 and b.status=1 and a.catID='" + catID + "'";
                }
                sql += psql;
                ResultSet rs = db.getData(sql);
                // ResultSet rs = db.getData(sql);
//                while(rs.next())
//                out.print(rs.getString(1));
                //Set value for view

                request.setAttribute("pkq", rs);
                request.setAttribute("ckq", crs);
                //request.setAttribute("loginBean", (LoginBean) session.getAttribute("loginBean"));
                //call view
                //set view: JSP 
                RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
                //run

                dis.forward(request, response);

            }
            if (service.equals("preUpdate")) {
                String pid = request.getParameter("pid");
                String cid = request.getParameter("cid");
                String Csql = "select * from Category where status=1";
                String Psql = "select * from Product where status=1 and pid=" + Integer.parseInt(pid);
                ResultSet Crs = db.getData(Csql);
                ResultSet Prs = db.getData(Psql);

                out.print("<form action=\"ProductController\" method=\"POST\">\n"
                        + "<table border=\"0\">\n"
                        + "<tr>\n"
                        + "<th>Category Name</th>\n"
                        + "<th><select name=\"cid\">");
                while (Crs.next()) {
                    out.print("<option value=" + Crs.getString(1) + " " + (Crs.getString(1).equals(cid) ? "selected" : "") + ">" + Crs.getString(2) + "</option>");
                }
                out.print(" </select></th>");
                while (Prs.next()) {
                    out.print("<tr>\n"
                            + "<th>Product ID</th>\n"
                            + "<th><input type=\"text\" name=\"pid\" value=\"" + Prs.getString(1) + "\" readonly /></th>\n"
                            + "</tr>\n"
                            + "<tr>\n"
                            + "<th>Product Name</th>\n"
                            + "<th><input type=\"text\" name=\"pname\" value=\"" + Prs.getString(2) + "\" /></th>\n"
                            + "</tr>"
                            + "<tr>"
                            + "<th>Quantity</th><th><input type= \"number\" name = \"quantity\" value = \"" + Prs.getInt(3) + "\"/></th></tr>"
                            + "<tr>"
                            + "<th>Price</th><th><input type= \"number\" name = \"price\" value = \"" + Prs.getInt(4) + "\"/></th></tr>"
                            + "<tr>"
                            + "<th>Image</th><th><input type= \"text\" name = \"image\" value = \"" + Prs.getString(5) + "\"/></th></tr>"
                            + "<tr>"
                            + "<th>Description</th><th><input type= \"text\" name = \"description\" value = \"" + Prs.getString(6) + "\"/></th></tr>"
                    );
                }
                out.print(" <tr><input type=\"hidden\" name=\"service\" value=\"updateProduct\">"
                        + " <th><input type=\"submit\" value=\"Update\"></th></tr>");
                out.print("</table></form>");

            }
            if (service.equalsIgnoreCase("updateProduct")) {
                String pid = request.getParameter("pid");
                String cid = request.getParameter("cid");
                String pname = request.getParameter("pname");
                String quantity = request.getParameter("quantity");
                String price = request.getParameter("price");
                String image = request.getParameter("image");
                String description = request.getParameter("description");
                dao.updateProduct(pname, Integer.parseInt(quantity), Integer.parseInt(price), image, description, cid, Integer.parseInt(pid));
                response.sendRedirect("ProductController");
            }
            if (service.equalsIgnoreCase("addToCart")) {
                //out.print(session);
                String id = request.getParameter("pid");
                Object value = session.getAttribute(id);

                //the first time the product is selected
                if (value == null) {
                    //put name-value pair into session object (see HttpSession)                        
                    session.setAttribute(id, "1");
                } //the second/third... time the product is selected
                else {
                    int count = Integer.parseInt(value.toString()) + 1;
                    //put name-value pair into session object (see HttpSession)
                    session.setAttribute(id, String.valueOf(count));

                }
                response.sendRedirect("ProductController");
            }
            if (service.equalsIgnoreCase("removeFromCart")) {
                String id = request.getParameter("pid");
                session.setAttribute(id, null);
                if (id.equalsIgnoreCase("all")) {
                    Customer temp = (Customer) session.getAttribute("User");
                    session.invalidate();
                    HttpSession session1 = request.getSession();
                    session1.setAttribute("User", temp);
                }
                response.sendRedirect("ProductController");
            }

            if (service.equalsIgnoreCase("checkout")) {
                java.util.Enumeration em = session.getAttributeNames();
                if (!em.hasMoreElements()) {
                    response.sendRedirect("ProductController");
                }
                int total = 0;
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                Bill bill = new Bill(sqlDate, 2, "123", "456", "789", "Unknown");

                BillDAO billDao = new BillDAO(db);
                int x = billDao.insertBill(bill);
                int billID = billDao.getLastBill();
                bill.setID(billID);
                // out.println("<h1>" + x + " " + billID + "</h1>");
                BillDetailDAO bdDao = new BillDetailDAO(db);

                for (; em.hasMoreElements();) {
                    String id = em.nextElement().toString();
                    //get value from session object (see HttpSession)
                    if (id.equalsIgnoreCase("User")) {
                        continue;
                    }
                    String count = session.getAttribute(id).toString();
                    String sql = "select * from Product where status=1 and pid=" + Integer.parseInt(id);
                    ResultSet rs = db.getData(sql);
                    while (rs.next()) {
//                        out.println("<h1>"+rs.getInt(1)+"</h1>");
                        BillDetail billDetail = new BillDetail(billID, rs.getInt(1), Integer.parseInt(count), rs.getInt(4));
                        total += rs.getInt(4) * Integer.parseInt(count);;
                        bdDao.insertBillDetail(billDetail);
                    }
                }
                bill.setTotal(total);
                billDao.updateBill(bill);
                Customer temp = (Customer) session.getAttribute("User");
                session.invalidate();
                HttpSession session1 = request.getSession();
                session1.setAttribute("User", temp);
                response.sendRedirect("ProductController");

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
