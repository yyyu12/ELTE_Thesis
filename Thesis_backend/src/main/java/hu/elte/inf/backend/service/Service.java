package hu.elte.inf.backend.service;

import hu.elte.inf.backend.sqlEntity.User;

import java.util.List;

public interface Service {
    /**
     * Get All Users
     * @return all users
     */
    List<User> getAllUsers();

    /**
     * User Register
     * @param user
     */
    void registerUser(User user);

    /**
     * User login
     * @param username
     * @param password
     * @return User data of successful login
     */
    User login(String username, String password);

    /**
     * Update user profile info
     * @param user
     * @return
     */
    User updateUserInfo(User user);

    /**
     * Update user password
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    boolean updatePassword(Long userId, String oldPassword, String newPassword);

    /**
     * Delete user
     * @param userId
     * @return
     */
    boolean deleteUser(Long userId);
}