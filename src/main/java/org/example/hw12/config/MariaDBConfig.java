package org.example.hw12.config;


import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:application.properties")
public class MariaDBConfig {
    @Value(value = "${spring.datasource.url}")
    private String url;
    @Value(value = "${spring.datasource.username}")
    private String username;

    @Value(value = "${spring.datasource.password}")
    private String password;
//    private String driver;
    @Bean
    public DataSource mariaDBDataSource() throws SQLException {
        MariaDbDataSource dbDataSource = new MariaDbDataSource();
        dbDataSource.setUrl(url);
        dbDataSource.setUser(username);
        dbDataSource.setPassword(password);
        return dbDataSource;
    }
}
