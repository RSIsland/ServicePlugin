package com.ecconia.rsisland.plugin.service.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.ecconia.rsisland.plugin.service.AbstractCommand;
import com.ecconia.rsisland.plugin.service.ServicePlugin;

public class CommandReload extends AbstractCommand
{
	public CommandReload(JavaPlugin plugin)
	{
		super(plugin, "reload");
	}
	
	@Override
	protected void exec(CommandSender sender, String[] args)
	{
		final Server server = sender.getServer();
		
		sender.getServer().broadcastMessage(ServicePlugin.prefix + ChatColor.YELLOW + "Started plugin reload.");
		Bukkit.reload();
		
		//Delayed execution, else it won't show ingame.
		server.getScheduler().scheduleSyncDelayedTask(server.getPluginManager().getPlugin("ServicePlugin"), new Runnable()
		{
			public void run()
			{
				server.broadcastMessage(ServicePlugin.prefix + ChatColor.YELLOW + "Reload done.");
			}
		}, 1);
	}
}
