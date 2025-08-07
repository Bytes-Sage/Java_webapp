package com.example.demo.model;

import java.math.BigDecimal;
import java.sql.Date;

public class BankAsset {
    private int id;
    private String categoryId;
    private String assetsName;
    private BigDecimal assetsAmount;
    private int durationMonths;
    private Date purchasedDate;
    private String branch;
    private Date lastDepDate;
    private String status;
    
    // Default constructor
    public BankAsset() {}
    
    // Constructor with all fields
    public BankAsset(int id, String categoryId, String assetsName, BigDecimal assetsAmount,
                    int durationMonths, Date purchasedDate, String branch, Date lastDepDate, String status) {
        this.id = id;
        this.categoryId = categoryId;
        this.assetsName = assetsName;
        this.assetsAmount = assetsAmount;
        this.durationMonths = durationMonths;
        this.purchasedDate = purchasedDate;
        this.branch = branch;
        this.lastDepDate = lastDepDate;
        this.status = status;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getAssetsName() {
        return assetsName;
    }
    
    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }
    
    public BigDecimal getAssetsAmount() {
        return assetsAmount;
    }
    
    public void setAssetsAmount(BigDecimal assetsAmount) {
        this.assetsAmount = assetsAmount;
    }
    
    public int getDurationMonths() {
        return durationMonths;
    }
    
    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }
    
    public Date getPurchasedDate() {
        return purchasedDate;
    }
    
    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }
    
    public String getBranch() {
        return branch;
    }
    
    public void setBranch(String branch) {
        this.branch = branch;
    }
    
    public Date getLastDepDate() {
        return lastDepDate;
    }
    
    public void setLastDepDate(Date lastDepDate) {
        this.lastDepDate = lastDepDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "BankAsset{" +
                "id=" + id +
                ", categoryId='" + categoryId + '\'' +
                ", assetsName='" + assetsName + '\'' +
                ", assetsAmount=" + assetsAmount +
                ", durationMonths=" + durationMonths +
                ", purchasedDate=" + purchasedDate +
                ", branch='" + branch + '\'' +
                ", lastDepDate=" + lastDepDate +
                ", status='" + status + '\'' +
                '}';
    }
}