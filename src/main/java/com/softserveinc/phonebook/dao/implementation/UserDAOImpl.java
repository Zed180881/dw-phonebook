
package com.softserveinc.phonebook.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.softserveinc.phonebook.api.User;
import com.softserveinc.phonebook.dao.UserDAO;

@Repository("userDao")
public class UserDAOImpl implements UserDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public User getUserByName(String username) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("name", username);
        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM users WHERE name = :name", namedParameters,
                new UserMapper());
    }

    @Override
    public User getUserById(int id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM users WHERE id = :id", namedParameters,
                new UserMapper());
    }

    @Override
    public int createUser(User user) {
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
        return namedParameterJdbcTemplate.update(
                "INSERT INTO users (name, password, userrole, userstatus) VALUES (:name, :password, :userRole, :userStatus)",
                namedParameters);
    }

    @Override
    public void updateUser(User user) {
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update(
                "UPDATE users SET name = :name, password =:password, userrole = :userRole, userstatus = :userStatus WHERE id=:id",
                namedParameters);

    }

    @Override
    public void deleteUser(int id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        namedParameterJdbcTemplate.update("DELETE FROM users WHERE id = :id", namedParameters);

    }

    @Override
    public List<User> getAllUsers() {
        return namedParameterJdbcTemplate.query("SELECT * FROM users", new UserMapper());
    }

    @Override
    public boolean hasNoDuplicates(User user) {
        return (getUserByName(user.getName()) == null);
    }

    private static final class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setUserRole(rs.getString("userrole"));
            user.setUserStatus(rs.getString("userstatus"));
            return user;
        }
    }

}
