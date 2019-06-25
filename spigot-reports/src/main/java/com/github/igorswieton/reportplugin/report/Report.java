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
