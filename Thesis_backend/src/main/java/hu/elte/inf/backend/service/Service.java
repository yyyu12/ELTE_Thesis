package hu.elte.inf.backend.service;

// import hu.elte.inf.backend.controller.request.;
import hu.elte.inf.backend.sqlEntity.User;

public interface Service {

    void registerUser(User user);

    /**
     * User login
     * @param username
     * @param password
     * @return User data of successful login
     */
    User login(String username, String password);

    User updateUserInfo(User user);
}