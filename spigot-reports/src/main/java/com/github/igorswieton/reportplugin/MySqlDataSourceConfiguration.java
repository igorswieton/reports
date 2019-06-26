/*
 * Copyright (C) 2019 Igor Swieton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
public final class MySqlDataSourceConfiguration {

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
