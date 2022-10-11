package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.imageio.spi.ServiceRegistry;

public class Util {
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "Enjoi2324";
    public static final String URL = "jdbc:mysql://localhost:3306/baza";

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DIALECT = "org.hibernate.dialect.MySQLDialect";
    public static SessionFactory sessionFactory;


    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


    static {
        try {
            // Свойства
            Properties properties = new Properties();
            properties.setProperty("hibernate.connection.driver_class", DRIVER);
            properties.setProperty("hibernate.connection.url",URL);
            properties.setProperty("hibernate.connection.username",USER_NAME);
            properties.setProperty("hibernate.connection.password",PASSWORD);
            properties.setProperty("hibernate.dialect",DIALECT);
            // Конфигурация и класс
            Configuration configuration = new Configuration();
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);
            sessionFactory = (SessionFactory) configuration.buildSessionFactory();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }
}

