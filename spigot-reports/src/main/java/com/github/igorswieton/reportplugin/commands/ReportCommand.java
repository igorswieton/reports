package com.github.igorswieton.reportplugin.commands;

import static org.bukkit.GameMode.SPECTATOR;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import com.github.igorswieton.reportplugin.ReportPlugin;
import com.github.igorswieton.reportplugin.report.Report;
import com.github.igorswieton.reportplugin.report.ReportRepository;
import com.google.inject.Inject;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.IntStream;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author Igor Swieton (https://www.github.com/igorswieton)
 * @since 24.06.2019
 */
@CommandAlias("report")
public final class ReportCommand extends BaseCommand {

  private static final String CHAT_PREFIX = "§c§lReports §7» ";
  private static final String CHAT_CORRECT_USAGE = "§cCorrect usage§7: /report <player>";
  private static final String CHAT_NOT_AVAILABLE = "This player is currently not available.";

  private static final String REPORT_INVENTORY_NAME = "§c§lReports";
  private static final int REPORT_INVENTORY_SIZE = 27;

  private static final Collection<Player> CONTROLLING_PLAYERS = new HashSet<>();

  private static final Map<Player, GameMode> GAMEMODE_CACHE = new HashMap<>();
  private static final Map<Player, ItemStack[]> INVENTORY_CONTENTS_CACHE = new HashMap<>();
  private static final Map<Player, ItemStack[]> INVENTORY_ARMOR_CONTENTS_CACHE = new HashMap<>();
  private static final Map<Player, Location> LOCATION_CACHE = new HashMap<>();

  private final ReportPlugin plugin;
  private final ReportRepository repository;

  @Inject
  public ReportCommand(ReportPlugin plugin, ReportRepository repository) {
    this.plugin = plugin;
    this.repository = repository;
  }

  @Default
  @CommandCompletion("@players")
  @Description("Used to report any player and control them.")
  public void execute(Player player, String[] args) {
    if (args.length == 1) {

      Player victim = Bukkit.getPlayer(args[0]);

      if (victim == null) {
        player.sendMessage(CHAT_PREFIX + CHAT_NOT_AVAILABLE);
        return;
      }
      if (!repository.isReported(victim.getName())) {
        executeReport(player, victim);
      } else {
        player.sendMessage(CHAT_PREFIX + "This player was already reported.");
      }

    } else if (args.length == 2) {
      if (!player.hasPermission("report.*")) {
        return;
      }
      Player victim = Bukkit.getPlayer(args[1]);
      if (victim == null) {
        player.sendMessage(
            CHAT_PREFIX + CHAT_NOT_AVAILABLE);
        return;
      }
      if (args[0].equalsIgnoreCase("control")) {
        if (!repository.isReported(victim.getName())) {
          player.sendMessage(
              CHAT_PREFIX + "This player has not been reported yet.");
          return;
        }
        if (!CONTROLLING_PLAYERS.contains(player)) {
          startControl(player, victim);
        }
      }

    } else {
      player.sendMessage(CHAT_CORRECT_USAGE);
    }
  }

  @Subcommand("control @")
  @CommandCompletion("@players")
  @CommandPermission("report.*")
  @Description("Just serves to complete the command for users with certain permission.")
  public void onTabComlete() {
  }

  @Subcommand("close")
  @CommandCompletion("close")
  @CommandPermission("report.*")
  @Description("Used to close the current controlling process.")
  public void executeReportControl(Player player) {
    if (CONTROLLING_PLAYERS.contains(player)) {
      closeControl(player);
    } else {
      player.sendMessage(
          CHAT_PREFIX + "You do not control anyone at the moment.");
    }
  }

  private void executeReport(Player player, Player victim) {
    new AnvilGUI(plugin, player, "report reason",
        (author, reason) -> {
          author.sendMessage(getReportMessage(reason, victim));

          Bukkit.getOnlinePlayers().stream().filter(current ->
              current.hasPermission("report.*")).forEach(current ->
              current
                  .sendMessage(getNotificationMessage(reason, author, victim)));

          Report report = new Report(reason, author.getName(),
              victim.getName());

          if (!repository.isReported(victim.getName())) {
            repository.create(report);
          }

          return null;
        });
  }

  private void startControl(Player player, Player victim) {
    GAMEMODE_CACHE.put(player, player.getGameMode());
    INVENTORY_CONTENTS_CACHE.put(player, player.getInventory().getContents());
    INVENTORY_ARMOR_CONTENTS_CACHE
        .put(player, player.getInventory().getArmorContents());
    LOCATION_CACHE.put(player, player.getLocation());

    player.setGameMode(SPECTATOR);
    player.getInventory().clear();
    player.getInventory().setArmorContents(null);
    player.teleport(victim);
    CONTROLLING_PLAYERS.add(player);
    clearChat(player);
    player.sendMessage(getControlMessage(player, victim));
    Report report = repository.getByName(victim.getName());
    repository.remove(report);
  }

  private void closeControl(Player player) {
    player.setGameMode(GAMEMODE_CACHE.get(player));
    player.getInventory().setContents(INVENTORY_CONTENTS_CACHE.get(player));
    player.getInventory()
        .setArmorContents(INVENTORY_ARMOR_CONTENTS_CACHE.get(player));
    player.teleport(LOCATION_CACHE.get(player));
    CONTROLLING_PLAYERS.remove(player);
    clearChat(player);
    player.sendMessage(getCloseMessage());
  }

  private void clearChat(Player player) {
    IntStream.range(0, 200).mapToObj(i -> " ").forEach(player::sendMessage);
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
        + "§7» Type §6/report control <victim> §7to take a look.\n"
        + " \n"
        + "§7§m-------------------------------------";
  }

  private String getControlMessage(Player player, Player victim) {
    return "§7§m---------------§c§lReports§7§m---------------\n"
        + "§7 \n"
        + "§7» You are controlling now " + victim.getName() + "\n"
        + "§7» If you're done, type §6/report close§7.\n"
        + " \n"
        + "§7§m-------------------------------------";
  }

  private String getCloseMessage() {
    return "§7§m---------------§c§lReports§7§m---------------\n"
        + "§7 \n"
        + "§7» Your controlling process has been closed.\n"
        + "§7» Your previous game data has been loaded.\n"
        + " \n"
        + "§7§m-------------------------------------";
  }

}
