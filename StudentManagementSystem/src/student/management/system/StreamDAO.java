package student.management.system;
import java.sql.*;
import java.util.*;

public class StreamDAO {
    private Conn conn;

    public StreamDAO() {
        conn = new Conn();
    }

    public boolean addStream(Stream stream) {
        String sql = "INSERT INTO stream (stream_name, dept_id) VALUES (?, ?)";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setString(1, stream.getStreamName());
            ps.setInt(2, stream.getDeptId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding stream.");
            e.printStackTrace();
            return false;
        }
    }
    public Stream getStreamById(int streamId) {
        String sql = "SELECT * FROM stream WHERE stream_id = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setInt(1, streamId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Stream(
                        rs.getInt("stream_id"),
                        rs.getString("stream_name"),
                        rs.getInt("dept_id")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching stream by ID: " + streamId);
            e.printStackTrace();
        }
        return null;
    }
    public List<Stream> getAllStreams() {
        List<Stream> streams = new ArrayList<>();
        String sql = "SELECT * FROM stream";
        try (Statement stmt = conn.c.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                streams.add(new Stream(
                    rs.getInt("stream_id"),
                    rs.getString("stream_name"),
                    rs.getInt("dept_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all streams");
            e.printStackTrace();
        }
        return streams;
    }

    public boolean updateStream(Stream stream) {
        String sql = "UPDATE stream SET stream_name = ?, dept_id = ? WHERE stream_id = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setString(1, stream.getStreamName());
            ps.setInt(2, stream.getDeptId());
            ps.setInt(3, stream.getStreamId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating stream with ID: " + stream.getStreamId());
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteStream(int streamId) {
        String sql = "DELETE FROM stream WHERE stream_id = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(sql)) {
            ps.setInt(1, streamId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting stream with ID: " + streamId);
            e.printStackTrace();
            return false;
        }
    }
}
