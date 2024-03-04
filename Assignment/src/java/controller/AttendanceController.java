package controller;

import controller.auth.BaseRequiredAuthenticationController;
import dal.AttendDBContext;
import entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@WebServlet(name = "AttendanceServlet", urlPatterns = {"/attend"})
public class AttendanceController extends BaseRequiredAuthenticationController {

    AttendDBContext attendDBContext = new AttendDBContext();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String subjectName = req.getParameter("subject");
        int slotValue = Integer.parseInt(req.getParameter("value"));
        int slotDay = Integer.parseInt(req.getParameter("day"));

        // Iterate over parameters to extract attendance and note values for each student
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("attendance_")) {
                String studentID = paramName.substring("attendance_".length());
                String attendance = req.getParameter(paramName);
                String note = req.getParameter("des_" + studentID);
                int attendanceValue = "1".equals(attendance) ? 1 : 0;
                // Gọi phương thức insertAttend từ lớp AttendDBContext
                attendDBContext.insertAttend(subjectName, slotValue, slotDay, studentID, note, attendanceValue == 1 ? Boolean.TRUE : Boolean.FALSE);
            }
        }

        resp.sendRedirect("./fap/success.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
