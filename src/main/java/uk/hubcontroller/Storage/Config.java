package uk.hubcontroller.Storage;

import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import uk.hubcontroller.Objects.SSign;
import uk.hubcontroller.HubController;
import uk.hubcontroller.Utils.pluginUtils;

public class Config {
    
    
    public static HashMap<Location, SSign> importSigns() {
        HashMap<Location, SSign> ss = new HashMap<Location, SSign>();
        FileConfiguration config = pluginUtils.getConfig();
        
        List<String> Signs = (List<String>) config.getList("TheHub.Signs");
        String[] Signss = Signs.toArray(new String[0]);
        if(!Signs.isEmpty()){
        for(String s:Signss){ss.put(stringToLocation(s),new SSign(HubController.getServer(getBungeeID(s)),stringToLocation(s)));}
        }
        
        return ss;
    }

    public static Location stringToLocation(String s) {
        String[] ls = s.split(",");
        World w = Bukkit.getWorld(ls[0]);
        double X = Double.parseDouble(ls[1]);
        double Y = Double.parseDouble(ls[2]);
        double Z = Double.parseDouble(ls[3]);

        return new Location(w, X, Y, Z);
    }

    public static String locationToString(Location l) {
        return l.getWorld().getName() +","+ l.getX() +","+ l.getY() +","+ l.getZ();
    }

    public static String getBungeeID(String s) {
        String[] ls = s.split(",");
        String ss = ls[4];
        return ss;
    }
}