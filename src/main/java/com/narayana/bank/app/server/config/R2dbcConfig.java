package com.narayana.bank.app.server.config;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableR2dbcRepositories("com.narayana.bank.app.server.repository")
@ConfigurationProperties(prefix = "app.database")
@EnableTransactionManagement
@EnableR2dbcAuditing
public class R2dbcConfig {
    private Map<String, String> h2 = new HashMap<>();

    @Bean
    public ConnectionFactory connectionFactory() {
        String url = getH2().get("url");
        url = url.substring("r2dbc:h2:".length());
        H2ConnectionConfiguration build = H2ConnectionConfiguration.builder()
                .url(url)
                .username(getH2().get("username"))
                .password(getH2().get("password"))
                //.property("readOnly", "false") // false/true:   h2.jdbc.JdbcSQLNonTransientException: General error: "java.lang.RuntimeException: type=3"; SQL statement:
                //.property("sensitive", "false")
                .build();

        return new H2ConnectionFactory(build);
    }

    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    public Map<String, String> getH2() {
        return h2;
    }

    public void setH2(Map<String, String> h2) {
        this.h2 = h2;
    }
}
