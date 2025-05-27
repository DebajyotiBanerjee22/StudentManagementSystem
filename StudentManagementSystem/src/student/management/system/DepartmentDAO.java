package student.management.system;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    private Conn conn;

    public DepartmentDAO() {
        conn = new Conn();
    }

    public boolean addDepartment(Department department) {
        String sql = "INSERT INTO department (dept_name) VALUES (?)";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setString(1, department.getDeptName());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding department");
            e.printStackTrace();
            return false;
        }
    }

    public List<Department> getAllDepartments() {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT dept_id, dept_name FROM department";
        try (Statement stmt = conn.c.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Department(
                    rs.getInt("dept_id"),
                    rs.getString("dept_name")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving departments");
            e.printStackTrace();
        }
        return list;
    }
    public Department getDepartmentById(int deptId) {
        String sql = "SELECT dept_id, dept_name FROM department WHERE dept_id = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setInt(1, deptId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Department(
                        rs.getInt("dept_id"),
                        rs.getString("dept_name")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving department by ID");
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateDepartment(Department department) {
        String sql = "UPDATE department SET dept_name = ? WHERE dept_id = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setString(1, department.getDeptName());
            ps.setInt(2, department.getDeptId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating department");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDepartment(int deptId) {
        String sql = "DELETE FROM department WHERE dept_id = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setInt(1, deptId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting department");
            e.printStackTrace();
            return false;
        }
    }
}
