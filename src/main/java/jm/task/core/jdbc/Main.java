package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl(new UserDaoHibernateImpl());
        userService.createUsersTable();
        userService.saveUser("Ivan","Ivanovich", (byte) 19);
        userService.saveUser("Ivan","Ivanovich", (byte) 19);
        userService.saveUser("Ivan","Ivanovich", (byte) 19);
        userService.saveUser("Ivan","Ivanovich", (byte) 19);
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
