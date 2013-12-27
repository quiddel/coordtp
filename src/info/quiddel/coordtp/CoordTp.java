package info.quiddel.coordtp;


import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CoordTp extends JavaPlugin{
	public void onEnable() {
		System.out.println("coordtp: sucessfully initialized!");
	}
	public void onDisable() {
		;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("coordtp")) {
			//is it targetted on yourself...
			if (args.length == 3) {
				if (sender instanceof Player) {
					if  (sender.isPermissionSet("coordtp.use.self") || sender.isOp()) {
						Player player = (Player) sender;
						player.teleport(new Location(player.getWorld(),
								Integer.parseInt(args[0]),
								Integer.parseInt(args[1]),
								Integer.parseInt(args[2])));
						return true;
					} else {
						sender.sendMessage("YOU DON'T HAVE ACCESS TO THAT COMMAND!");
						return true;
					}
				} else {
					sender.sendMessage("YOU MUST SPECIFY A PLAYER");
				}
			//... or someone else?
			} else if (args.length == 4) {
				//is the command issued from a player...
				if (sender instanceof Player) {
					if (sender.isPermissionSet("coordtp.use.others")) {
						Player player = sender.getServer().getPlayer(args[0]);
						player.teleport(new Location(player.getWorld(),
								Integer.parseInt(args[1]),
								Integer.parseInt(args[2]),
								Integer.parseInt(args[3])));
						return true;
					} else {
						sender.sendMessage("YOU DON'T HAVE ACCESS TO THAT COMMAND!");
						return true;
					}
					//... or the server-console?
				} else {
					Player player = sender.getServer().getPlayer(args[0]);
					player.teleport(new Location(player.getWorld(),
							Integer.parseInt(args[1]),
							Integer.parseInt(args[2]),
							Integer.parseInt(args[3])));
					return true;
				}
			}
		}
		return false;
	}
}
