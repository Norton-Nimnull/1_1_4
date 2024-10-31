package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import  jm.task.core.jdbc.service.UserService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService usimp = new UserServiceImpl();
        //usimp.dropUsersTable();
        //usimp.dropUsersTable();
        usimp.createUsersTable();
        //usimp.createUsersTable();
        usimp.saveUser("Lestat", "Delionkur", (byte) 125);
        usimp.saveUser("Akasha", "Goddes", (byte) -1);
        usimp.saveUser("Claudia", "Rice", (byte) 6);
        usimp.saveUser("Anne", "Rice", (byte) 80);
        //usimp.saveUser("ass","ass",(byte)0);
        //проверка rollback, сохранение пользователя с недопустимым именем
        //для проверки надо также раскрмментировать строчку в UseDaoHibernateImpl.java
        List<User> ulist = usimp.getAllUsers();
        for (int i = 0; i < ulist.size(); i++) {
            System.out.println(ulist.get(i).toString());
        }
        usimp.removeUserById(3L);
        usimp.cleanUsersTable();
        usimp.dropUsersTable();
        Util.shutdown_hib();
    }
}
