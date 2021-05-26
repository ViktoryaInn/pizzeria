package pizzeria.dbService.dataSets;

import javax.persistence.*;

@Entity
@Table(name = "Ingredient")
public class Ingredient {
    public Ingredient() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
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
