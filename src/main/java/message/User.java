package message;

public class User {
    private String username;
    private long id;

    public User(String username) {
        this.username = username;
    }

    public User(long id, String username) {
        this.username = username;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public long getId() {
        return id;
    }
}
