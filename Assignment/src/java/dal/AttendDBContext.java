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

    public void insertAttend(String subjectName, int slotValue, int slotDay, String studentID, String des, String isattend, String istaken) {
        try {
            PreparedStatement stm;
            String sql = """
                         SELECT se.id FROM Session se 
                         JOIN [Group] g ON se.gid = g.id
                         JOIN Slot sl ON sl.id = se.slid
                         JOIN Subject su ON su.id = g.suid
                         WHERE su.name = ? AND sl.value = ? AND sl.day =?""";
            stm = connection.prepareStatement(sql);
            stm.setString(1, subjectName);
            stm.setInt(2, slotValue);
            stm.setInt(3, slotDay);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String seId = rs.getString("id");

                PreparedStatement stm_2;
                String sql_2 = """
                               UPDATE [dbo].[Attendance]
                                  SET [des] = ?
                                     ,[isattend] = ?
                                WHERE [seid] = ?
                                     AND [sid] = ?""";
                stm_2 = connection.prepareStatement(sql_2);
                stm_2.setString(3, seId);
                stm_2.setString(4, studentID);
                stm_2.setString(1, des);
                stm_2.setString(2, isattend);
                stm_2.executeUpdate();

                PreparedStatement stm_3;
                String sql_3 = """
                               UPDATE [dbo].[Session]
                                  SET [istaken] = ?
                                WHERE [id] = ?""";
                stm_3 = connection.prepareStatement(sql_3);
                stm_3.setString(2, seId);
                stm_3.setString(1, istaken);
                stm_3.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
