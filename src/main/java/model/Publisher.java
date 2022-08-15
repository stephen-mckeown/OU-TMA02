package model;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    public Integer publisherId;
    private String publisherName;

    public Double fee;

    public List<Integer> blackList = new ArrayList<Integer>();

    public Publisher(Integer publisherId, String publisherName, Double fee, List<Integer> blackList) {
        this.publisherId = publisherId;
        this.publisherName = publisherName;
        this.fee = fee;
        this.blackList = blackList;
    }
}
