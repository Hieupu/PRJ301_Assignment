package controller;

import dal.StudentDBContext;
import entity.Student;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import controller.auth.BaseRequiredAuthenticationController;
import entity.Account;

public class ViewAttendController extends BaseRequiredAuthenticationController {

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String subjectName = request.getParameter("subject");
            int slotValue = Integer.parseInt(request.getParameter("value"));
            int slotDay = Integer.parseInt(request.getParameter("day"));

            StudentDBContext stuDB = new StudentDBContext();
            ArrayList<Student> students = stuDB.show(subjectName, slotValue, slotDay);

            request.setAttribute("students", students);
            request.setAttribute("subjectName", subjectName);
            request.getRequestDispatcher("./fap/lecture/viewattend.jsp").forward(request, response);
        } catch (ServletException | IOException | NumberFormatException | SQLException ex) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<h2>Xảy ra lỗi khi xử lý yêu cầu:</h2>");
            out.println("<p>" + ex.getMessage() + "</p>");
            ex.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
