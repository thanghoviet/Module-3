package com.example.list_Of_Customers;

public class Customer {
    private String name;
    private String birthday;
    private String andress;
    private String image;

    public Customer(String name, String birthday, String andress, String image) {
        this.name = name;
        this.birthday = birthday;
        this.andress = andress;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAndress() {
        return andress;
    }

    public void setAndress(String andress) {
        this.andress= andress;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
