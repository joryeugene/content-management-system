package com.teamexcalibur.dao;

import com.teamexcalibur.dto.User;
import java.util.List;

public interface UserDao {
    
    User addUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
    User getUserById(int id);
    List<User> getAllUsers();

}
