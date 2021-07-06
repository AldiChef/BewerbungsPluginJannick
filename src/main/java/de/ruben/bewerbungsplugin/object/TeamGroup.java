package de.ruben.bewerbungsplugin.object;

public enum TeamGroup {

    INHABER("§4Inhaber", 1),
    DEVELOPER("§bDeveloper",2),
    MODERATOR("§cModerator",3);

    private String displayName;
    private int value;

    TeamGroup(String displayName, int value) {
        this.displayName = displayName;
        this.value = value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
