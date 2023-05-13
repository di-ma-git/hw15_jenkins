package org.example.hw12;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.*;

@TestConfiguration
public class MysqlEmbeddedTestConfig {
    @Value(value = "${spring.mysql.url}")
    private String url;
    @Value("${spring.mysql.port}")
    private int mysqlPort;
    @Value("${spring.mysql.host}")
    private String mysqlHost;


    @Bean
    public EmbeddedMysql mysqlServer() {
        EmbeddedMysql mysqlServer = anEmbeddedMysql(aMysqldConfig(v5_7_latest)
                .withPort(mysqlPort)
                .withUser("test", "test")
                .build())
                .addSchema("books", classPathScript("init_db.sql"))
                .start();
        return mysqlServer;
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(url);
        dataSource.setPort(mysqlPort);
        dataSource.setUser("test");
        dataSource.setPassword("test");
        return dataSource;
    }


}
