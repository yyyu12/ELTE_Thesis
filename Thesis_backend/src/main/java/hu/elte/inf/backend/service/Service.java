package hu.elte.inf.backend.service;

// import hu.elte.inf.backend.controller.request.;
import hu.elte.inf.backend.sqlEntity.User;

public interface Service {

    void registerUser(User user);
    User login(String username);
    void updateUser(User user);
}