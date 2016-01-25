package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Ban;
import me.StevenLawson.TotalFreedomMod.TFM_BanManager;
import me.StevenLawson.TotalFreedomMod.TFM_PlayerData;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.StevenLawson.TotalFreedomMod.TFM_UuidManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Temporarily bans a player for fiftteen minutes.", usage = "/<command> <partialname>", aliases = "nickban")
public class Command_nb extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length != 1)
        {
            return false;
        }

        final Player player = getPlayer(args[0]);

        if (player == null)
        {
            playerMsg(TFM_Command.PLAYER_NOT_FOUND, ChatColor.RED);
            return true;
        }

        // strike with lightning effect:
        final Location targetPos = player.getLocation();
        for (int x = -1; x <= 1; x++)
        {
            for (int z = -1; z <= 1; z++)
            {
                final Location strike_pos = new Location(targetPos.getWorld(), targetPos.getBlockX() + x, targetPos.getBlockY(), targetPos.getBlockZ() + z);
                targetPos.getWorld().strikeLightning(strike_pos);
            }
        }

        TFM_Util.adminAction(sender.getName(), "Temporarily Banning: " + player.getName() + " for fiveteen minutes.", true);
        TFM_BanManager.addUuidBan(
                new TFM_Ban(TFM_UuidManager.getUniqueId(player), player.getName(), sender.getName(), TFM_Util.parseDateOffset("15m"), ChatColor.RED + "You have been temporarily banned for 15 minutes."));
        TFM_BanManager.addIpBan(
                new TFM_Ban(TFM_Util.getIp(player), player.getName(), sender.getName(), TFM_Util.parseDateOffset("5m"), ChatColor.RED + "You have been temporarily banned for 5 minutes."));

        player.kickPlayer(ChatColor.RED + "You have been temporarily banned for fifteen minutes. Please read http://ufreedom.boards.net for more info.");

        return true;
    }
}
