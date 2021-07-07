package de.ruben.bewerbungsplugin;

import de.ruben.bewerbungsplugin.command.TeamCommand;
import de.ruben.bewerbungsplugin.listener.InventoryClickListener;
import de.ruben.bewerbungsplugin.object.TeamPlayer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class BewerbungsPlugin extends JavaPlugin {

    public final List<TeamPlayer> teamPlayers = new ArrayList<TeamPlayer>();

    @Override
    public void onEnable() {

        getCommand("team").setExecutor(new TeamCommand(this));

        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(this), this);

        saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public List<TeamPlayer> getTeamPlayers() {
        return teamPlayers;
    }
}
