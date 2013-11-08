package uk.co.shadycast.shadycontroller.Objects;

import uk.co.shadycast.shadycontroller.Storage.DB;

public class SServer {
    int id;
    String Type;
    String Name;
    String Status;
    int CurPlayers;
    int MaxPlayers;
    String BungeeID;
    
    public SServer(int i,String Ty,String Na,String St,int CP,int MP,String BI)
    {this.id = i;this.Type = Ty;this.Name = Na;
     this.Status = St;this.CurPlayers = CP;
     this.MaxPlayers = MP;this.BungeeID =BI;}
    
    public int getID(){return this.id;}
    public String getType(){return this.Type;}
    public String getName(){return this.Name;}
    public String getStatus(){return this.Status;}
    public int getCurPlayers(){return this.CurPlayers;}
    public int getMaxPlayers(){return this.MaxPlayers;}
    public String getBungeeID(){return this.BungeeID;}
    
    public void setAll(String Ty,String Na,String St,int CP,int MP,String BI)
    {
     this.Type = Ty;this.Name = Na;
     this.Status = St;this.CurPlayers = CP;
     this.MaxPlayers = MP;this.BungeeID =BI;
    }
    public void setStatus(String s){this.Status = s;DB.UpdateStatus(this, Status);}
    public void setCurPlayers(int cp){this.CurPlayers = cp;DB.UpdateCurPlayers(this, CurPlayers);}
    public void setMaxPlayers(int mp){this.MaxPlayers = mp;DB.UpdateMaxPlayers(this, MaxPlayers);}
    
}