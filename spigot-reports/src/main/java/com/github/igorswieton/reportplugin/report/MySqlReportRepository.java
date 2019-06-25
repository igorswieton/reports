package com.github.igorswieton.reportplugin.report;

import com.github.igorswieton.reportplugin.MySqlDataSourceConfiguration;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Singleton
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

  public void createTable() {
    String query = "CREATE TABLE IF NOT EXISTS `report_table` "
        + "(`reason` VARCHAR(50), `author` VARCHAR(17), `victim` VARCHAR(17), "
        + "`creation_date` DATE)";
    try (Connection connection = dataSource
        .getConnection(); PreparedStatement statement = connection
        .prepareStatement(query)) {
      statement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void create(Report report) {
    String query = "INSERT INTO `report_table` (`reason`, `author`, `victim`, `creation_date`) VALUES (?, ?, ?, NOW())";
    try (Connection connection = dataSource
        .getConnection(); PreparedStatement statement = connection
        .prepareStatement(query)) {
      statement.setString(1, report.getReason());
      statement.setString(2, report.getAuthor());
      statement.setString(3, report.getVictim());
      statement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void remove(Report report) {
    String query = "DELETE FROM `report_table` WHERE `author` = ?";
    try (Connection connection = dataSource
        .getConnection(); PreparedStatement statement = connection
        .prepareStatement(query)) {
      statement.setString(1, report.getAuthor());
      statement.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public boolean isReported(Report report) {
    String query = "SELECT * FROM `report_table` WHERE `author` = ?";
    try (Connection connection = dataSource
        .getConnection(); PreparedStatement statement = connection
        .prepareStatement(query)) {
      statement.setString(1, report.getAuthor());
      ResultSet resultSet = statement.executeQuery();
      return resultSet.next();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return false;
  }

  public HikariDataSource getDataSource() {
    return dataSource;
  }
}
