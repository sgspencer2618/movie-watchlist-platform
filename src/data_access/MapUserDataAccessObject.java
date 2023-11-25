package data_access;

import data_store.UserDBInterface;
import entity.User;
import entity.UserFactory;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

public class MapUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface {
    private final UserDBInterface userDBMap;
    private UserFactory userFactory;

    public MapUserDataAccessObject(UserDBInterface userDBMap, UserFactory userFactory) {
        this.userDBMap = userDBMap;
    }

    @Override
    public boolean existsByName(String username) {
        return userDBMap.userExists(username);
    }

    @Override
    public void save(User user) {
        userDBMap.addUser(user.getUsername(), user.getPassword());
    }

    @Override
    public User get(String username) {
        return userDBMap.getUser(username);
    }
}
