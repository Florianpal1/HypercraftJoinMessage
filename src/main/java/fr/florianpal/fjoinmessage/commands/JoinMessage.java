package fr.florianpal.fjoinmessage.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandIssuer;
import co.aikar.commands.CommandManager;
import co.aikar.commands.annotation.*;
import fr.florianpal.fjoinmessage.FJoinMessage;
import fr.florianpal.fjoinmessage.languages.MessageKeys;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@CommandAlias("joinmessage")
public class JoinMessage extends BaseCommand {
    private CommandManager commandManager;
    private FJoinMessage plugin;

    public JoinMessage(FJoinMessage plugin) {
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
