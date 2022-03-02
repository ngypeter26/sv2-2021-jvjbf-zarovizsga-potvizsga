package message;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageRepository {
    private DataSource dataSource;

    public MessageRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertMessage(long sender_id, long receiver_id, String message) {
        try (Connection conn = dataSource.getConnection()) {
            try {
                saveMessage(sender_id, receiver_id, message, conn);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Insertation not succeeded!", ex);
            }
        } catch (SQLException sqle) {
            throw new IllegalStateException("Wrong connection.", sqle);
        }
    }

    private void saveMessage(long sender_id, long receiver_id, String message, Connection conn) {
        try (PreparedStatement stmt = conn.prepareStatement("insert into messages(sender_id, receiver_id, message) values (?, ?, ?);")) {
            stmt.setLong(1, sender_id);
            stmt.setLong(2, receiver_id);
            stmt.setString(3, message);
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            throw new IllegalStateException("save to table not succed.", sqle);
        }
    }

    public List<String> findMessagesBySenderId(long sender_id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("select * from messages where sender_id = ?;")) {
            stmt.setLong(1, sender_id);
            return getMessageBySenderId(stmt, conn);
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot select message.", sqle);
        }
    }

    private List<String> getMessageBySenderId(PreparedStatement stmt, Connection conn) {
        List<String> messages = new ArrayList<>();

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String message = rs.getString("message");
                messages.add(message);
            }
            return messages;
        } catch (SQLException sqle) {
            throw new IllegalArgumentException("No message with this id.");
        }
    }

}
