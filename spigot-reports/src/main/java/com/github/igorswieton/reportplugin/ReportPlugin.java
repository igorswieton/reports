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

package com.github.igorswieton.reportplugin;

import co.aikar.commands.PaperCommandManager;
import com.github.igorswieton.reportplugin.commands.ReportCommand;
import com.github.igorswieton.reportplugin.listeners.PlayerJoinListener;
import com.github.igorswieton.reportplugin.report.MySqlReportRepository;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Igor Swieton (https://www.github.com/igorswieton)
 * @since 24.06.2019
 */
public final class ReportPlugin extends JavaPlugin {

  private BinderModule module;
  private Injector injector;
  private PaperCommandManager manager;
  private PluginManager pluginManager;

  @Inject
  private MySqlReportRepository repository;
  @Inject
  private ReportCommand command;
  @Inject
  private PlayerJoinListener joinListener;

  /**
   * Called when plugin is enabled.
   */
  @Override
  public void onEnable() {
    this.getConfig().options().copyDefaults(true);
    this.saveConfig();
    initialize();
    injector.injectMembers(this);
    manager.registerCommand(this.command);
    repository.createTable();
    pluginManager.registerEvents(this.joinListener, this);
  }

  /**
   * Called when plugin is disabled.
   */
  @Override
  public void onDisable() {
    repository.getDataSource().close();
  }

  /**
   * Serves to initialize variables from this class.
   */
  private void initialize() {
    module = new BinderModule(this);
    injector = module.createInjector();
    manager = new PaperCommandManager(this);
    pluginManager = Bukkit.getPluginManager();
  }
}
