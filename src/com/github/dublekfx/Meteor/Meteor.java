package com.github.dublekfx.Meteor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Meteor extends JavaPlugin {
	Player p;

	@Override
	public void onEnable()	{
		
	}
	
	@Override
	public void onDisable()	{
		
	}
	
	public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args)	{
		p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("meteor"))	{
			if ((sender instanceof Player && sender.hasPermission("Meteor.launch")) )	{
				if (args.length == 0)	{
					Meteorite m = new Meteorite(p.getTargetBlock(null, 128).getLocation());
				}
				else if (args.length == 1)	{
					Meteorite m = new Meteorite(p.getTargetBlock(null, 128).getLocation(), Integer.parseInt(args[0]));
				}
				else if (args.length == 2)	{
					if (args[1].substring(0, 1).equalsIgnoreCase("p:"))
					Meteorite m = new Meteorite(args[1].substring(2, args[1].length()), Integer.parseInt(args[0]));
				//args[1] p:<Player> should return target player location
				}				
				return true;
			}
		}
		return false;
	}
}
