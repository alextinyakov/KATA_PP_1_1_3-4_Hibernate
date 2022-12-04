package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

// Подключение к БД через JDBC:

    private static String username = "root";
    private static String password = "root";
    private static String url = "jdbc:mysql://localhost:3306/users";
    public static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Подключение к БД через Hibernate, создание фабрики сессий
    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration()
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/users")
                .setProperty("hibernate.order_updates", "true")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "root")
                .setProperty("hibernate.current_session_context_class", "thread")
                .setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");

        SessionFactory sessionFactory = configuration.addAnnotatedClass(User.class).buildSessionFactory();

        return sessionFactory;
    }
}


