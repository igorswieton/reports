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
