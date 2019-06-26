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

/**
 * @author Igor Swieton (https://www.github.com/igorswieton)
 * @since 24.06.2019
 */
public final class Report {

  private final String reason;
  private final String author;
  private final String victim;

  public Report(String reason, String author, String victim) {
    this.reason = reason;
    this.author = author;
    this.victim = victim;
  }

  public String getVictim() {
    return victim;
  }

  public String getAuthor() {
    return author;
  }

  public String getReason() {
    return reason;
  }
}
