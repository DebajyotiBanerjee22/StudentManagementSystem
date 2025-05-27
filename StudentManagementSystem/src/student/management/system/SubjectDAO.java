package student.management.system;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO {
    private Conn conn;

    public SubjectDAO() {
        conn = new Conn();
    }
    public boolean addSubject(Subject subject) {
        String sql = "INSERT INTO subject (subject_name) VALUES (?)";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setString(1, subject.getSubjectName());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding subject");
            e.printStackTrace();
            return false;
        }
    }
    public Subject getSubjectById(int subjectId) {
        String sql = "SELECT * FROM subject WHERE subject_id = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setInt(1, subjectId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Subject(
                        rs.getInt("subject_id"),
                        rs.getString("subject_name")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching subject by ID: " + subjectId);
            e.printStackTrace();
        }
        return null;
    }

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM subject";
        try (Statement stmt = conn.c.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                subjects.add(new Subject(
                    rs.getInt("subject_id"),
                    rs.getString("subject_name")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all subjects");
            e.printStackTrace();
        }
        return subjects;
    }

    public boolean updateSubject(Subject subject) {
        String sql = "UPDATE subject SET subject_name = ? WHERE subject_id = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setString(1, subject.getSubjectName());
            ps.setInt(2, subject.getSubjectId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating subject with ID: " + subject.getSubjectId());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSubject(int subjectId) {
        String sql = "DELETE FROM subject WHERE subject_id = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setInt(1, subjectId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting subject with ID: " + subjectId);
            e.printStackTrace();
            return false;
        }
    }
}