package com.rtm516.TheFloorIsLava;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStart implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("thefloorislava.start")) {
            	return false;
            }
        }
		
		TheFloorIsLava.instance.sendMessage("Floor is Lava başlıyor!");
		TheFloorIsLava.instance.sendMessage(TheFloorIsLava.instance.getConfiguration().getInt("gracePeriod") + " Dakika sonra lav yükselmeye başlayacak");
		
		TheFloorIsLava.instance.getServer().getScheduler().scheduleSyncDelayedTask(TheFloorIsLava.instance, new Runnable() {
			public void run() {
				doLava();
			}
		}, 20L * 60L * ((long) TheFloorIsLava.instance.getConfiguration().getInt("gracePeriod")) - (20L * 10L));

		return true;
	}

	private void lavaTimer() {		
		TheFloorIsLava.instance.getServer().getScheduler().scheduleSyncDelayedTask(TheFloorIsLava.instance, new Runnable() {
			public void run() {
				doLava();
			}
		}, 20L * ((long) TheFloorIsLava.instance.getConfiguration().getInt("roundTimer")) - (20L * 10L));
	}
	
	private void doLava() {
		TheFloorIsLava.instance.sendMessage("10 saniye sonra lav yükselecek");

		TheFloorIsLava.instance.getServer().getScheduler().scheduleSyncDelayedTask(TheFloorIsLava.instance, new Runnable() {
			public void run() {
				TheFloorIsLava.instance.sendMessage("5 saniye sonra lav yükselecek");
			}
		}, 20L * 5L);
		
		TheFloorIsLava.instance.getServer().getScheduler().scheduleSyncDelayedTask(TheFloorIsLava.instance, new Runnable() {
			public void run() {
				TheFloorIsLava.instance.sendMessage("4 saniye sonra lav yükselecek");
			}
		}, 20L * 6L);
		
		TheFloorIsLava.instance.getServer().getScheduler().scheduleSyncDelayedTask(TheFloorIsLava.instance, new Runnable() {
			public void run() {
				TheFloorIsLava.instance.sendMessage("3 saniye sonra lav yükselecek");
			}
		}, 20L * 7L);
		
		TheFloorIsLava.instance.getServer().getScheduler().scheduleSyncDelayedTask(TheFloorIsLava.instance, new Runnable() {
			public void run() {
				TheFloorIsLava.instance.sendMessage("2 saniye sonra lav yükselecek");
			}
		}, 20L * 8L);
		
		TheFloorIsLava.instance.getServer().getScheduler().scheduleSyncDelayedTask(TheFloorIsLava.instance, new Runnable() {
			public void run() {
				TheFloorIsLava.instance.sendMessage("1 saniye sonra lav yükselecek");
			}
		}, 20L * 9L);
		
		TheFloorIsLava.instance.getServer().getScheduler().scheduleSyncDelayedTask(TheFloorIsLava.instance, new Runnable() {
			public void run() {				
				TheFloorIsLava.instance.sendMessage("Lav Yükseliyor!");
				
				LavaInfo lavaInfo = TheFloorIsLava.instance.getLavaInfo();
				
				World world = lavaInfo.bottomRight.getWorld();
				Location edgeMin = lavaInfo.bottomRight;
				Location edgeMax = lavaInfo.topLeft;
			   
			    for (int x = edgeMin.getBlockX(); x <= edgeMax.getBlockX(); x ++) {
			        for (int y = edgeMin.getBlockY(); y <= edgeMax.getBlockY(); y ++) {
			            for (int z = edgeMin.getBlockZ(); z <= edgeMax.getBlockZ(); z ++) {
			            	Block block = new Location(world, x, y, z).getBlock();
			            	
			                if (block.getType() == Material.AIR) {
			                    block.setType(TheFloorIsLava.instance.getBlock());
			                }
			            }
			        }
			    }
				
				lavaInfo.IncreaseCurrentLevel();
				
				lavaTimer();
			}
		}, 20L * 10L);
	}
}
