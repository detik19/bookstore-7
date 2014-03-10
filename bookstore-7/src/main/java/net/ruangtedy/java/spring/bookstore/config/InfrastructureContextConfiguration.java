package net.ruangtedy.java.spring.bookstore.config;


import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configures the main infrastructure related beans such as:
 * 
 * <ul>
 * <li>Creates the {@link EntityManagerFactory} based upon information in the META-INF/persistence.xml
 * <li>Creates a JPA local transaction manager</li>
 * <li>Creates a datasource to a local database</li>
 * </ul>
 * 
 * @author Marten Deinum
 * @author Koen Serneels
 */

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "net.ruangtedy.java.spring.bookstore.service",
        "net.ruangtedy.java.spring.bookstore.repository", "net.ruangtedy.java.spring.bookstore.domain.support" })
public class InfrastructureContextConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public FactoryBean<EntityManagerFactory> entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setPackagesToScan( new String[ ] { "net.ruangtedy.java.spring.bookstore.domain.support"} );
        emfb.setDataSource(this.dataSource);

        emfb.setJpaVendorAdapter(jpaVendorAdapter());
        emfb.setJpaProperties(hibernateProperties());
        return emfb;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(this.entityManagerFactory);
//        txManager.setDataSource(this.dataSource);
//        return txManager;
//    }
    
//    @Bean
//    public PlatformTransactionManager transactionManager(){
//       JpaTransactionManager transactionManager = new JpaTransactionManager();
//       transactionManager.setEntityManagerFactory(this.entityManagerFactory);
//       return transactionManager;
//    }
//    @Bean
//    public DataSource dataSource() {
//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//        builder.setType(EmbeddedDatabaseType.H2);
//        return builder.build();
//    }
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
    	ds.setDriverClassName("com.mysql.jdbc.Driver");
    	ds.setUsername("root");
    	ds.setPassword("");
        ds.setUrl("jdbc:mysql://127.0.0.1:3306/bookstore");
    	return ds;
    }
    @Bean
    public LocalSessionFactoryBean alertsSessionFactory(){
    	LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
       sessionFactory.setDataSource( this.dataSource() );
       sessionFactory.setPackagesToScan( new String[ ] { "net.ruangtedy.java.spring.bookstore.domain.support"} );
       sessionFactory.setHibernateProperties( this.hibernateProperties() );
       
       return sessionFactory;
    }
    
    private Properties hibernateProperties() {
        Properties pp = new Properties();
        pp.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        pp.setProperty("hibernate.max_fetch_depth", "3");
        pp.setProperty("hibernate.show_sql", "true");

		return pp;
	}
	@Bean
    public HibernateTransactionManager transactionManager(){
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory( this.alertsSessionFactory().getObject() );
       
       return txManager;
    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
       return new PersistenceExceptionTranslationPostProcessor();
    }
}
