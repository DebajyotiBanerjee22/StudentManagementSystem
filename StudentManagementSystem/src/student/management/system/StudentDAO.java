package student.management.system;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Conn conn;

    public StudentDAO() {
        conn = new Conn();
    }

    public boolean addStudent(Student student) {
        String sql = "INSERT INTO student (roll_no, name, semester, year_of_admission, expected_passing_year, stream_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setString(1, student.getRollNo());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getSemester());
            ps.setInt(4, student.getYearOfAdmission());
            ps.setInt(5, student.getExpectedPassingYear());
            ps.setInt(6, student.getStreamId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding student: " + student.getRollNo());
            e.printStackTrace();
            return false;
        }
    }

    public Student getStudentByRollNo(String rollNo) {
        String sql = "SELECT * FROM student WHERE roll_no = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setString(1, rollNo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                        rs.getString("roll_no"),
                        rs.getString("name"),
                        rs.getInt("semester"),
                        rs.getInt("year_of_admission"),
                        rs.getInt("expected_passing_year"),
                        rs.getInt("stream_id")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching student by roll number: " + rollNo);
            e.printStackTrace();
        }
        return null;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (ResultSet rs = conn.s.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                    rs.getString("roll_no"),
                    rs.getString("name"),
                    rs.getInt("semester"),
                    rs.getInt("year_of_admission"),
                    rs.getInt("expected_passing_year"),
                    rs.getInt("stream_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all students");
            e.printStackTrace();
        }
        return students;
    }

    public boolean updateStudent(Student student) {
        String sql = "UPDATE student SET name = ?, semester = ?, year_of_admission = ?, expected_passing_year = ?, stream_id = ? WHERE roll_no = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setInt(2, student.getSemester());
            ps.setInt(3, student.getYearOfAdmission());
            ps.setInt(4, student.getExpectedPassingYear());
            ps.setInt(5, student.getStreamId());
            ps.setString(6, student.getRollNo());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating student: " + student.getRollNo());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStudent(String rollNo) {
        try {

            StudentSubjectDAO studentSubjectDAO = new StudentSubjectDAO();
            studentSubjectDAO.deleteSubjectsByRollNo(rollNo);

            ContactDAO contactDAO = new ContactDAO();
            contactDAO.deleteContactByRollNo(rollNo);


            String sql = "DELETE FROM student WHERE roll_no = ?";
            try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
                ps.setString(1, rollNo);
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error deleting student: " + rollNo);
            e.printStackTrace();
            return false;
        }
    }

    public boolean isStudentExists(String rollNo) {
        String sql = "SELECT 1 FROM student WHERE roll_no = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setString(1, rollNo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error checking existence of student: " + rollNo);
            e.printStackTrace();
        }
        return false;
    }
}
