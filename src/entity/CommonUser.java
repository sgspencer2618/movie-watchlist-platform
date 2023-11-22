package entity;

import java.time.LocalDateTime;

class CommonUser implements User {

    private final String username;
    private final String password;
    private final LocalDateTime creationTime;

    /**
     * Requires: password is valid.
     * @param username
     * @param password
     */
    CommonUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.creationTime = LocalDateTime.now();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}
