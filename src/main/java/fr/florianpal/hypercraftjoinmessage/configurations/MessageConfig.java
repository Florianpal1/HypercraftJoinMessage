package fr.florianpal.hypercraftjoinmessage.configurations;

import net.md_5.bungee.config.Configuration;

import java.util.ArrayList;
import java.util.List;

public class MessageConfig {

    private List ignorePlayer = new ArrayList();
    private int protocolVersion;
    private String welcomeMessage;
    private String joinMessage;
    private String leaveMessage;

    public void load(Configuration config) {
        ignorePlayer = config.getStringList("ignorePlayer");
        protocolVersion = config.getInt("protocolVersion");
        welcomeMessage = config.getString("message.welcome");
        joinMessage = config.getString("message.join");
        leaveMessage = config.getString("message.leave");
    }
    public void save(Configuration config) {
        config.set("ignorePlayer", ignorePlayer);
        config.set("protocolVersion", protocolVersion);
        config.set("message.welcome", welcomeMessage);
        config.set("message.join", joinMessage);
        config.set("message.leave", leaveMessage);
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public String getJoinMessage() {
        return joinMessage;
    }

    public String getLeaveMessage() {
        return leaveMessage;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }
    public List getIgnorePlayer() {
        return ignorePlayer;
    }
}
