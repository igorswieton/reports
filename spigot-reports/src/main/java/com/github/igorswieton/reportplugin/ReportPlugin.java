package com.github.igorswieton.reportplugin;

import com.google.inject.Injector;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Igor Swieton (https://www.github.com/igorswieton)
 * @since 24.06.2019
 */
public final class ReportPlugin extends JavaPlugin {

  private BinderModule module;
  private Injector injector;

  /**
   * Called when plugin is enabled.
   */
  @Override
  public void onEnable() {
    initialize();
    injector.injectMembers(this);
  }

  /**
   * Called when plugin is disabled.
   */
  @Override
  public void onDisable() {

  }

  /**
   * Serves to initialize variables from this class.
   */
  private void initialize() {
    module = new BinderModule(this);
    injector = module.createInjector();
  }
}
