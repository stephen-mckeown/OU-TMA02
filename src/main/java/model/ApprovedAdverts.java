package model;

public class ApprovedAdverts {

    private Integer advertId;
    private Integer advertiser_id;
    private Boolean status;
    public ApprovedAdverts(Integer advertId, Integer advertiser_id, Boolean status) {
        this.advertId = advertId;
        this.advertiser_id = advertiser_id;
        this.status = status;

    }
}
