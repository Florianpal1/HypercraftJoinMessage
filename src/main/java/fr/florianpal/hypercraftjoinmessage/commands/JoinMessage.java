package fr.florianpal.hypercraftjoinmessage.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandIssuer;
import co.aikar.commands.CommandManager;
import co.aikar.commands.annotation.*;
import fr.florianpal.hypercraftjoinmessage.HypercraftJoinMessage;
import fr.florianpal.hypercraftjoinmessage.languages.MessageKeys;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@CommandAlias("joinmessage")
public class JoinMessage extends BaseCommand {
    private CommandManager commandManager;
    private HypercraftJoinMessage plugin;

    public JoinMessage(HypercraftJoinMessage plugin) {
        this.plugin = plugin;
        this.commandManager = plugin.getCommandManager();
    }

    @Subcommand("reload")
    @CommandPermission("hc.joinmessage.reload")
    @Description("{@@hypercraft.votes_help_description}")
    public void onReload(ProxiedPlayer playerSender){
        CommandIssuer issuerTarget = commandManager.getCommandIssuer(playerSender);
        plugin.getConfigurationManager().reload();
        issuerTarget.sendInfo(MessageKeys.JOINMESSAGE_RELOAD);
    }
}
