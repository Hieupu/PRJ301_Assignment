/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.auth;

import dal.StudentLoginDBContext;
import entity.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public abstract class BaseRequiredAuthenticationController extends HttpServlet {

    private Account getAuthentication(HttpServletRequest req) {
        Account account = (Account) req.getSession().getAttribute("account");
        if (account == null) {
            String username = null;
            String password = null;
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cooky : cookies) {
                    if (cooky.getName().equals("username")) {
                        username = cooky.getValue();
                    }
                    if (cooky.getName().equals("password")) {
                        password = cooky.getValue();
                    }
                    if (username != null && password != null) {
                        break;
                    }
                }

                if (username != null && password != null) {
                    try {
                        StudentLoginDBContext db = new StudentLoginDBContext();
                        return db.checkLogin(username, password);
                    } catch (Exception ex) {
                        Logger.getLogger(BaseRequiredAuthenticationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }

        return account;
    }

    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = getAuthentication(req);
        if (account != null) {
            doPost(req, resp, account);
        } else {
            resp.getWriter().println("access denied!");
        }
    }

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = getAuthentication(req);
        if (account != null) {
            doGet(req, resp, account);
        } else {
            resp.getWriter().println("access denied!");
        }
    }

}
