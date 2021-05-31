package pizzeria.dbService.dataSets;

public class OrderIngredients {

    private String ingredientId;

    private String orderId;

    public OrderIngredients(String ingredientId, String orderId){
        this.ingredientId = ingredientId;
        this.orderId = orderId;
    }

    public String getIngredientId() {
        return ingredientId;
    }

    public String getOrderId() {
        return orderId;
    }
}
