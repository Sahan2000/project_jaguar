package controller;


public class Animal {

    public String clmId;
    public String clmName;
    public String clmAge;
    public String clmStatus;
    public String clmGender;
    public String clmFoodType;
    public String clmKingdomType;

    public String getClmId() {
        return clmId;
    }

    public void setClmId(String clmId) {
        this.clmId = clmId;
    }

    public String getClmName() {
        return clmName;
    }

    public void setClmName(String clmName) {
        this.clmName = clmName;
    }

    public String getClmAge() {
        return clmAge;
    }

    public void setClmAge(String clmAge) {
        this.clmAge = clmAge;
    }

    public String getClmStatus() {
        return clmStatus;
    }

    public void setClmStatus(String clmStatus) {
        this.clmStatus = clmStatus;
    }

    public String getClmGender() {
        return clmGender;
    }

    public void setClmGender(String clmGender) {
        this.clmGender = clmGender;
    }

    public String getClmFoodType() {
        return clmFoodType;
    }

    public void setClmFoodType(String clmFoodType) {
        this.clmFoodType = clmFoodType;
    }

    public String getClmKingdomType() {
        return clmKingdomType;
    }

    public void setClmKingdomType(String clmKingdomType) {
        this.clmKingdomType = clmKingdomType;
    }

    public String getClmHealth() {
        return clmHealth;
    }

    public void setClmHealth(String clmHealth) {
        this.clmHealth = clmHealth;
    }

    public String getClmEndangered() {
        return clmEndangered;
    }

    public void setClmEndangered(String clmEndangered) {
        this.clmEndangered = clmEndangered;
    }

    public String getClmOrigin() {
        return clmOrigin;
    }

    public void setClmOrigin(String clmOrigin) {
        this.clmOrigin = clmOrigin;
    }

    public String clmHealth;
    public String clmEndangered;
    public String clmOrigin;

    public Animal(String clmId, String clmName, String clmAge, String clmStatus, String clmGender, String clmFoodType, String clmKingdomType, String clmOrigin, String clmHealth, String clmEndangered) {

        this.clmId = clmId;
        this.clmName = clmName;
        this.clmAge = clmAge;
        this.clmStatus = clmStatus;
        this.clmGender = clmGender;
        this.clmFoodType = clmFoodType;
        this.clmKingdomType = clmKingdomType;
        this.clmHealth = clmHealth;
        this.clmEndangered = clmEndangered;
        this.clmOrigin = clmOrigin;
    }
}
