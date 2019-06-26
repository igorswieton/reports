package com.github.igorswieton.reportplugin.report;

import com.google.inject.ImplementedBy;
import java.util.Collection;

/**
 * @author Igor Swieton (https://www.github.com/igorswieton)
 * @since 24.06.2019
 */
@ImplementedBy(MySqlReportRepository.class)
public interface ReportRepository {

  void create(Report report);

  void remove(Report report);

  Report getByName(String name);

  Collection<Report> getAll();

  boolean isReported(String name);

}
