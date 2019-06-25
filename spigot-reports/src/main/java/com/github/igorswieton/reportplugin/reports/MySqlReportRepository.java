package com.github.igorswieton.reportplugin.reports;

import com.github.igorswieton.reportplugin.MySqlDataSourceConfiguration;
import com.google.inject.Inject;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Igor Swieton (https://www.github.com/igorswieton)
 * @since 24.06.2019
 */
public class MySqlReportRepository implements ReportRepository {

  private final HikariDataSource dataSource;

  @Inject
  public MySqlReportRepository(MySqlDataSourceConfiguration configuration) {
    this.dataSource = configuration.createConnection();
  }

  @Override
  public void create() {

  }

  @Override
  public void remove() {

  }

  @Override
  public boolean isReported() {
    return false;
  }
}
