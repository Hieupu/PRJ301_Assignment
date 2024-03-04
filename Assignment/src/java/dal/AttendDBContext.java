/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Attendance;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class AttendDBContext extends DBContext<Attendance> {

    public void insertAttend(String subjectName, int slotValue, int slotDay, String studentID, String des, Boolean isattend) {
        int attend;
        if(isattend){
            attend = 1;
        }else{
            attend = 0;
        }
    try {
        PreparedStatement stm = null;
        String sql = "SELECT se.id FROM Session se \n"
                + "JOIN [Group] g ON se.gid = g.id\n"
                + "JOIN Slot sl ON sl.id = se.slid\n"
                + "JOIN Subject su ON su.id = g.suid\n"
                + "WHERE su.name = ? AND sl.value = ? AND sl.day =?";
        stm = connection.prepareStatement(sql);
        stm.setString(1, subjectName);
        stm.setInt(2, slotValue);
        stm.setInt(3, slotDay);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            String seId = rs.getString("id");
            
            // Thực hiện INSERT Attendance với giá trị se.id đã lấy được
            PreparedStatement stm_2 = null;
            String sql_2 = "INSERT INTO [dbo].[Attendance]\n"
                    + "           ([seid]\n"
                    + "           ,[sid]\n"
                    + "           ,[des]\n"
                    + "           ,[isattend])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";      
            stm_2 = connection.prepareStatement(sql_2);
            stm_2.setString(1, seId);
            stm_2.setString(2, studentID); 
            stm_2.setString(3, des); 
            stm_2.setInt(4, attend); 
            stm_2.executeUpdate();
        }
    } catch (SQLException ex) {
        Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}
