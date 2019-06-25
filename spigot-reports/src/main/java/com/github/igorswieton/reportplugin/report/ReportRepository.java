package com.github.igorswieton.reportplugin.report;

/**
 * @author Igor Swieton (https://www.github.com/igorswieton)
 * @since 24.06.2019
 */
public interface ReportRepository {

  void createTable();

  void create(Report report);

  void remove(Report report);

  boolean isReported(Report report);

}
