package prog.test;

import jm.task.core.jdbc.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

// Тренировочный класс
public class HibernateConnection {


    public static void main(String[] args) {
        // создание таблицы user в базе данных users
        try (Session session = getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS user " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(64), " +
                    "lastname VARCHAR(64), " +
                    "age INT);")
                    .executeUpdate();
            session.getTransaction().commit();
        }
        // удаление таблицы user в базе данных users
        try (Session session = getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS user;")
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    // Создание подключения - фабрики сессий
    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/users");
        configuration.setProperty("hibernate.order_updates", "true");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.current_session_context_class", "thread");
        configuration.setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext");

        SessionFactory sessionFactory = configuration.addAnnotatedClass(User.class).buildSessionFactory();

        return sessionFactory;
    }
}

