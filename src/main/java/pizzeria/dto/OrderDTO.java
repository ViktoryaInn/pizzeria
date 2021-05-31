package pizzeria.dto;

import pizzeria.dbService.dataSets.Ingredient;

public class OrderDTO {
    private String clientName;

    private String clientPhone;

    private int cost;

    private Ingredient[] ingredients;

    public OrderDTO(String clientName, String clientPhone, int cost, Ingredient[] ingredients){
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.cost = cost;
        this.ingredients = ingredients;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public int getCost() {
        return cost;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }
}
