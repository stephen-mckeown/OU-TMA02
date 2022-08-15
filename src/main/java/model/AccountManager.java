package model;

import java.util.*;
import java.util.stream.Collectors;

public class AccountManager {

    private Integer id;
    private String name;

    public AccountManager(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // associations

    HashMap<Integer, Advertiser> advertisers = new HashMap<>();

    Map<Integer, Contract> contracts = new HashMap<>();





    public void AddAdvertiser(Advertiser advertiser){
        advertisers.put(advertiser.advertiserId, advertiser);
    }

    public Advertiser GetAdvertiser(Integer advertiserId){
        return advertisers.get(advertiserId);
    }


    public HashMap<Integer, Advert> GetAdverts(InventoryManager inventoryManager) {
        // filter out Advertisers that appear on the Publisher's blackList
//        Set<Advertiser> approvedAdvertisersSet = GetApprovedAdvertisers(blackList);
        HashMap<Integer, Advert> approvedAds = new HashMap<>();
        try {
            //request for ad approval
            RequestForAdApproval(inventoryManager, advertisers);
            approvedAds = inventoryManager.GetApprovedAds();
            return approvedAds;
        } catch (Exception e){
            System.out.println(e);
        }
        return approvedAds;
    }

    public void RequestForAdApproval(InventoryManager inventoryManager, HashMap<Integer, Advertiser> advertisers) throws Exception {
        // There must be at least 1 Advertiser
        if(advertisers.isEmpty()){
            throw new Exception("no advertisers");
        } else {
            advertisers.forEach((key, advertiser) -> {
                // Get the advert from the advertiser and submit to the inventory manager for approval
                inventoryManager.ApproveAdvert(advertiser.GetAdvert());
            });
        }
    }

    public ArrayList<Integer> GetApprovedAdvertisers(List<Integer> blackList) throws Exception {
        // filter out Advertisers that appear on the Publisher's blackList
        Map<Integer, Advertiser> approvedAdvertisersMap = advertisers.entrySet()
            .stream()
            .filter(map -> !blackList.contains(map.getKey()))
            .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

        ArrayList<Integer> approvedAdvertisers = new ArrayList<>(approvedAdvertisersMap.keySet());

        if(approvedAdvertisers.isEmpty()){
            throw new Exception("no approved advertisers");
        } else {
            return approvedAdvertisers;
        }
    }

    public Integer createSSPContract(Publisher publisher, InventoryManager inventoryManager) throws Exception {
        ArrayList<Integer> approvedAdvertisers;
        Integer contractId = 0;
        Advert advert;

        try {
            //check no contract already created for publisher
            CheckContract(publisher, ContractType.SUPPLY_SOURCED);
            //check there is at least 1 approved Advertiser
            approvedAdvertisers = GetApprovedAdvertisers(publisher.blackList);
            //check there is at least 1 approved advert
            advert = inventoryManager.GetApprovedAd(approvedAdvertisers);
            //create unique contract id
            contractId = contracts.size() + 1;
            // create new contract
            SSPContract contract = new SSPContract(contractId, publisher.publisherId,  publisher.fee, advert.advertId);
            contracts.put(contractId, contract);
        } catch (Exception e){
            System.out.println(e);
            throw e;
        }
        return contractId;
    }


    // to check that no other contract exists for the same type and publisherId
    public void CheckContract(Publisher publisher, ContractType contractType){
        Set<Contract> contractsSet = new HashSet<>(contracts.values());
        contractsSet.forEach(contract -> {

            if(contract.GetContractType() == contractType && contract.publisherId == publisher.publisherId){
                throw new RuntimeException("Contract of type: " + contractType + " exists for publisher: " + publisher.publisherId);
            }
        });
    }


}