package fr.florianpal.fjoinmessage.listeners;

import fr.florianpal.fjoinmessage.FJoinMessage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LeaveListener implements Listener {
    private FJoinMessage plugin;
    public LeaveListener(FJoinMessage plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onLeaveEvent(PlayerDisconnectEvent event) {
        if(event.getPlayer().getPendingConnection().getVersion() == plugin.getConfigurationManager().getMessage().getProtocolVersion()) {
            String leaveMessage = plugin.getConfigurationManager().getMessage().getLeaveMessage();
            leaveMessage = leaveMessage.replace("{Player}", event.getPlayer().getDisplayName());
            leaveMessage = ChatColor.translateAlternateColorCodes('&', leaveMessage);
            ProxyServer.getInstance().broadcast(new TextComponent(leaveMessage));
        }
    }
}
