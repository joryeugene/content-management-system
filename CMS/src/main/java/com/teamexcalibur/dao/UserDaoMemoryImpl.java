package com.teamexcalibur.dao;

import com.teamexcalibur.dto.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author
 */
public class UserDaoMemoryImpl implements UserDao {

    Map<Integer, User> userMap = new HashMap<>();
    public static int userIdCounter = 0;

    public UserDaoMemoryImpl() {
        addUser(new User("user1@example.com", "User1", "ROLE_ADMIN", "/img/avatar.png"));
        addUser(new User("user2@example.com", "User2", "ROLE_USER", "/img/avatar.png"));
    }

    @Override
    public User addUser(User user) {
        user.setId(userIdCounter);
        userIdCounter++;
        userMap.put(userIdCounter, user);
        return user;
    }

    @Override
    public void deleteUser(int id) {
      userMap.remove(id);
    }

    @Override
    public void updateUser(User user) {
       userMap.put(user.getId(), user);
    }

    @Override
    public User getUserById(int id) {
       User user = userMap.get(id);
       return user;
    }

    @Override
    public List<User> getAllUsers() {
        Collection<User> u = userMap.values();
        return new ArrayList(u);
    }

}
