package model;

public abstract class Contract {
    Integer contractId;
    Integer publisherId;
    String adId;
    Double feeValue;

    public Contract(Integer contractId, Integer publisherId,  String adId, Double feeValue){
        this.contractId = contractId;
        this.publisherId = publisherId;
        this.adId = adId;
        this.feeValue = feeValue;
    }

    public abstract ContractType GetContractType();
    public abstract void CreateFee(Integer feeId,Integer contractId,Integer feeValue);
}
