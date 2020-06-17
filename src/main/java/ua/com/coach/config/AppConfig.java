package ua.com.coach.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Class that configures connection to database.
 */
@Configuration
public class AppConfig {

    private static final String PACKAGE = "ua.com.coach";

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String user;

    @Value("${jdbc.password}")
    private String password;

    @Value("${sql.dialect}")
    private String dialect;

    @Value("${sql.show-sql}")
    private String showSql;

    @Value("${sql.ddl-auto}")
    private String hbm2ddlAuto;

    @Value("${sql.quote-keyword}")
    private String quoteKeyword;

    /**
     * Passing attributes for database connection
     *
     * @return DataSource object that includes all requirements attributes for creating session
     * factory
     */
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }

    /**
     * Passing attributes for hibernate settings
     *
     * @return Properties object with hibernate settings for SQL
     */
    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", dialect);
        hibernateProperties.setProperty("hibernate.show_sql", showSql);
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
//        hibernateProperties.setProperty("hibernate.auto_quote_keyword", quoteKeyword);

        return hibernateProperties;
    }

    /**
     * Creating SessionFactory object with filled database and hibernate settings
     *
     * @return SessionFactory object with configuration for work with database
     */
    @Bean
    public SessionFactory sessionFactory() {
        return new LocalSessionFactoryBuilder(dataSource())
                .scanPackages(PACKAGE)
                .addProperties(hibernateProperties())
                    .buildSessionFactory();
    }

    /**
     * Bean is needed for transaction implementation
     *
     * @return PlatformTransactionManager object that gives us transactional functional
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory());
    }

}
