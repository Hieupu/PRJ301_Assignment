/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Group;
import entity.Lecture;
import entity.Room;
import entity.Session;
import entity.Slot;
import entity.Subject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class SessionDBContext extends DBContext<Session> {

    public ArrayList<Session> list() throws SQLException {
        ArrayList<Session> ses = new ArrayList<>();
        PreparedStatement stm = null;
        String sql = "SELECT su.name AS subject_name, sl.value, sl.duration, sl.day, l.name AS lecture_name, g.name AS group_name, r.code "
                + "FROM [Group] g "
                + "JOIN [Session] se ON se.gid = g.id "
                + "JOIN Slot sl ON sl.id = se.slid "
                + "JOIN Subject su ON su.id = g.suid "
                + "JOIN Lecture l ON l.id = se.lid "
                + "JOIN Room r ON r.id = se.rid";
        try {
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slot sl = new Slot();
                sl.setValue(rs.getInt("value"));
                sl.setDuration(rs.getString("duration"));
                sl.setDay(rs.getInt("day"));

                Subject su = new Subject();
                su.setName(rs.getString("subject_name"));

                Lecture l = new Lecture();
                l.setName(rs.getString("lecture_name"));

                Group g = new Group();
                g.setName(rs.getString("group_name"));

                Room r = new Room();
                r.setCode(rs.getString("code"));

                // Create a Session object and add it to the list
                Session session = new Session();
                session.setSlot(sl);
                session.setSubject(su);
                session.setLecture(l);
                session.setGroup(g);
                session.setRoom(r);

                ses.add(session);
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
        }
        return ses;
    }
}
