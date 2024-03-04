/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Account;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class LoginDBContext extends DBContext<Account>{

    PreparedStatement stm = null;
    ResultSet rs = null;
    public Account checkLogin(String user, String password) throws Exception{
        String sql = "SELECT [user],pass,role FROM Account WHERE [user] = ? AND [pass] = ?";
            stm  = connection.prepareStatement(sql);
            stm.setString(1,user);
            stm.setString(2,password);
            rs = stm.executeQuery();
            while (rs.next()){
                Account acc = new Account();
                acc.setUser(user);
                acc.setPass(password);
                acc.setRole(rs.getString("role"));
                return acc;
            }
        
        return null;
        
    }
}

