package com.github.dublekfx.Meteor;

import java.util.ArrayList;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.Material;

public class Meteorite {
	
	private int radius;
	private final int DEFAULT_RADIUS = 3;
	private Location target;
	private boolean falling;
	private ArrayList<Location> sphereCoords = new ArrayList<Location>();

	public Meteorite(Location xyz)	{
		target = xyz;
		radius = DEFAULT_RADIUS;
		falling = true;
		target.setY(255 - radius);
	}
	public Meteorite(Location xyz, int r)	{
		target = xyz;
		radius = r;
		falling = true;
		target.setY(255 - radius);
	}
	public Meteorite(Location xyz, int r, boolean drop)	{
		target = xyz;
		radius = r;		
		falling = drop;
		target.setY(255 - radius);
	}
		
	public void genMeteorite()	{
		this.genSphere();
		for (Location a : sphereCoords)	{			
				target.getWorld().spawnFallingBlock(a, Material.NETHERRACK, (byte) 0);
		}
		Logger.getLogger("Minecraft").info("Let Chaos Reign!");
	}
	
	private void genSphere()	{
		double bpow = Math.pow(radius, 2);
		double bx = target.getX();
		double by = target.getY();
		double bz = target.getZ();
		
		 for (int z = 0; z <= radius; z++) {
	            double zpow = Math.pow(z, 2);
	            for (int x = 0; x <= radius; x++) {
	                double xpow = Math.pow(x, 2);
	                for (int y = 0; y <= radius; y++) {
	                    if ((xpow + Math.pow(y, 2) + zpow) <= bpow) {
	                    	sphereCoords.add(new Location(target.getWorld(), bx + x, by + y, bz + z));
	                    	sphereCoords.add(new Location(target.getWorld(), bx + x, by + y, bz - z));
	                    	sphereCoords.add(new Location(target.getWorld(), bx - x, by + y, bz + z));
	                    	sphereCoords.add(new Location(target.getWorld(), bx - x, by + y, bz - z));
	                    	sphereCoords.add(new Location(target.getWorld(), bx + x, by - y, bz + z));
	                    	sphereCoords.add(new Location(target.getWorld(), bx + x, by - y, bz - z));
	                    	sphereCoords.add(new Location(target.getWorld(), bx - x, by - y, bz + z));
	                    	sphereCoords.add(new Location(target.getWorld(), bx - x, by - y, bz - z));
	                    }
	                }
	            }
		 }
	}
}
