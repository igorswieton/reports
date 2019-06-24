package com.github.igorswieton.reportplugin;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Igor Swieton (https://www.github.com/igorswieton)
 * @since 24.06.2019
 */
public class BinderModule extends AbstractModule {

  private ReportPlugin plugin;

  public BinderModule(ReportPlugin plugin) {
    this.plugin = plugin;
  }

  public Injector createInjector() {
    return Guice.createInjector(this);
  }

  @Override
  protected void configure() {
    this.bind(ReportPlugin.class).toInstance(this.plugin);
  }
}
