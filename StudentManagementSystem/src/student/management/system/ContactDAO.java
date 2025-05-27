package student.management.system;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    private Conn conn;

    public ContactDAO() {
        conn = new Conn();
    }

    public boolean addContact(Contact contact) {
        String sql = "INSERT INTO contact (roll_no, reg_no, phone, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setString(1, contact.getRollNo());
            ps.setString(2, contact.getRegNo());
            ps.setString(3, contact.getPhone());
            ps.setString(4, contact.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding contact for roll number: " + contact.getRollNo());
            e.printStackTrace();
            return false;
        }
    }

    public Contact getContactByRollNo(String rollNo) {
        String sql = "SELECT * FROM contact WHERE roll_no = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setString(1, rollNo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Contact(
                        rs.getString("roll_no"),
                        rs.getString("reg_no"),
                        rs.getString("phone"),
                        rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching contact for roll number: " + rollNo);
            e.printStackTrace();
        }
        return null;
    }

    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM contact";
        try (Statement stmt = conn.c.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                contacts.add(new Contact(
                    rs.getString("roll_no"),
                    rs.getString("reg_no"),
                    rs.getString("phone"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all contacts");
            e.printStackTrace();
        }
        return contacts;
    }

    public boolean updateContact(Contact contact) {
        String sql = "UPDATE contact SET reg_no = ?, phone = ?, email = ? WHERE roll_no = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setString(1, contact.getRegNo());
            ps.setString(2, contact.getPhone());
            ps.setString(3, contact.getEmail());
            ps.setString(4, contact.getRollNo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating contact for roll number: " + contact.getRollNo());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteContactByRollNo(String rollNo) {
        String sql = "DELETE FROM contact WHERE roll_no = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setString(1, rollNo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting contact for roll number: " + rollNo);
            e.printStackTrace();
            return false;
        }
    }

    public boolean isContactExists(String rollNo) {
        String sql = "SELECT 1 FROM contact WHERE roll_no = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setString(1, rollNo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error checking contact existence for roll number: " + rollNo);
            e.printStackTrace();
            return false;
        }
    }
}
