/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.student.viewgrade;

import controller.auth.BaseRequiredAuthenticationController;
import dal.GradeDBContext;
import entity.Account;
import entity.Grade;
import entity.Subject;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DateTimeHelper;

/**
 *
 * @author Admin
 */
public class ViewGradeController extends BaseRequiredAuthenticationController {

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
            float avg = 0;
            int isgrade;
            String sid = req.getParameter("id");
            String subname = req.getParameter("subname");
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
            ArrayList<Subject> subject = db.studentSubject(sid, from, to);
            ArrayList<Grade> grade = db.viewgrade(sid, subname);
            avg = calculateTotal(grade);
            isgrade = db.checkIsgrade(sid, subname, from, to);

            req.setAttribute("isgrade", isgrade);
            req.setAttribute("avg", avg);
            req.setAttribute("from", from);
            req.setAttribute("to", to);
            req.setAttribute("dates", dates);
            req.setAttribute("subject", subject);
            req.setAttribute("grade", grade);
            req.getRequestDispatcher("../fap/student/viewgrade.jsp").forward(req, resp);
        } catch (NumberFormatException ex) {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<h2>Xảy ra lỗi khi xử lý yêu cầu:</h2>");
            out.println("<p>" + ex.getMessage() + "</p>");
            ex.printStackTrace(out);
        } catch (SQLException ex) {
            Logger.getLogger(ViewGradeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public float calculateTotal(ArrayList<Grade> grade) {
        float total = 0;
        for (Grade g : grade) {
            int weight = g.getWeight();
            float studentGrade = Float.parseFloat(g.getGrade());
            total += studentGrade * (weight / 100.0);
        }
        return Float.parseFloat(String.format("%.1f", total));
    }
}
