/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.management.system;
import java.sql.*;
/**
 *
 * @author debaj
 */
public class Conn {
    Connection c;
    Statement s;
    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "#Access2Admin");
            s = c.createStatement();
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return c;
    }
}
