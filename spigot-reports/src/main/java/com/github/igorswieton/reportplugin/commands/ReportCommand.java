package com.github.igorswieton.reportplugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import com.github.igorswieton.reportplugin.ReportPlugin;
import com.google.inject.Inject;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Igor Swieton (https://www.github.com/igorswieton)
 * @since 24.06.2019
 */
@CommandAlias("report")
public final class ReportCommand extends BaseCommand {

  private static final String CHAT_PREFIX = "§c§lReports §7» ";

  private static final String CHAT_INVALID_SYNTAX = "§cWrong syntax! Check command help to get help.";

  public static final String REPORT_INVENTORY_NAME = "§c§lReports";

  private static final int REPORT_INVENTORY_SIZE = 27;

  private final ReportPlugin plugin;

  @Inject
  public ReportCommand(ReportPlugin plugin) {
    this.plugin = plugin;
  }

  @Default
  @CommandPermission("report.use")
  public void execute(CommandSender sender, String[] args) {
    if (!(sender instanceof Player)) {
      return;
    }
    Player player = (Player) sender;
    if (args.length == 1) {
      Player target = Bukkit.getPlayer(args[0]);
      if (target == null) {
        return;
      }
      new AnvilGUI(plugin, player, "report reason",
          (consumer, reply) -> {
            consumer.sendMessage(getReportMessage(reply, target));

            Bukkit.getOnlinePlayers().stream()
                .filter(current -> current.hasPermission("report.*")).forEach(
                  current -> current.sendMessage(
                    getNotificationMessage(reply, consumer, target)));

            return null;
          });
    } else {
      player.sendMessage(CHAT_PREFIX + CHAT_INVALID_SYNTAX);
    }
  }

  private String getReportMessage(String reason, Player victim) {
    return "§7§m---------------§c§lReports§7§m---------------\n"
        + "§7 \n"
        + "§7» Thank you for your §c§lReport§7!\n"
        + " \n"
        + "§7» §6Reason§7: " + reason + "\n"
        + "§7» §6Victim§7: " + victim.getName() + "\n"
        + " \n"
        + "§7» The team will take care of it within a few minutes.\n"
        + " \n"
        + "§7§m-------------------------------------";
  }

  private String getNotificationMessage(String reason, Player author,
      Player victim) {
    return "§7§m---------------§c§lReports§7§m---------------\n"
        + "§7 \n"
        + "§7» A new §c§lreport §7has been received!\n"
        + " \n"
        + "§7» §6Reason§7: " + reason + "\n"
        + "§7» §6Author§7: " + author.getName() + "\n"
        + "§7» §6Victim§7: " + victim.getName() + "\n"
        + " \n"
        + "§7» Type §6/report see <victim> §7to take a look.\n"
        + " \n"
        + "§7§m-------------------------------------";
  }

}
