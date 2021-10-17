package net.cukrus.woValidationDemo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Part {
    private String name;
    @JsonProperty("inventory_number")
    private String inventoryNumber;
    private Integer count;

    public Part() {}

    public Part(String name, String inventoryNumber, Integer count) {
        this.name = name;
        this.inventoryNumber = inventoryNumber;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}