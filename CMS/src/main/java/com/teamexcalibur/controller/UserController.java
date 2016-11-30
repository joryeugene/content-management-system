/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamexcalibur.controller;

import com.teamexcalibur.dao.UserDao;
import com.teamexcalibur.dto.User;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author apprentice
 */
@Controller
public class UserController {

    private UserDao dao;
    private PasswordEncoder encoder;

    @Inject
    public UserController(UserDao dao, PasswordEncoder pwe) {
        this.dao = dao;
        this.encoder = pwe;
    }

    // #1 - This endpoint retrieves all users from the database and puts the
    // List of users on the model
    @RequestMapping(value = "/displayUserList", method = RequestMethod.GET)
    public String displayUserList(Map<String, Object> model) {
        List users = dao.getAllUsers();
        model.put("users", users);
        return "displayUserList";
    }
    // #2 - This endpoint just displays the Add User form

    @RequestMapping(value = "/displayUserForm", method = RequestMethod.GET)
    public String displayUserForm(Map<String, Object> model) {
        return "addUser";
    }
    // #3 - This endpoint processes the form data and creates a new User

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(HttpServletRequest req) {
        User newUser = new User();
        // #4 - This example uses a plain HTML form so we must get values from the request
        newUser.setEmail(req.getParameter("email"));
        newUser.setDisplayName(req.getParameter("displayName"));
        newUser.setAvatarUrl(req.getParameter("avatarUrl"));
        String clearPw = req.getParameter("password");
        String hashPw = encoder.encode(clearPw);
        newUser.setPassword(hashPw);
        // #5 - All users have ROLE_WRITER, only add ROLE_ADMIN if the isAdmin box is checked
        if (null != req.getParameter("isAdmin")) {
            newUser.setAuthority("ROLE_ADMIN");
        } else {
            newUser.setAuthority("ROLE_WRITER");
        }

        dao.addUser(newUser);

        return "redirect:displayUserList";
    }
    // #6 - This endpoint deletes the specified User

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteUser(@RequestParam("userId") int userId,
            Map<String, Object> model) {
        dao.deleteUser(userId);
        return "redirect:displayUserList";
    }
}
