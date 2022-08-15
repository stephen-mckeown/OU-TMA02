import model.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        Adserver adserver = new Adserver();

        AccountManager accountManager = new AccountManager(101, "John Account Manager");
        InventoryManager inventoryManager = new InventoryManager(301, "Clare Inv Manager");

        // blackList of Advertiser's id's that the Publisher does not want to start a contract with
        List<Integer> blackList = new ArrayList<>();
        blackList.add(202);
        Publisher publisher1100 = new Publisher(1100, "the Daily Mash", 10.0, blackList);

        Advertiser advertiser1 = new Advertiser(1, "drinks_company");

        adserver.AddAdvertiser(accountManager, advertiser1);
        adserver.GetAdverts(accountManager, inventoryManager);
        adserver.CreateContract(accountManager, publisher1100, inventoryManager);
    }
}