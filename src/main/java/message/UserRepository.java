package message;

import javax.sql.DataSource;
import java.io.OptionalDataException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository {
    private DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertUser(String username) {
        try (Connection conn = dataSource.getConnection()) {
            try {
                saveUser(username, conn);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Insertation not succeeded!", ex);
            }
        } catch (SQLException sqle) {
            throw new IllegalStateException("Wrong connection.", sqle);
        }
    }

    private void saveUser(String username, Connection conn) {
        try (PreparedStatement stmt = conn.prepareStatement("insert into users(username) values (?);")) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException sqle) {
            throw new IllegalStateException("save to table not succed.", sqle);
        }
    }

    public Optional<User> findUserByName(String username) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("select * from users where username = ?;")) {
            stmt.setString(1, username);
            return getUserByName(stmt, conn);
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot select user.", sqle);
        }
    }

    private Optional<User> getUserByName(PreparedStatement stmt, Connection conn) {
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                long id = rs.getLong("id");
                String username = rs.getString("username");
//                Optional<User> result = Optional.of(new User(id, username));
//                return result;
                return Optional.of(new User(id, username));
            }
        } catch (SQLException sqle) {
            throw new IllegalArgumentException("No user optional with this id.");
        }
        return Optional.empty();
    }

}
