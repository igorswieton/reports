package com.github.igorswieton.reportplugin.reports;

/**
 * @author Igor Swieton (https://www.github.com/igorswieton)
 * @since 24.06.2019
 */
public final class Report {

  private final String reason;
  private final String author;
  private final String accused;

  public Report(String reason, String author, String accused) {
    this.reason = reason;
    this.author = author;
    this.accused = accused;
  }

  public String getAccused() {
    return accused;
  }

  public String getAuthor() {
    return author;
  }

  public String getReason() {
    return reason;
  }
}
