package uk.hubcontroller.Objects;

import uk.hubcontroller.Storage.DB;

public class SServer {
    String Type;
    String Name;
    SStatus Status;
    int CurPlayers;
    int MaxPlayers;
    String BungeeID;
    int Port;
    
    public SServer(String Ty,String Na,SStatus St,int CP,int MP,String BI,int P)
    {;this.Type = Ty;this.Name = Na;
     this.Status = St;this.CurPlayers = CP;
     this.MaxPlayers = MP;this.BungeeID =BI;this.Port = P;}
    
    public String getType(){return this.Type;}
    public String getName(){return this.Name;}
    public SStatus getStatus(){return this.Status;}
    public int getCurPlayers(){return this.CurPlayers;}
    public int getMaxPlayers(){return this.MaxPlayers;}
    public String getBungeeID(){return this.BungeeID;}
    public int getPort(){return this.Port;}
    
    public void setAll(String Ty,String Na,SStatus St,int CP,int MP,String BI,int P)
    {
     this.Type = Ty;this.Name = Na;
     this.Status = St;this.CurPlayers = CP;
     this.MaxPlayers = MP;this.BungeeID =BI;
     this.Port = P;
    }
    public void setStatus(SStatus s){this.Status = s;DB.updateStatus(this, Status.toString());}
    public void setMaxPlayers(int mp){this.MaxPlayers = mp;DB.updateMaxPlayers(this, MaxPlayers);}
}