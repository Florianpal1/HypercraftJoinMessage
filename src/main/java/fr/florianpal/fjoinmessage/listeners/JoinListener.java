package fr.florianpal.fjoinmessage.listeners;

import fr.florianpal.fjoinmessage.FJoinMessage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinListener implements Listener {
    private FJoinMessage plugin;
    public JoinListener(FJoinMessage plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onJoinEvent(final PostLoginEvent event) {

        plugin.getProxy().getScheduler().runAsync(plugin, new Runnable() {
            @Override
            public void run() {
                if(event.getPlayer().getPendingConnection().getVersion() == plugin.getConfigurationManager().getMessage().getProtocolVersion()) {
                    if(!plugin.getConfigurationManager().getMessage().getIgnorePlayer().contains(event.getPlayer().getPendingConnection().getName())) {
                        if (plugin.getFirstJoinQueries().getPlayer(event.getPlayer().getUniqueId().toString()) != null) {

                            String joinMessage = plugin.getConfigurationManager().getMessage().getJoinMessage();
                            joinMessage = joinMessage.replace("{Player}", event.getPlayer().getDisplayName());
                            joinMessage = ChatColor.translateAlternateColorCodes('&', joinMessage);
                            ProxyServer.getInstance().broadcast(new TextComponent(joinMessage));
                        } else {
                            String firstjoinMessage = plugin.getConfigurationManager().getMessage().getWelcomeMessage();
                            firstjoinMessage = firstjoinMessage.replace("{Player}", event.getPlayer().getDisplayName());
                            firstjoinMessage = ChatColor.translateAlternateColorCodes('&', firstjoinMessage);
                            ProxyServer.getInstance().broadcast(new TextComponent(firstjoinMessage));
                            plugin.getFirstJoinQueries().addPlayer(event.getPlayer().getUniqueId().toString());
                        }
                    }
                }
            }
        });

    }
}
