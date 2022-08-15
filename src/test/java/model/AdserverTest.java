package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdserverTest {
    Adserver testAdserver = new Adserver();
    AccountManager accountManagerTest = new AccountManager(888, "Test Account Manager");
    InventoryManager inventoryManagerTest = new InventoryManager(999, "Test Inv Manager");
    Advertiser advertiserTest1 = new Advertiser(1, "Test Advertiser_1");
    Advertiser advertiserTest2 = new Advertiser(2, "Test Advertiser_2");


    SSPContract sspContract1 = new SSPContract(10,110,10.0, "10_A");


    @Test
    @DisplayName("get adverts for all advertisers")
    void getAdverts() {
        testAdserver.AddAdvertiser(accountManagerTest, advertiserTest1);
        testAdserver.AddAdvertiser(accountManagerTest, advertiserTest2);
        assertEquals("Test Advertiser_1 advert",testAdserver.GetAdverts(accountManagerTest, inventoryManagerTest).get(1).advertName);
        assertEquals("Test Advertiser_2 advert",testAdserver.GetAdverts(accountManagerTest, inventoryManagerTest).get(2).advertName);
    }

    @Test
    @DisplayName("add advertiser to hashMap")
    void addAdvertiser() {
        testAdserver.AddAdvertiser(accountManagerTest, advertiserTest1);
        assertEquals("Test Advertiser_1", accountManagerTest.advertisers.get(1).advertiserName);
    }


    @Test
    @DisplayName("returns 'no approved ads' when creating a contract with no adverts")
    void createContractFailAdverts() {
        testAdserver.AddAdvertiser(accountManagerTest, advertiserTest1);
        List<Integer> blackListTest = new ArrayList<>();
        blackListTest.add(2);
        Publisher publisherTest = new Publisher(1111, "Test Publisher", 99.0, blackListTest);
        Exception exception = assertThrows( Exception.class, ()-> {
            accountManagerTest.createSSPContract(publisherTest, inventoryManagerTest);
        });

        System.out.println(exception.getMessage());
        System.out.println(exception.getMessage());
        String expectedMessage = "no approved ads";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("returns contractId on successful creation of contract")
    void createContractPass() throws Exception {
        testAdserver.AddAdvertiser(accountManagerTest, advertiserTest1);
        inventoryManagerTest.ApproveAdvert(advertiserTest1.GetAdvert());
        List<Integer> blackListTest = new ArrayList<>();
        blackListTest.add(3);
        Publisher publisherTest = new Publisher(1111, "Test Publisher", 99.0, blackListTest);

        Integer contractId = accountManagerTest.createSSPContract(publisherTest, inventoryManagerTest);
        System.out.println(contractId);
        assertEquals( Integer.class, contractId.getClass());
    }

    @Test
    @DisplayName("returns 'no approved advertiser' when creating a contract with no advertiser on blackList")
    void createContractFailAdvertisers() {
        testAdserver.AddAdvertiser(accountManagerTest, advertiserTest1);
        List<Integer> blackListTest = new ArrayList<>();
        blackListTest.add(1);
        Publisher publisherTest = new Publisher(1111, "Test Publisher", 99.0, blackListTest);
        Exception exception = assertThrows( Exception.class, ()-> {
            accountManagerTest.createSSPContract(publisherTest, inventoryManagerTest);
        });

        System.out.println(exception.getMessage());
        System.out.println(exception.getMessage());
        String expectedMessage = "no approved advertisers";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("fails when contract exists of the same type for the same publisher id")
    void CheckContractTypeFail() {

        List<Integer> blackList = new ArrayList<>();
        blackList.add(202);
        Publisher publisher = new Publisher(110, "Publisher Test 110", 10.0, blackList);
        SSPContract sspContract1 = new SSPContract(10,publisher.publisherId,10.0, "10_A");
        accountManagerTest.contracts.put(sspContract1.contractId, sspContract1);

        Exception exception = assertThrows( Exception.class, ()-> {
            accountManagerTest.CheckContract(publisher, ContractType.SUPPLY_SOURCED);
        });

        System.out.println(exception.getMessage());
        System.out.println(exception.getMessage());
        String expectedMessage = "Contract of type: SUPPLY_SOURCED exists for publisher: 110";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}