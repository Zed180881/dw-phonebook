
package com.softserveinc.phonebook.health;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codahale.metrics.health.HealthCheck;

@Component
public class DataSourceConnectionHealthCheck extends HealthCheck {

    @Autowired
    private DataSource dataSource;

    @Override
    protected Result check() throws Exception {
        if (!checkDatabaseAvailable()) {
            return Result.unhealthy("Database connection lost");
        }
        return Result.healthy("Database connection healthy");
    }

    private Boolean checkDatabaseAvailable() throws Exception {
        return dataSource.getConnection().isValid(2);
    }
}