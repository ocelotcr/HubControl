package uk.hubcontroller.Utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import uk.hubcontroller.Objects.SServer;


public class Utils {

    
    public void SendTo(Player p,SServer s){
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("Connect");
            out.writeUTF(s.getBungeeID()); // Target Server
        } catch (IOException e) {
            // Can never happen
        }
        p.sendPluginMessage(pluginUtils.getPlugin(), "BungeeCord", b.toByteArray());
    }
    public static boolean isInt(String str) {
    try {
        Integer.parseInt(str);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
    }
        public static boolean sqlToJava(int i){
            boolean b = false;
            if(i == 0){
                b = false;
            }else if(i == 1){
                b = true;
            }
            return b;
        }
        public static int javaToSql(boolean b){
            int i = 0;
            if(b){
                i = 1;
            }else if(!b){
                i = 0;
            }
            return i;
        }
        
}