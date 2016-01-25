
package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Op everyone on the server, optionally change everyone's gamemode at the same time.", usage = "/<command> [-c | -s]")
public class Command_offduty extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        TFM_Util.playerMsg(sender_p, "", ChatColor.RED);

        boolean doSetGamemode = false;
        GameMode targetGamemode = GameMode.CREATIVE;
        if (args.length != 0)
        {
            if (args[0].equals("on"))
            {
            TFM_Util.bcastMsg(ChatColor.RED + sender.getName() + ": I am now offduty, contact another administrator.");
            }
            else if (args[0].equals("off"))
            {
            TFM_Util.bcastMsg(ChatColor.RED + sender.getName() + ": I am no longer offduty.");
            }
        }

        return true;
    }
}
