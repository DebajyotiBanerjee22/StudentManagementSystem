package student.management.system;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentSubjectDAO {
    private Conn conn;

    public StudentSubjectDAO() {
        conn = new Conn();
    }

    public boolean addStudentSubject(StudentSubject ss) {
        String sql = "INSERT INTO student_subject (roll_no, subject_id) VALUES (?, ?)";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setString(1, ss.getRollNo());
            ps.setInt(2, ss.getSubjectId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding student-subject relation");
            e.printStackTrace();
            return false;
        }
    }

    public List<StudentSubject> getSubjectsByRollNo(String rollNo) {
        List<StudentSubject> list = new ArrayList<>();
        String sql = "SELECT * FROM student_subject WHERE roll_no = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setString(1, rollNo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new StudentSubject(
                        rs.getString("roll_no"),
                        rs.getInt("subject_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching subjects for roll number: " + rollNo);
            e.printStackTrace();
        }
        return list;
    }

    public List<StudentSubject> getAllStudentSubjects() {
        List<StudentSubject> list = new ArrayList<>();
        String sql = "SELECT * FROM student_subject";
        try (Statement stmt = conn.c.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new StudentSubject(
                        rs.getString("roll_no"),
                        rs.getInt("subject_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all student-subject mappings");
            e.printStackTrace();
        }
        return list;
    }

    public boolean deleteStudentSubject(String rollNo, int subjectId) {
        String sql = "DELETE FROM student_subject WHERE roll_no = ? AND subject_id = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setString(1, rollNo);
            ps.setInt(2, subjectId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting student-subject association");
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteSubjectsByRollNo(String rollNo) {
        String sql = "DELETE FROM student_subject WHERE roll_no = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setString(1, rollNo);
            ps.executeUpdate(); // no need to check row count unless you want to log it
            return true;
        } catch (SQLException e) {
            System.err.println("Error deleting subjects for roll number: " + rollNo);
            e.printStackTrace();
            return false;
        }
    }
}
