package pizzeria.dbService.dataSets;

public class Ingredient {
    public Ingredient() {}

    private int id;

    private String name;

    private int price;

    public Ingredient(int id, String name, int price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString(){
        return name + " " + price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
