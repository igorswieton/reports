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

  private static final String REMINDER_PREFIX = "§6§lReminder §7» ";

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
