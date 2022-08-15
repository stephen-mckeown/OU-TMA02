package model;

public class SSPContract extends Contract {
    public ContractType contractType;

    public SSPContract(Integer contractId, Integer publisher, Double feeValue, String adId){
        super(contractId, publisher, adId, feeValue);
        this.contractType = ContractType.SUPPLY_SOURCED;
    }

    public ContractType GetContractType(){
        return contractType;
    }

    @Override
    public void CreateFee(Integer feeId, Integer contractId, Integer feeValue) {
        System.out.println("NEW FEE");
    }
}