package com.github.dublekfx.Meteor;

import java.util.ArrayList;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.EntityBlockFormEvent;

public class Meteorite {
	
	private int radius;
	private final int DEFAULT_RADIUS = 3;
	private Location target;
	private Location skyTarget;
	private boolean falling;
	private Material mat = Material.NETHERRACK;
	private ArrayList<Location> sphereCoords = new ArrayList<Location>();

	public Meteorite(Location xyz)	{
		target = xyz;
		radius = DEFAULT_RADIUS;
		target = skyTarget.clone();
		target.setY(target.getWorld().getHighestBlockAt(target).getY());
		skyTarget.setY(255 - radius);
		falling = false;
	}
	public Meteorite(Location xyz, int r)	{
		target = xyz;
		radius = r;
		target = skyTarget.clone();
		target.setY(target.getWorld().getHighestBlockAt(target).getY());
		skyTarget.setY(255 - radius);
		falling = false;
	}
	public boolean isFalling()	{
		return falling;
	}
	public void setFalling(boolean drop)	{
		drop = falling;
	}
		
	public void dropMeteorite()	{
		if (sphereCoords.size() >= 1 && this.isFalling())	{	//Ensures that genSphereCoords has been run, and falling = true
			for (Location a : sphereCoords)	{			
				skyTarget.getWorld().spawnFallingBlock(a, mat, (byte) 0);
			}
			Logger.getLogger("Minecraft").info("Let Chaos Reign!");
		}
		else
			Logger.getLogger("Minecraft").info("Preconditions not met!");
	}
	
	public void genMeteorite()	{	//Creates a floating ball. dropMeteor must be called separately
		this.genSphereCoords();
		for (Location a : sphereCoords)	{			
				a.getBlock().setType(mat);
		}
	}
	
	@EventHandler
	public void onEntityBlockForm(EntityBlockFormEvent event){
		if(event.getBlock().getLocation().equals(target) && event.getBlock().getType().equals(mat))	{
			this.explode();
		}
	}
	
	private void genSphereCoords()	{
		double bpow = Math.pow(radius, 2);
		double bx = skyTarget.getX();
		double by = skyTarget.getY();
		double bz = skyTarget.getZ();
		
		 for (int z = 0; z <= radius; z++) {
	            double zpow = Math.pow(z, 2);
	            for (int x = 0; x <= radius; x++) {
	                double xpow = Math.pow(x, 2);
	                for (int y = 0; y <= radius; y++) {
	                    if ((xpow + Math.pow(y, 2) + zpow) <= bpow) {
	                    	sphereCoords.add(new Location(skyTarget.getWorld(), bx + x, by + y, bz + z));
	                    	sphereCoords.add(new Location(skyTarget.getWorld(), bx + x, by + y, bz - z));
	                    	sphereCoords.add(new Location(skyTarget.getWorld(), bx - x, by + y, bz + z));
	                    	sphereCoords.add(new Location(skyTarget.getWorld(), bx - x, by + y, bz - z));
	                    	sphereCoords.add(new Location(skyTarget.getWorld(), bx + x, by - y, bz + z));
	                    	sphereCoords.add(new Location(skyTarget.getWorld(), bx + x, by - y, bz - z));
	                    	sphereCoords.add(new Location(skyTarget.getWorld(), bx - x, by - y, bz + z));
	                    	sphereCoords.add(new Location(skyTarget.getWorld(), bx - x, by - y, bz - z));
	                    }
	                }
	            }
		 }
	}

	private void explode()	{
		target.getWorld().createExplosion(target, radius);
	}
}
