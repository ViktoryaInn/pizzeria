package pizzeria.dbService;

import pizzeria.dbService.dao.IngredientDAO;
import pizzeria.dbService.dao.OrderDAO;
import pizzeria.dbService.dataSets.Ingredient;
import pizzeria.dbService.dataSets.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
    private final Connection connection;

    public DBService() {
        this.connection = getMySQLConnection();
        System.out.println("Соединение с СУБД выполнено");
    }

    private Connection getMySQLConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/pizzeria_db";
            String login = "root";
            String pass = "123456789aa_AA";

            return DriverManager.getConnection(url, login, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Ingredient[] getListIngredient() {
        try {
            return new IngredientDAO(connection).getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new Ingredient[0]; // TODO: исправить
        }
    }

    public Ingredient getIngredient(int id) {
        try{
            return new IngredientDAO(connection).get(id);
        } catch (SQLException e){
            e.printStackTrace();
            return new Ingredient(); // TODO: исправить
        }
    }

    public void addIngredient(Ingredient ingredient) {
        try {
            connection.setAutoCommit(false);
            new IngredientDAO(connection).insert(ingredient);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateIngredient(Ingredient ingredient) {
        try {
            connection.setAutoCommit(false);
            new IngredientDAO(connection).update(ingredient);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteIngredient(int id) {
        try {
            connection.setAutoCommit(false);
            new IngredientDAO(connection).delete(id);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Order[] getListOrder() {
        try {
            return new OrderDAO(connection).getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new Order[0]; // TODO: исправить
        }
    }

    public Order getOrder(int id) {
        try{
            return new OrderDAO(connection).get(id);
        } catch (SQLException e){
            e.printStackTrace();
            return new Order(); // TODO: исправить
        }
    }

    public void addOrder(Order order) {
        try {
            connection.setAutoCommit(false);
            new OrderDAO(connection).insert(order);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOrder(Order order) {
        try {
            connection.setAutoCommit(false);
            new OrderDAO(connection).update(order);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id) {
        try {
            connection.setAutoCommit(false);
            new OrderDAO(connection).delete(id);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
