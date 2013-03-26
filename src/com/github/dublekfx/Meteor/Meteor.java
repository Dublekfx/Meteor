package com.github.dublekfx.Meteor;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
//import org.bukkit.entity.LivingEntity;
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
		Meteorite m;
		if (cmd.getName().equalsIgnoreCase("meteor"))	{
			if ((sender instanceof Player && sender.hasPermission("meteor.launch")) )	{
				if (args.length == 0)	{
					m = new Meteorite(p.getTargetBlock(null, 128).getLocation());
					m.genMeteorite();
					return true;
				}
				else if (args.length == 1)	{
					m = new Meteorite(p.getTargetBlock(null, 128).getLocation(), Integer.parseInt(args[0]));
					m.genMeteorite();
					return true;
				}
				else if (args.length == 2)	{
					if (args[1].substring(0, 1).equalsIgnoreCase("p:"))	{
						m = new Meteorite(Bukkit.getPlayer(args[1].substring(2, args[1].length())).getLocation(), Integer.parseInt(args[0]));
						m.genMeteorite();
						return true;
					}
				}
			}
			if (!(sender instanceof Player))	{
				if (args.length == 1)	{
					if (args[1].substring(0, 1).equalsIgnoreCase("p:"))	{
						m = new Meteorite(Bukkit.getPlayer(args[1].substring(2, args[1].length())).getLocation());
						m.genMeteorite();
						return true;
					}
				}
				else if (args.length == 2)	{
					if (args[1].substring(0, 1).equalsIgnoreCase("p:"))	{
						m = new Meteorite(Bukkit.getPlayer(args[1].substring(2, args[1].length())).getLocation(), Integer.parseInt(args[0]));
						m.genMeteorite();
						return true;
					}
				}
			}
		}
		return false;
	}
}
