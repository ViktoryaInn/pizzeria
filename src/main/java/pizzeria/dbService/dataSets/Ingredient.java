package pizzeria.dbService.dataSets;

import java.util.UUID;

public class Ingredient {
    public Ingredient() {}

    private String id = UUID.randomUUID().toString();

    private String name;

    private int price;

    public Ingredient(String name, int price){
        this.name = name;
        this.price = price;
    }

    public Ingredient(String id, String name, int price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString(){
        return name + " " + price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
