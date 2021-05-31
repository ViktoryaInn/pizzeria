package pizzeria.dbService.dao;

import pizzeria.dbService.dataSets.Order;
import pizzeria.dbService.dataSets.OrderIngredients;
import pizzeria.dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public class OrderIngredientsDAO {
    private final Executor executor;

    public OrderIngredientsDAO(Connection connection) {
        executor = new Executor(connection);
    }

    public void insert(String orderId, String ingredientId) throws SQLException {
        executor.execUpdate(String.format("insert into ORDER_INGREDIENTS_TABLE (order_id, ingredient_id) values ('%s', '%s')", orderId, ingredientId));
    }

    public OrderIngredients[] getByOrder(String orderId) throws SQLException {
        return executor.execQuery(String.format("select * from ORDER_INGREDIENTS_TABLE where order_id='%s'", orderId), result -> {
            var list = new LinkedList<OrderIngredients>();
            while(result.next()){
                list.add(new OrderIngredients(
                        result.getString("ingredient_id"),
                        result.getString("order_id")));
            }
            return list.toArray(new OrderIngredients[0]);
        });
    }

    public void deleteByOrder(String orderId) throws SQLException {
        executor.execUpdate(String.format("delete from ORDER_INGREDIENTS_TABLE where order_id='%s'",  orderId));
    }

    public void deleteByIngredient(String ingredientId) throws SQLException {
        executor.execUpdate(String.format("delete from ORDER_INGREDIENTS_TABLE where ingredient_id='%s'",  ingredientId));
    }
}
