package com.github.igorswieton.reportplugin;

import com.github.igorswieton.reportplugin.annotation.Database;
import com.github.igorswieton.reportplugin.annotation.Host;
import com.github.igorswieton.reportplugin.annotation.Password;
import com.github.igorswieton.reportplugin.annotation.Port;
import com.github.igorswieton.reportplugin.annotation.Username;
import com.google.inject.Inject;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Igor Swieton (https://www.github.com/igorswieton)
 * @since 24.06.2019
 */
public class MySqlDataSourceConfiguration {

  private final String username;
  private final int port;
  private final String database;
  private final String password;
  private final String host;

  @Inject
  public MySqlDataSourceConfiguration(@Username String username, @Port int port,
      @Database String database, @Password String password, @Host String host) {
    this.username = username;
    this.port = port;
    this.database = database;
    this.password = password;
    this.host = host;
  }

  public HikariDataSource createConnection() {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(
        "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database);
    config.setUsername(this.username);
    config.setPassword(this.password);
    config.setMaximumPoolSize(10);
    config.setAutoCommit(true);
    config.setMinimumIdle(0);
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    config.addDataSourceProperty("useServerPrepStmts", "true");
    config.addDataSourceProperty("useLocalSessionState", "true");
    config.addDataSourceProperty("rewriteBatchedStatements", "true");
    config.addDataSourceProperty("cacheResultSetMetadata", "true");
    config.addDataSourceProperty("cacheServerConfiguration", "true");
    config.addDataSourceProperty("elideSetAutoCommits", "true");
    config.addDataSourceProperty("maintainTimeStats", "false");
    return new HikariDataSource(config);
  }
}
