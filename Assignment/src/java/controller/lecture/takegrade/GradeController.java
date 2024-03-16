/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.lecture.takegrade;

import controller.auth.BaseRequiredAuthenticationController;
import dal.GradeDBContext;
import entity.Account;
import entity.Grade;
import entity.Group;
import entity.Student;
import entity.Subject;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
public class GradeController extends BaseRequiredAuthenticationController {

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
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
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
        String groupname = req.getParameter("groupname");
        String subname = req.getParameter("subname");
        String sid = req.getParameter("sid");
        String lid = req.getParameter("id");
        String isgrade = req.getParameter("isgrade");
        GradeDBContext db = new GradeDBContext();
        ArrayList<Grade> gradeList = db.listGrade(subname, sid, groupname);
        ArrayList<Student> student = db.listStudent(subname, groupname);
        ArrayList<Group> group = db.listGroup(lid, subname);
        ArrayList<Subject> subs = db.listSublect(lid, from, to);

        String[] items = new String[gradeList.size()];
        String[] categories = new String[gradeList.size()];
        String[] weights = new String[gradeList.size()];

        for (int i = 0; i < gradeList.size(); i++) {
            Grade grade = gradeList.get(i);
            items[i] = grade.getItem();
            categories[i] = grade.getCategory();
            weights[i] = String.valueOf(grade.getWeight());
        }

        for (int i = 0; i < categories.length; i++) {

            String paramName = categories[i] + "_" + items[i] + "_" + weights[i];

            String paramValue = req.getParameter(paramName);
            try {
                db.gradeStudent(paramValue, categories[i], items[i], sid, subname);
            } catch (SQLException ex) {
                Logger.getLogger(GradeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                db.graded(isgrade, sid, groupname);
            } catch (SQLException ex) {
                Logger.getLogger(GradeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        req.setAttribute("from", from);
        req.setAttribute("to", to);
        req.setAttribute("dates", dates);
        req.setAttribute("group", group);
        req.setAttribute("subs", subs);
        req.setAttribute("student", student);
        resp.sendRedirect("/Assignment/lecture/takestudent?id="+lid+"&subname="+subname+"&groupname="+groupname+"");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
    }
}