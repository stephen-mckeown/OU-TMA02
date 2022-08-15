package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class InventoryManager {

    private Integer id;
    private String name;

    public InventoryManager(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    //associations
    private HashMap<Integer, Advert> advertsApprovedMap = new HashMap<>();
    public void ApproveAdvert(Advert advert)  {
        // Review Advert and if accepted add to the Map of approved Adverts
        if(advert.advertName.contains("*")){
            System.out.println("advert not approved: " + advert.advertId);
        } else {
            advertsApprovedMap.put(advert.advertiserId, advert);
        }
    }

    public HashMap<Integer, Advert> GetApprovedAds() throws Exception {
        // There must be at least 1 approved advert
        if(advertsApprovedMap.isEmpty()){
            throw new Exception("no approved ads");
        }
            return advertsApprovedMap;
    }

    public Advert GetApprovedAd(ArrayList<Integer> advertiserIds) throws Exception {
        if(advertiserIds.isEmpty()){
            throw new Exception("no approved advertisers");
        } else if(advertsApprovedMap.isEmpty()) {
            throw new Exception("no approved ads");
        } else {
            return advertsApprovedMap.get(advertiserIds.get(0));
        }
    }
}
