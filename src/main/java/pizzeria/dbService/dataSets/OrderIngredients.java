package pizzeria.dbService.dataSets;

public class OrderIngredients {
    public OrderIngredients(){}

    private int ingredientId;

    private int orderId;

    public OrderIngredients(int ingredientId, int orderId){
        this.ingredientId = ingredientId;
        this.orderId = orderId;
    }

}
