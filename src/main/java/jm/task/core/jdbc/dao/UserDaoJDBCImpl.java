package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    public UserDaoJDBCImpl() {
        connection = Util.getCon();
    }

    @Override
    public void createUsersTable() {

        String SQL = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT not NULL AUTO_INCREMENT, " +
                " name VARCHAR(50), " +
                " lastName VARCHAR (50), " +
                " age TINYINT not NULL, " +
                " PRIMARY KEY (id));";
        try (Statement stmt = connection.createStatement();) {
            stmt.executeUpdate(SQL);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String SQL = "DROP TABLE IF EXISTS users";
        try (Statement stmt = connection.createStatement();) {
            stmt.executeUpdate(SQL);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setInt(3, age);
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException se) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            se.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id=?";
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException se) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            se.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        String SQL = "SELECT * FROM users";
        List<User> list = new ArrayList<>();
        try (ResultSet results = connection.createStatement().executeQuery(SQL);) {
            User u;
            while (results.next()) {
                u = new User(results.getString(2), results.getString(3), results.getByte(4));
                u.setId(results.getLong(1));
                list.add(u);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        String SQL = "DELETE FROM users";
        try {
            connection.setAutoCommit(false);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        try (Statement stmt = connection.createStatement();) {
            stmt.executeUpdate(SQL);
            connection.commit();
        } catch (SQLException se) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            se.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
