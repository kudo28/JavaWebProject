/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Category;
import Entity.Product;
import Model.CategoryDAO;
import Model.DBConnection;
import Model.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kudo
 */
public class CategoryController extends HttpServlet {

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
        CategoryDAO dao = new CategoryDAO(db);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String service = request.getParameter("service");
            // null pointer exception
            if (service == null) {
                service = "listCategory";
            }
            if (service.equalsIgnoreCase("insertCategory")) {

                //getdata
                String catID = request.getParameter("catID");
                String catName = request.getParameter("catName");

                //check data
                //proccess: add customer
                Category cat = new Category(catID, catName);
                String sql = "select * from category where catid='" + catID + "'";
                ResultSet rs = db.getData(sql);
                if (rs.next()) {
                    out.print("Category exists");
                }
                int n = dao.insertCategory(cat);
                response.sendRedirect("CategoryController?service=listCategory");
//                if (n > 0) {
//                    out.print("inserted");
//                }
            }
            if (service.equalsIgnoreCase("listCategory")) {
                String sql = "select * from Category";
                ResultSet rs = db.getData(sql);
                request.setAttribute("kq", rs);
                request.setAttribute("title", "Category Manager");
                //call view
                //set view: JSP 
                RequestDispatcher dis = request.getRequestDispatcher("/Server/Category.jsp");
                //run
                dis.forward(request, response);
//                out.print(" <table border=\"1\">\n"
//                        + "            <caption>CategoryList</caption>\n"
//                        + "                <tr>\n"
//                        + "                    <th>ID</th>\n"
//                        + "                    <th>Name</th>\n"
//                        + "                    <th>Status</th>\n"
//                        + "                    <th>Update</th>\n"
//                        + "                    <th>Delete</th>\n"
//                        + "                    <th> Change status"
//                        + "                </tr>");
//                while (rs.next()) {
//                    out.print("<tr>");
//                    out.print(" <td>" + rs.getString(1) + "</td>");
//                    out.print(" <td>" + rs.getString(2) + "</td>");
//                    out.print(" <td>" + (rs.getInt(3) == 1 ? "Active" : "Deactive") + "</td>");
//                    out.print(" <td><a href=CategoryController?service=preupdate&catid=" + rs.getString(1) + ">update</a></td>");
//                    out.print(" <td><a href=CategoryController?service=delete&catid=" + rs.getString(1) + ">remove</a></td>");
//                    out.print(" <td><a href=CategoryController?service=changeStatus&catid=" + rs.getString(1) + ">Change Status</a></td>");
//                    out.print("</tr>");
//                }
//
//                out.print("</table>");
            }
            if (service.equalsIgnoreCase("preupdate")) {
                String catID = request.getParameter("catid");
                out.print("<form action=\"CategoryController\" method=\"POST\">\n"
                        + "<table border=\"0\">\n"
                        + "<tr>\n"
                        + "<th>Category ID</th>\n"
                        + "<th><input type=\"text\" name=\"catid\" value=\"" + catID + "\" readonly /></th></tr>\n"
                        + "<tr><th>Category Name: </th><th> <input type=\"text\" name=\"catName\" id=\"catName\" > </th></tr>  "
                        + " <input type=\"hidden\" name=\"service\" value=\"updateCategory\">"
                        + " <th><input type=\"submit\" value=\"Update\"></th>"
                        + "</table></form>");
            }
            if (service.equalsIgnoreCase("updateCategory")) {
                String catID = request.getParameter("catid");
                String catName = request.getParameter("catName");
                dao.updateCategory(catID, catName);
                response.sendRedirect("CategoryController");
            }
            if (service.equalsIgnoreCase("changeStatus")) {
                String catID = request.getParameter("catid");
                dao.changeStatus(catID);
                response.sendRedirect("CategoryController");
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
