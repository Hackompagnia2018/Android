package com.federicobenedetti.res;

public class Product {
    private String title;
    private String product;
    private String place;
    private String address;
    private String pNumber;
    private boolean bOrS = false; // false = Buyer, true = Seller
    private int quantity;
    private int price;

    public Product() {
    }

    public Product(String title, String product, String place, String address, String pNumber, boolean bOrS, int quantity, int price) {
        this.title = title;
        this.product = product;
        this.place = place;
        this.address = address;
        this.pNumber = pNumber;
        this.bOrS = bOrS;
        this.quantity = quantity;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getProduct() {
        return product;
    }

    public String getPlace() {
        return place;
    }

    public String getAddress() {
        return address;
    }

    public String getpNumber() {
        return pNumber;
    }

    public boolean isbOrS() {
        return bOrS;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
}
