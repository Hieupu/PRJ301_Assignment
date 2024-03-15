package controller.lecture;

import controller.auth.BaseRequiredAuthenticationController;
import dal.AttendDBContext;
import entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class AttendanceController extends BaseRequiredAuthenticationController {

    AttendDBContext attendDBContext = new AttendDBContext();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        String subjectName = req.getParameter("subject");
        int slotValue = Integer.parseInt(req.getParameter("value"));
        int slotDay = Integer.parseInt(req.getParameter("day"));

        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("attendance_")) {
                String studentID = paramName.substring("attendance_".length());
                String attendance = req.getParameter(paramName);
                String note = req.getParameter("des_" + studentID);
                String istaken = req.getParameter("taken");
                attendDBContext.insertAttend(subjectName, slotValue, slotDay, studentID, note, attendance, istaken);
            }
        }

        resp.sendRedirect("../fap/lecture/success.jsp?id=" + account.getId());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
