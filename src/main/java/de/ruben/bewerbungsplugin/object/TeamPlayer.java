package de.ruben.bewerbungsplugin.object;

public class TeamPlayer {

    private String uuid;
    private TeamGroup teamGroup;

    public TeamPlayer(String uuid, TeamGroup teamGroup) {
        this.uuid = uuid;
        this.teamGroup = teamGroup;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public TeamGroup getTeamGroup() {
        return teamGroup;
    }

    public void setTeamGroup(TeamGroup teamGroup) {
        this.teamGroup = teamGroup;
    }
}
