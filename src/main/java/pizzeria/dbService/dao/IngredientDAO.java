package pizzeria.dbService.dao;

import pizzeria.dbService.dataSets.Ingredient;
import pizzeria.dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public class IngredientDAO {
    private final Executor executor;

    public IngredientDAO(Connection connection){
        executor = new Executor(connection);
    }

    public void insert(Ingredient ingredient) throws SQLException {
        executor.execUpdate(String.format("insert into INGREDIENT_TABLE (id, name, price) values ('%s', '%s', %d)",
                ingredient.getId(), ingredient.getName(), ingredient.getPrice()));
    }

    public Ingredient get(String id) throws SQLException {
        return executor.execQuery(String.format("select * from INGREDIENT_TABLE where id='%s'", id), result -> {
            try {
                result.next();
                return new Ingredient(
                        result.getString("id"),
                        result.getString("name"),
                        result.getInt("price")
                );
            } catch (SQLException e){
                return null;
            }
        });
    }

    public Ingredient[] getAll() throws SQLException {
        return executor.execQuery("select * from INGREDIENT_TABLE", result -> {
            var list = new LinkedList<Ingredient>();
            while (result.next())
            {
                list.add(new Ingredient(
                        result.getString("id"),
                        result.getString("name"),
                        result.getInt("price")
                ));
            }

            return list.toArray(new Ingredient[0]);
        });
    }

    public void update(Ingredient ingredient) throws SQLException {
        executor.execUpdate(String.format("update INGREDIENT_TABLE set price=%d, name='%s' where id='%s'",
                ingredient.getPrice(), ingredient.getName(), ingredient.getId()));
    }

    public void delete(String id) throws SQLException {
        executor.execUpdate(String.format("delete from INGREDIENT_TABLE where id='%s'", id));
    }
}
