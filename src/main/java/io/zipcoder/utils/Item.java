package io.zipcoder.utils;

import java.util.Objects;

/**
 * @ATTENTION_TO_STUDENTS - You are forbidden from modifying this class
 */
public class Item {
    private String name;
    private Double price;
    private String type;
    private String expiration;

    /**
     * Item should not be created unless you have all of the elements, which is why you are forcing
     * it to be set in the constructor. In ItemParser, if you do not find all the elements of a Item,
     * you should throw an Custom Exception.
     * @param name
     * @param price
     * @param type
     * @param expiration
     */
    public Item(String name, Double price, String type, String expiration){
        this.name = name;
        this.price = price;
        this.type = type;
        this.expiration = expiration;
    }

    public String getName() {
        return name;
    }


    public Double getPrice() {
        return price;
    }


    public String getType() {
        return type;
    }


    public String getExpiration() {
        return expiration;
    }

    @Override
    public String toString(){
        return "name:" + name + " price:" + price + " type:" + type + " expiration:" + expiration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(name, item.name) &&
                Objects.equals(price, item.price) &&
                Objects.equals(type, item.type) &&
                Objects.equals(expiration, item.expiration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, type, expiration);
    }
}
