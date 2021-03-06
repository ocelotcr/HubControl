package uk.hubcontroller.Events;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import uk.hubcontroller.Objects.SPlayer;
import uk.hubcontroller.HubController;
import uk.hubcontroller.Storage.DB;
import uk.hubcontroller.Utils.Utils;


public class JoinLeave implements Listener{
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void Join(PlayerLoginEvent evt){
        boolean b = false;
        Date d = new Date();
        String reas = null;
        Date fin = null;
        String p = evt.getPlayer().getName();
        try {
            Statement st1 = con.createStatement();
            ResultSet r1 = st1.executeQuery("SELECT * FROM Bans WHERE Name = '" + p + "'");
            if (!r1.wasNull()) {
                if (r1.next()) {
                    fin = r1.getDate(6);
                    if (fin.before(d)) {
                        Statement st2 = con.createStatement();
                        st2.executeUpdate("DELETE FROM Bans WHERE Name = '" + p + "'");
                        Statement st3 = con.createStatement();
                        st3.executeQuery("UPDATE Users SET Banned='"+Utils.javaToSql(false)+"' WHERE Name='"+p+"'");
                        b = false;
                    } else {
                        reas = r1.getString("Reason");
                        b = true;
                    }
                }
            } else {
                b = false;
            }
        } catch (SQLException ex) {
        }
        if(b){evt.disallow(PlayerLoginEvent.Result.KICK_BANNED, "Banned until " + fin + " Reason: " + reas);}
    }
    
    @EventHandler
    public void Join(PlayerJoinEvent evt){
        Player p = evt.getPlayer();
        Date d = new Date();
        SPlayer sp;
        DB.addShadyPlayer(p.getName(), "Player", d, d, 0);
        sp = DB.getShadyPlayer(evt.getPlayer());
        HubController.Players.put(p.getName(), sp);
        DB.setPlayerLatestJoin(sp.getName(), d);
        DB.updateCurPlayers(HubController.getThisServer());
    }
    
    @EventHandler
    public void Leave(PlayerQuitEvent evt){
        Player p = evt.getPlayer();
        SPlayer sp = HubController.getPlayer(p);
        if(HubController.Players.containsKey(sp.getName())){HubController.Players.remove(sp.getName());}else{}
        DB.updateCurPlayers(HubController.getThisServer());
    } 
}