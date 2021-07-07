package de.ruben.bewerbungsplugin.listener;

import de.ruben.bewerbungsplugin.BewerbungsPlugin;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InventoryClickListener implements Listener {

    private final BewerbungsPlugin plugin;

    public InventoryClickListener(BewerbungsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        String inventoryTitle = event.getView().getTitle();

        if(inventoryTitle.equals(formatColors(plugin.getConfig().getString("inventory_title")))){

            event.setCancelled(true);
        }

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
