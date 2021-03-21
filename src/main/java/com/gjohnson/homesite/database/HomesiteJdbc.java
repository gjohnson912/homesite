package com.gjohnson.homesite.database;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

public class HomesiteJdbc extends NamedParameterJdbcTemplate {
    public HomesiteJdbc(DataSource dataSource) {
        super(dataSource);
    }

    public HomesiteJdbc(JdbcOperations classicJdbcTemplate) {
        super(classicJdbcTemplate);
    }
}
