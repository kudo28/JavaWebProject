/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.BillDAO;
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
public class BillController extends HttpServlet {

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
        BillDAO dao = new BillDAO(db);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            if (service == null) {
                service = "listBill";
            }
            if (service.equalsIgnoreCase("listBill")) {
                String sql = "Select bid,c.cname,datecreate,total,b.status from bill b inner join customer c on b.Cid = c.cid";
                ResultSet rs = db.getData(sql);
                request.setAttribute("kq", rs);
                //request.setAttribute("loginBean", (LoginBean) session.getAttribute("loginBean"));
                //call view
                //set view: JSP 
                RequestDispatcher dis = request.getRequestDispatcher("/AdminPage.jsp");
                //run
                dis.forward(request, response);

            }
            if (service.equalsIgnoreCase("preupdate")) {
                String bid = request.getParameter("bid");
                String ssql = "SELECT [bid]\n"
                        + "      ,[datecreate]\n"
                        + "      ,[status]\n"
                        + "      ,[Cid]\n"
                        + "      ,[total]\n"
                        + "      ,[recName]\n"
                        + "      ,[recPhone]\n"
                        + "      ,[recEmail]\n"
                        + "      ,[infor]\n"
                        + "  FROM [Bill] where bid=" + bid;
                String psql = "SELECT bd.[pid],\n"
                        + "	p.pname,\n"
                        + "      bd.[quantity]      \n"
                        + "  FROM [BillDetail] bd inner join Product p on bd.pid=p.pid\n"
                        + "where bid ="+bid;
                ResultSet rs = db.getData(ssql);
                ResultSet prs = db.getData(psql);

                request.setAttribute("bkq", rs);
                request.setAttribute("pkq", prs);
                //call view
                //set view: JSP 
                RequestDispatcher dis = request.getRequestDispatcher("/Server/BillDetail.jsp");
                //run
                dis.forward(request, response);

            }
            if (service.equalsIgnoreCase("updateBill")) {
                String bid = request.getParameter("bid");
                String cid = request.getParameter("cid");
                String rname = request.getParameter("rname");
                String rphone = request.getParameter("rphone");
                String rmail = request.getParameter("rmail");
                String descript = request.getParameter("description");
                String status = request.getParameter("status");
                dao.updateBill(bid, cid, rname, rphone, rmail, descript, status);
                response.sendRedirect("BillController");
            }
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
