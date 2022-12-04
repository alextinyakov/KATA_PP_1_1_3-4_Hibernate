package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;


public class Main {


    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
//        Util.getConnection();



        userService.createUsersTable();
        userService.saveUser("Александр", "Александров", (byte) 33);
        userService.saveUser("Петр", "Петров", (byte) 44);
        userService.saveUser("Дмитрий", "Дмитриев", (byte) 55);
        userService.saveUser("Владимир", "Владимиров", (byte) 66);
//        userService.removeUserById(3);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

//
//        try {
//            Util.connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}
