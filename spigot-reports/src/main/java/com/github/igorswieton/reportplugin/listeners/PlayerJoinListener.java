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

package com.github.igorswieton.reportplugin.listeners;

import com.github.igorswieton.reportplugin.ReportPlugin;
import com.github.igorswieton.reportplugin.report.ReportRepository;
import com.google.inject.Inject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author Igor Swieton (https://www.github.com/igorswieton)
 * @since 26.06.2019
 */
public class PlayerJoinListener implements Listener {

  private final ReportPlugin plugin;
  private final ReportRepository reportRepository;

  @Inject
  public PlayerJoinListener(ReportPlugin plugin,
      ReportRepository reportRepository) {
    this.plugin = plugin;
    this.reportRepository = reportRepository;
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    for (Player current : Bukkit.getOnlinePlayers()) {
      if (reportRepository.isReported(current.getName())) {
        player.sendMessage(getReminderMessage());
      }
    }
  }

  private String getReminderMessage() {
    return "§7§m---------------§6§lReminder§7§m---------------\n"
        + "§7 \n"
        + "§7» There are still outstanding reports!\n"
        + " \n"
        + "§7§m--------------------------------------";
  }
}
