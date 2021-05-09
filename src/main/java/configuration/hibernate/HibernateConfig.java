package configuration.hibernate;

import entity.jpa.Author;
import entity.jpa.Book;
import entity.jpa.Computer;
import entity.jpa.Student;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class HibernateConfig {

    private static SessionFactory sessionFactory = sessionFactory();

    public static SessionFactory sessionFactory(){
        if (sessionFactory != null){
            return sessionFactory;
        }

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(readProperties());
        StandardServiceRegistry serviceRegistry = builder.build();

        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(Student.class);
        cfg.addAnnotatedClass(Author.class);
        cfg.addAnnotatedClass(Book.class);
        cfg.addAnnotatedClass(Computer.class);

        sessionFactory = cfg.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    private static Properties readProperties() {
        Properties prop = new Properties();

        try {
            prop.load(HibernateConfig.class.getClass().getResourceAsStream("/hibernate.properties"));
            return prop;
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
