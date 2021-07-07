package de.ruben.bewerbungsplugin;

import de.ruben.bewerbungsplugin.command.TeamCommand;
import de.ruben.bewerbungsplugin.object.TeamPlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class BewerbungsPlugin extends JavaPlugin {

    public final List<TeamPlayer> teamPlayers = new ArrayList<TeamPlayer>();

    @Override
    public void onEnable() {

        getCommand("team").setExecutor(new TeamCommand(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public List<TeamPlayer> getTeamPlayers() {
        return teamPlayers;
    }
}
