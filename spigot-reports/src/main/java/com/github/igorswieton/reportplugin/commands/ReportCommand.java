package com.github.igorswieton.reportplugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.github.igorswieton.reportplugin.ReportPlugin;
import com.google.inject.Inject;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Igor Swieton (https://www.github.com/igorswieton)
 * @since 24.06.2019
 */
@CommandAlias("report")
public final class ReportCommand extends BaseCommand {

  public static final String REPORT_INVENTORY_NAME = "§c§lReports";
  private static final int REPORT_INVENTORY_SIZE = 27;

  private ReportPlugin plugin;

  @Inject
  public ReportCommand(ReportPlugin plugin) {
    this.plugin = plugin;
  }

  @Default
  @CommandPermission("report.use")
  public void execute(CommandSender sender, String args) {
    if (!(sender instanceof Player)) {
      return;
    }
    Player player = (Player) sender;
    if (args.length() >= 1) {
      new AnvilGUI(plugin, player, "Report reason",
          (consumer, reply) -> reply);
    }
  }
}
