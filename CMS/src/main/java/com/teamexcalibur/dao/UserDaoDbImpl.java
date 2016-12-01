/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamexcalibur.dao;

import com.teamexcalibur.dto.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class UserDaoDbImpl implements UserDao {

    private static final String SQL_INSERT_USER
            = "insert into User (Email, DisplayName, Authority, AvatarUrl, Password) values (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_USER
            = "delete from User where UserId = ?";
    private static final String SQL_UPDATE_USER
            = "update User set Email = ?, DisplayName = ?, Authority = ?, AvatarUrl = ?, Password = ? where UserId = ?";
    private static final String SQL_SELECT_USER_BYID
            = "select * from User where UserId = ?";
    private static final String SQL_SELECT_USER_BY_EMAIL
            = "select * from User where Email = ?";
    private static final String SQL_SELECT_ALL_USERS
            = "select * from User";
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User addUser(User user) {
        jdbcTemplate.update(SQL_INSERT_USER, user.getEmail(), user.getDisplayName(),
                user.getAuthority(), user.getAvatarUrl(), user.getPassword());
        user.setId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));
        return user;
    }

    @Override
    public void deleteUser(int id) {
        jdbcTemplate.update(SQL_DELETE_USER, id);
    }

    @Override
    public void updateUser(User user) {
        jdbcTemplate.update(SQL_UPDATE_USER, user.getEmail(), user.getDisplayName(),
                user.getAuthority(), user.getAvatarUrl(), user.getPassword(), user.getId());
    }

    @Override
    public User getUserById(int id) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_USER_BYID,
                    new UserMapper(), id);
            return user;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public User getUserByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_EMAIL,
                    new UserMapper(), email);
            return user;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_USERS, new UserMapper());
    }

    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("UserId"));
            user.setEmail(rs.getString("Email"));
            user.setDisplayName(rs.getString("DisplayName"));
            user.setAuthority(rs.getString("Authority"));
            user.setAvatarUrl(rs.getString("AvatarUrl"));
            user.setPassword(rs.getString("Password"));
            return user;
        }
    }
}
