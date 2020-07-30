package com.itutry.configdatasource;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@Slf4j
public class ConfigDatasourceApplication implements CommandLineRunner {

  @Autowired
  private DataSource dataSource;
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public static void main(String[] args) {
    SpringApplication.run(ConfigDatasourceApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    showConnection();
    showData();
  }

  private void showConnection() throws SQLException {
    log.info(dataSource.toString());
    try (Connection conn = dataSource.getConnection()) {
      log.info(conn.toString());
    }
  }

  private void showData() {
    jdbcTemplate.queryForList("SELECT * FROM FOO").forEach(row -> log.info(row.toString()));
  }
}
