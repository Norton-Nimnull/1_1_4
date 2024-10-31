package jm.task.core.jdbc.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static final String USER = "norton";
    private static final String PASSWORD = "nimnull";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/test";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection con;

    public static Connection getCon() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return con;
    }

    public static void shutdown() {
        try {
            con.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    private static SessionFactory buildSessionFactory() {
        SessionFactory ret = null;
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(jm.task.core.jdbc.model.User.class);
        return cfg.buildSessionFactory();
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown_hib() {
        getSessionFactory().close();
    }

}