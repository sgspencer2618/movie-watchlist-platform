package entity;

import java.time.LocalDateTime;

public class UserFactory implements UserFactoryInterface{
    /**
     * Requires: password is valid.
     * @param name
     * @param password
     * @return
     */


    public User create(String name, String password, LocalDateTime ltd) {
        return new User(name, password, ltd);
    }
}
