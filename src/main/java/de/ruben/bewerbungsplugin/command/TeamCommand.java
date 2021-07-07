package de.ruben.bewerbungsplugin.command;

import com.sun.istack.internal.NotNull;
import de.ruben.bewerbungsplugin.BewerbungsPlugin;
import de.ruben.bewerbungsplugin.object.TeamGroup;
import de.ruben.bewerbungsplugin.util.TeamPlayerProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TeamCommand implements CommandExecutor {

    private final BewerbungsPlugin plugin;

    public TeamCommand(BewerbungsPlugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("Um diesen Command ausführen zu können musst du ein Spieler sein!");
            return true;
        }

        Player player = (Player) sender;



        if(args.length == 0){

            Inventory inv = Bukkit.createInventory(null, plugin.getConfig().getInt("inventory_rows")*9, formatColors(plugin.getConfig().getString("inventory_title")));

            player.openInventory(inv);
            inv.setContents(new TeamPlayerProvider(plugin).getSortedTeamPlayersConent());

        }else if(args.length == 3){
            if(args[0].equalsIgnoreCase("add")){
                Player target = Bukkit.getPlayer(args[1]);
                String teamGroup = args[2].toUpperCase();

                if(target == null || !target.isOnline()){
                    player.sendMessage("Der angegebene Spieler ist leider offline!");
                    return true;
                }

                TeamPlayerProvider teamPlayerProvider = new TeamPlayerProvider(plugin, target);

                if(teamPlayerProvider.isTeamPlayer()){
                    player.sendMessage("Der angegebene Spieler ist bereits in der Teamliste eingetragen!");
                    return true;
                }

                if(Arrays.stream(TeamGroup.values()).noneMatch(teamGroup1 -> teamGroup1.name().toUpperCase().equals(teamGroup))){
                    player.sendMessage("Die angegebene TeamGruppe ist ungültig/exestiert nicht!");
                    return true;
                }

                teamPlayerProvider.addTeamPlayer(TeamGroup.valueOf(teamGroup));

                player.sendMessage("Du hast den Spieler "+target.getName()+" erfolgreich zur Teamliste hinzugefügt!");
            }else{
                player.sendMessage("Folgende Commands stehen dir zur Auswahl:");
                player.sendMessage("/team");
                player.sendMessage("/team add <Spieler> <Teamgrupper>");
            }
        }else{
            player.sendMessage("Folgende Commands stehen dir zur Auswahl:");
            player.sendMessage("/team");
            player.sendMessage("/team add <Spieler> <Teamgrupper>");
        }

        return false;
    }

    private String formatColors(String message) {
        Pattern pattern = Pattern.compile("#[A-Fa-f0-9]{6}");
        Matcher matcher = pattern.matcher(message);

        while (matcher.find()) {
            String color = message.substring(matcher.start(), matcher.end());
            message = message.replace(color, net.md_5.bungee.api.ChatColor.of(color)+"");
            matcher = pattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
