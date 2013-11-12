package uk.co.shadycast.shadycontroller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import uk.co.shadycast.shadycontroller.Objects.SServer;
import java.util.HashMap;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.shadycast.shadycontroller.Events.JoinLeave;
import uk.co.shadycast.shadycontroller.Objects.SPlayer;
import uk.co.shadycast.shadycontroller.Storage.DB;


public class ShadyController extends JavaPlugin {
    public static HashMap<String,SServer> Servers;
    public static HashMap<String,SPlayer> Players;
    public static String BungeeID;
    public static DateFormat dateFormat ;
    @Override
    public void onDisable(){
        
    }
    
    @Override
    public void onEnable(){
        //If empty config stop load
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DB.init();
        Servers = new HashMap<String,SServer>();
        Players = new HashMap<String,SPlayer>();
        getServer().getPluginManager().registerEvents(new JoinLeave(), this);
        int port = getServer().getPort();
        //BungeeID = DB.getBungeeID(port);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        
    }
    /**
     * Returns a Shady player usable for DB access and updating as well as rank checking
     * @param p Any Player logged on to the server already
     * @return SPlayer An Shady Player 
     */
    public static SPlayer getPlayer(Player p){return Players.get(p.getName());}
    /**
     * Returns all Shady Server's usable for DB access and to get info from
     * @return List<SServer> A list of all registered servers on the network
     */
    public static List<SServer> GetAllServers(){return DB.getAllServers();}
    /**
     * Returns a Shady Server usable for DB access and to get info from
     * @param BungeeID The Id of the Bungee server the same as the DB and bungee config
     * @return SServer A registered servers on the network
     */
    public static SServer getServer(String BungeeID){return Servers.get(BungeeID);}
    /**
     * Returns the current Shady Server usable for DB access and to get info from
     * @return SServer The current registered server on the network
     */
    public static SServer getThisServer(){return Servers.get(BungeeID);}
    /**
     * Updates the specified Shady Servers Max player value
     * @param BungeeID The Id of the Bungee server the same as the DB and bungee config
     * @param PlayerCount The Value for Max Players for the Shady Server
     */
    public static void UpdateMaxPlayers(String BungeeID,int PlayerCount){DB.updateMaxPlayers(getServer(BungeeID), PlayerCount);}
    /**
     * Updates the specified Shady Servers Current player value 
     * @param BungeeID The Id of the Bungee server the same as the DB and bungee config
     * @param PlayerCount The Value for Max Players for the Shady Server
     */
    private static void UpdateCurPlayers(String BungeeID,int PlayerCount){DB.updateCurPlayers(getServer(BungeeID), PlayerCount);}


}
