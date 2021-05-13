package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;
import jm.task.core.jdbc.model.User;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static Util instance;
    private static Util hibernateInstance;
    private String DB_URL;
    private String user;
    private String pass;
    private SessionFactory sessionFactory;

    @SneakyThrows
    public Util() {
        Class.forName("com.mysql.cj.jdbc.Driver");
        DB_URL = "jdbc:mysql://localhost:3306/world?serverTimezone=UTC";
        user = "root";
        pass = "1234567890";
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, user, pass);
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }

    @SneakyThrows
    public Util(String s) {
        Configuration config = new Configuration();
        config.addAnnotatedClass(User.class);
        config.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/world");
        config.setProperty("hibernate.connection.username", "root");
        config.setProperty("hibernate.connection.password", "1234567890");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        config.setProperty("hibernate.current_session_context_class", "thread");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.format_sql", "true");
        config.setProperty("hibernate.hbm2ddl.auto", "update");
        sessionFactory = config.buildSessionFactory();
    }

    public static Util getHibernateInstance(){
        if(hibernateInstance==null){
            hibernateInstance = new Util("Hiber");
        }
        return hibernateInstance;
    }

    public static Util getInstance() {
        if (instance==null){
            instance = new Util();
        }
        return instance;
    }
}
