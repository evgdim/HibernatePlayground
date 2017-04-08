package com.github.evgdm.hibenrateplayground;

import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.github.evgdm.hibenrateplayground.entity.User;

public class App 
{
    public static void main( String[] args )
    {
    	
    	Properties c = new Properties();
        c.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57InnoDBDialect");
        c.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        c.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hplay?useSSL=false");
        c.setProperty("hibernate.connection.username", "evgeni");
        c.setProperty("hibernate.connection.password", "evgeni");
        c.setProperty("hibernate.connection.autoReconnect", "true");
        c.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        c.setProperty("hibernate.show_sql", "true");
        c.setProperty("hibernate.use_sql_comments", "true");
        c.setProperty("hibernate.format_sql", "true");

        c.setProperty("connection.provider_class", "org.hibernate.connection.C3P0ConnectionProvider");
        c.setProperty("c3p0.min_size", "5");
        c.setProperty("c3p0.max_size", "20");
        c.setProperty("c3p0.timeout", "1800");
        c.setProperty("c3p0.max_statements", "100");
        c.setProperty("hibernate.c3p0.testConnectionOnCheckout", "true");

        SessionFactory sessionFactory = new Configuration()
        		.addAnnotatedClass(User.class)
        								.setProperties(c)
        								.configure()
        								.buildSessionFactory();
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        User user = new User(null,"evgeni", "pwd", LocalDate.now());
        session.save(user);
        
        Query query = session.createQuery("select u from User u");
        List list = query.list();
        System.out.println(list);
        
        session.getTransaction().commit();
        session.close();
    }
}
