package com.github.igorswieton.reportplugin.reports;

/**
 * @author Igor Swieton (https://www.github.com/igorswieton)
 * @since 24.06.2019
 */
public interface ReportRepository {

  void create();

  void remove();

  boolean isReported();

}
