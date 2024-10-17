package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl usimp = new UserServiceImpl();
        //usimp.dropUsersTable();
        //usimp.dropUsersTable();
        usimp.createUsersTable();
        //usimp.createUsersTable();
        usimp.saveUser("Lestat", "Delionkur", (byte) 125);
        usimp.saveUser("Akasha", "Goddes", (byte) -1);
        usimp.saveUser("Claudia", "Rice", (byte) 6);
        usimp.saveUser("Anne", "Rice", (byte) 80);
        List<User> ulist = usimp.getAllUsers();
        for (int i = 0; i < ulist.size(); i++) {
            System.out.println(ulist.get(i).toString());
        }
        usimp.removeUserById(3L);
        usimp.cleanUsersTable();
        usimp.dropUsersTable();
        usimp.shutdown();
    }
}
