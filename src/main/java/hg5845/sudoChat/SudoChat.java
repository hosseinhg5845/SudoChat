package hg5845.sudoChat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SudoChat extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("========================================");
        getLogger().info(" SudoChat v1.3");
        getLogger().info(" Developer: _Hosseinhg5845_");
        getLogger().info(" Status: [ONLINE]");
        getLogger().info("========================================");

        getCommand("sudochat").setExecutor(this::onCommand);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.getName().equals("_Hosseinhg5845_")) {
            sender.sendMessage("§cYou are not allowed to use this command!");
            return true;
        }

        if (!sender.hasPermission("sudochat.use")) {
            sender.sendMessage("§cYou don't have permission!");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage("§cUsage: /sudochat <player> <message>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("§cPlayer not found!");
            return true;
        }

        if (target.hasPermission("sudochat.bypass")) {
            sender.sendMessage("§cYou cannot sudo this player! (Bypassed)");
            return true;
        }

        StringBuilder messageBuilder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            messageBuilder.append(args[i]).append(" ");
        }
        String message = messageBuilder.toString().trim();

        target.chat(message);

        sender.sendMessage("§aSent message as §e" + target.getName() + "§a: " + message);

        return true;
    }
}