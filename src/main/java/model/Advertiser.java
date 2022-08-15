package model;

import java.util.ArrayList;

public class Advertiser {

    public Integer advertiserId;
    public String advertiserName;

    public Advertiser(Integer advertiserId, String advertiserName) {
        this.advertiserId = advertiserId;
        this.advertiserName = advertiserName;
    }

    private Advert advert;
    private ArrayList<Advert> advertsArray = new ArrayList<>();


    private Advert CreateAdvert()
    {
        //create a new advert and add to the associations of adverts
        advert = new Advert(advertiserId.toString() + "_A",  advertiserId,advertiserName + " advert");
        advertsArray.add(advert);
        return advert;
    }

    public Advert GetAdvert()
    {
        // If there is no Advert for said Advertiser one will be created
        if(advertsArray.isEmpty()){
            return CreateAdvert();
        } else {
            // The advert should be the latest from the Advertiser
           return advertsArray.get(0);
        }
    }

}
