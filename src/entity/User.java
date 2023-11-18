package entity;
import java.time.LocalDateTime;

public class User implements User {
    private String username;
    private String password;
    private final LocalDateTime creationTime;

    public User(String username, String password, LocalDateTime creationTime){
        this.username = username;
        this.password = password;
        this.creationTime = creationTime;
    }

    public String getUsername(){return username;}

    public String getPassword(){return password;}

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

}
