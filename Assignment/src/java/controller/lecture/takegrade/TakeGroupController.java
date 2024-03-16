package controller.lecture.takegrade;

import controller.auth.BaseRequiredAuthenticationController;
import dal.GradeDBContext;
import entity.Account;
import entity.Group;
import entity.Subject;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import util.DateTimeHelper;

public class TakeGroupController extends BaseRequiredAuthenticationController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
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

        String subname = req.getParameter("subname");
        String lid = req.getParameter("id");
        GradeDBContext db = new GradeDBContext();
        ArrayList<Group> group = db.listGroup(lid, subname);
        ArrayList<Subject> subs = db.listSublect(lid, from, to);
        req.setAttribute("from", from);
        req.setAttribute("to", to);
        req.setAttribute("dates", dates);
        req.setAttribute("group", group);
        req.setAttribute("subs", subs);
        req.getRequestDispatcher("../fap/lecture/grade.jsp").forward(req, resp);
    }
}
