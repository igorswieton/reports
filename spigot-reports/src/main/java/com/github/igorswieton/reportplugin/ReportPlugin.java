package com.github.igorswieton.reportplugin;

import co.aikar.commands.PaperCommandManager;
import com.github.igorswieton.reportplugin.commands.ReportCommand;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Igor Swieton (https://www.github.com/igorswieton)
 * @since 24.06.2019
 */
public final class ReportPlugin extends JavaPlugin {

  private BinderModule module;
  private Injector injector;
  private Configuration config;
  private PaperCommandManager manager;

  @Inject
  private ReportCommand command;

  /**
   * Called when plugin is enabled.
   */
  @Override
  public void onEnable() {
    initialize();
    config.options().copyDefaults(true);
    this.saveConfig();
    injector.injectMembers(this);
    manager.registerCommand(this.command);
  }

  /**
   * Serves to initialize variables from this class.
   */
  private void initialize() {
    config = this.getConfig();
    module = new BinderModule(this);
    injector = module.createInjector();
    manager = new PaperCommandManager(this);
  }
}
