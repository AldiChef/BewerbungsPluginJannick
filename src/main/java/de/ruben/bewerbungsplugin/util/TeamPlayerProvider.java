package de.ruben.bewerbungsplugin.util;

import de.ruben.bewerbungsplugin.object.TeamGroup;
import de.ruben.bewerbungsplugin.object.TeamPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TeamPlayerProvider {

    private final List<TeamPlayer> teamPlayers = new ArrayList<>();

    private Player player;

    public TeamPlayerProvider(Player player) {
        this.player = player;
    }

    public TeamPlayerProvider(){
        this.player = null;
    }

    public void addTeamPlayer(TeamGroup teamGroup){
        System.out.println("Log > Spieler "+player.getName()+" ("+player.getUniqueId().toString()+") wurde zur Teamliste hinzugefügt!");
        teamPlayers.add(new TeamPlayer(player.getUniqueId().toString(), teamGroup));
    }

    public void removeTeamPlayer(Player player){
        System.out.println("Log > Spieler "+player.getName()+" ("+player.getUniqueId().toString()+") wurde zur Teamliste entfernt!");
        teamPlayers.stream().filter(teamPlayer -> teamPlayer.getUuid().equals(player.getUniqueId().toString())).forEach(teamPlayer -> teamPlayers.remove(teamPlayer));
    }

    public TeamPlayer getTeamPlayer(Player player){
        Optional<TeamPlayer> teamPlayerOptional = teamPlayers.stream().filter(teamPlayer -> teamPlayer.getUuid().equals(player.getUniqueId().toString())).findFirst();
        return teamPlayerOptional.isPresent() ? teamPlayerOptional.get() : null;
    }

    public boolean isTeamPlayer(Player player){
        return teamPlayers.stream().anyMatch(teamPlayer -> teamPlayer.getUuid().equals(player.getUniqueId()));
    }

    public List<TeamPlayer> getSortedTeamPlayers(){
        return teamPlayers.stream().sorted((o1, o2) ->
            o2.getTeamGroup().getValue().compareTo(o1.getTeamGroup().getValue())
        ).collect(Collectors.toList());
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
