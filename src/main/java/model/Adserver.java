package model;

import java.util.HashMap;
import java.util.List;

public class Adserver {

    // this method is for the System Design SO 02 - getAdvert
    public HashMap<Integer, Advert> GetAdverts(AccountManager accountManager, InventoryManager inventoryManager)
    {
        return accountManager.GetAdverts(inventoryManager);
    }


    public void CreateContract(AccountManager accountManager,  Publisher publisher, InventoryManager inventoryManager) throws Exception {
       accountManager.createSSPContract(publisher, inventoryManager);
    }

    public void AddAdvertiser(AccountManager accountManager, Advertiser advertiser){
        accountManager.AddAdvertiser(advertiser);
    }
}
