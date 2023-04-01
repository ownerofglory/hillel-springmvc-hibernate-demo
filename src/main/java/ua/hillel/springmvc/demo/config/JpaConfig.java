package ua.hillel.springmvc.demo.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ua.hillel.springmvc.demo.model.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@PropertySource("classpath:app.properties")
public class JpaConfig {
    @Value("${jpa.hibernate.mysql.driver:com.mysql.cj.jdbc.Driver}")
    private String mysqlDriver;
    @Value("${jpa.hibernate.mysql.url}")
    private String mysqlUrl;
    @Value("${jpa.hibernate.mysql.username}")
    private String mysqlUser;
    @Value("${jpa.hibernate.mysql.password}")
    private String mysqlPassword;
    @Value("${jpa.hibernate.showSql:false}")
    private String hibernateShowSql;
    @Value("${jpa.hibernate.autoStrategy:none}")
    private String hibernateAutoStrategy;
    @Value("${jpa.hibernate.dialect:org.hibernate.dialect.MySQL8Dialect}")
    private String hibernateDialect;

    @Bean
    public SessionFactory sessionFactory() {
        Properties props = new Properties();
        props.put("jakarta.persistence.jdbc.driver", mysqlDriver);
        props.put("hibernate.connection.driver_class", mysqlDriver);
        props.put("jakarta.persistence.jdbc.url", mysqlUrl);
        props.put("hibernate.connection.url", mysqlUrl);
        props.put("jakarta.persistence.jdbc.user", mysqlUser);
        props.put("hibernate.connection.username", mysqlUser);
        props.put("jakarta.persistence.jdbc.password", mysqlPassword);
        props.put("hibernate.connection.password", mysqlPassword);
        props.put("hibernate.show_sql", hibernateShowSql);
        props.put("hibernate.hbm2ddl.auto", hibernateAutoStrategy);
        props.put("hibernate.dialect", hibernateDialect);

        var configuration = new org.hibernate.cfg.Configuration();
        configuration.setProperties(props);
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(props)
                .build();

        return new MetadataSources(serviceRegistry)
                .addAnnotatedClass(User.class)
                .getMetadataBuilder()
                .build()
                .getSessionFactoryBuilder()
                .build();
    }
}
