/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecture.takegrade;

import controller.auth.BaseRequiredAuthenticationController;
import dal.GradeDBContext;
import entity.Account;
import entity.Subject;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import util.DateTimeHelper;

/**
 *
 * @author Admin
 */
public class TakeSubjectController extends BaseRequiredAuthenticationController {

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        try {

            String lid = req.getParameter("id");
            String raw_from = req.getParameter("from");
            String raw_to = req.getParameter("to");
            Date today = new Date();
            java.sql.Date from = null;
            java.sql.Date to = null;

            if (raw_from == null) {
                from = DateTimeHelper.convertUtilDateToSqlDate(DateTimeHelper.getWeekStart(today));
            } else {
                from = java.sql.Date.valueOf(raw_from);
            }

            if (raw_to == null) {
                to = DateTimeHelper.convertUtilDateToSqlDate(DateTimeHelper.
                        addDaysToDate(DateTimeHelper.getWeekStart(today), 6));
            } else {
                to = java.sql.Date.valueOf(raw_to);
            }

            ArrayList<java.sql.Date> dates = DateTimeHelper.getDatesBetween(from, to);

            GradeDBContext db = new GradeDBContext();
            ArrayList<Subject> subs = db.listSublect(lid, from, to);

            req.setAttribute("from", from);
            req.setAttribute("to", to);
            req.setAttribute("dates", dates);
            req.setAttribute("subs", subs);
            req.getRequestDispatcher("../fap/lecture/grade.jsp").forward(req, resp);
        } catch (NumberFormatException ex) {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<h2>Xảy ra lỗi khi xử lý yêu cầu:</h2>");
            out.println("<p>" + ex.getMessage() + "</p>");
            ex.printStackTrace(out);
        }
    }

}
