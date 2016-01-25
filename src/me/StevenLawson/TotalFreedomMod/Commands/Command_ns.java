package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_Util;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Someone being a little bitch? Smite them down...", usage = "/<command> [playername]", aliases = "nicksmite")
public class Command_ns extends TFM_Command
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
            playerMsg(TFM_Command.PLAYER_NOT_FOUND);
            return true;
        }

        smite(player);

        return true;
    }

    public static void smite(final Player player)
    {
        TFM_Util.bcastMsg(player.getName() + " has been a naughty, naughty boy/(girl). Now, he better get back in action, otherwise then he won't get the booty wipe. Anyways! Time for the fun booty wipey to start! >:D", ChatColor.RED);
        player.chat("OH NO! NOT THE BOOTY WIPE!");
        player.chat("ARGAHHRHAGHH! LOOK, I'M SORRY FOR WHAT I'VE DONE!");
        TFM_Util.bcastMsg(player.getName() + " it cannot be undone! But you can earn back your respect. Now this is when your punishment starts to begin :)", ChatColor.RED);
        
        //Deop
        player.setOp(false);

        //Set gamemode to survival:
        player.setGameMode(GameMode.SURVIVAL);

        //Clear inventory:
        player.getInventory().clear();

        //Strike with lightning effect:
        final Location targetPos = player.getLocation();
        final World world = player.getWorld();
        for (int x = -1; x <= 1; x++)
        {
            for (int z = -1; z <= 1; z++)
            {
                final Location strike_pos = new Location(world, targetPos.getBlockX() + x, targetPos.getBlockY(), targetPos.getBlockZ() + z);
                world.strikeLightning(strike_pos);
            }
        }

        //Kill:
        player.setHealth(0.0);
    }
}
