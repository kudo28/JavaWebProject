/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Customer;
import Model.CustomerDAO;
import Model.DBConnection;
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
public class CustomerController extends HttpServlet {

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

        /* TODO output your page here. You may use following sample code. */
        DBConnection db = new DBConnection();
        CustomerDAO dao = new CustomerDAO(db);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //get service
            String service = request.getParameter("service");
            // null pointer exception
            if (service == null) {
                service = "listCustomer";
            }
            if (service.equalsIgnoreCase("insertCustomer")) {

                //getdata
                String fname = request.getParameter("cName");
                String phone = request.getParameter("cPhone");
                String email = request.getParameter("cEmail");
                String userName = request.getParameter("cUsername");
                String password = request.getParameter("cPassword");
                //check data
                //proccess: add customer
                Customer cus = new Customer(fname, phone, email, userName, password);
                //check username is unique
                String sql = "select username from Customer where username='" + userName + "'";
                ResultSet rs = db.getData(sql);
                if (rs.next()) {
                    out.print("username is exist");
                }

                int n = dao.insertCustomer(cus);
                response.sendRedirect("ProductController");
            }
            if (service.equalsIgnoreCase("listCustomer")) {
                String status = request.getParameter("status");
                String sql;
                String searchParam = request.getParameter("searchParams");
                String searchQuery;
                if(status == null){
                    status = "2";
                }
                if (searchParam == null || searchParam.isEmpty()) {
                    searchQuery = "'%'";
                } else {
                    searchQuery = "'%" + searchParam + "%'";
                }
                if (!status.equals("1") && !status.equals("0")) {
                    sql = "select * from Customer where cname LIKE " + searchQuery;
                } else {
                    sql = "select * from Customer where status = " + status + " and cname LIKE " + searchQuery;
                }
                ResultSet rs = db.getData(sql);
               // out.print(sql);
                 //Set value for view
                request.setAttribute("kq", rs);
                request.setAttribute("title", "Customer List");
                request.setAttribute("status", status);
                //call view
                //set view: JSP 
                RequestDispatcher dis = request.getRequestDispatcher("/Customer.jsp");
                //run
                dis.forward(request, response);
//                out.print(" <table border=\"1\">\n"
//                        + "            <caption>CustomerList</caption>\n"
//                        + "                <tr>\n"
//                        + "                    <th>ID</th>\n"
//                        + "                    <th>Name</th>\n"
//                        + "                    <th>Phone</th>\n"
//                        + "                    <th>Email</th>\n"
//                        + "                    <th>UserName</th>\n"
//                        + "                    <th>Status</th>\n"
//                        + "                    <th>Update</th>\n"
//                        + "                    <th>Delete</th>\n"
//                        + "                    <th>Change Status </th>"
//                        + "                    <th>Reset Password </th>"
//                        + "                </tr>");
//                while (rs.next()) {
//                    out.print("<tr>");
//                    out.print(" <td>" + rs.getInt(1) + "</td>");
//                    out.print(" <td>" + rs.getString(2) + "</td>");
//                    out.print(" <td>" + rs.getString(3) + "</td>");
//                    out.print(" <td>" + rs.getString(4) + "</td>");
//                    out.print(" <td>" + rs.getString(5) + "</td>");
//                    out.print(" <td>" + (rs.getInt(7) == 1 ? "Active" : "Deactive") + "</td>");
//                    out.print(" <td><a href=CustomerController?service=update&cid=" + rs.getInt(1) + ">Update</a></td>");
//                    out.print(" <td><a href=CustomerController?service=delete&cid=" + rs.getInt(1) + ">Remove</a></td>");
//                    if (rs.getInt(7) == 1) {
//                        out.print(" <td><a href=CustomerController?service=deactivate&cid=" + rs.getInt(1) + ">Deactivate</a></td>");
//                    } else {
//                        out.print(" <td><a href=CustomerController?service=activate&cid=" + rs.getInt(1) + ">Activate</a></td>");
//                    }
//                    out.print(" <td><a href=CustomerController?service=resetPassword&cid=" + rs.getInt(1) + ">Reset Password</a></td>");
//                    out.print("</tr>");
//                }
//
//                out.print("</table>");
            }
            if (service.equalsIgnoreCase("deactivate") || service.equalsIgnoreCase("activate")) {
                String cid = request.getParameter("cid");
                dao.changeStatus(Integer.parseInt(cid));
                response.sendRedirect("CustomerController?service=listCustomer&status=2");
            }
            if (service.equalsIgnoreCase("resetPassword")) {
                String cid = request.getParameter("cid");
                dao.resetPassword(Integer.parseInt(cid));
                response.sendRedirect("CustomerController?service=displayCustomer");
            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
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
