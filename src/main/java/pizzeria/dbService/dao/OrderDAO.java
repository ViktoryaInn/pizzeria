package pizzeria.dbService.dao;

import pizzeria.dbService.dataSets.Order;
import pizzeria.dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class OrderDAO {
    private final Executor executor;
    private final SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public OrderDAO(Connection connection) {
        executor = new Executor(connection);
    }

    public void insert(Order order) throws SQLException {
        executor.execUpdate(String.format("insert into Order (client_name, client_phone, cost, date) values ('%s', '%s', %d, '%s')",
                order.getClientName(), order.getClientPhone(), order.getCost(), sqlFormat.format(order.getDate())));
    }

    public Order get(int id) throws SQLException {
        return executor.execQuery("select * from Order where id=" + id, result -> {
            result.next();
            return new Order(
                    result.getInt("id"),
                    result.getString("client_name"),
                    result.getString("client_phone"),
                    result.getInt("cost"),
                    result.getTimestamp("date"));
        });
    }

    public Order[] getAll() throws SQLException {
        return executor.execQuery("select * from Order", result -> {
            var list = new LinkedList<Order>();
            while (result.next()) {
                list.add(new Order(result.getInt("id"),
                        result.getString("client_name"),
                        result.getString("client_phone"),
                        result.getInt("cost"),
                        result.getTimestamp("date")));
            }

            return list.toArray(new Order[0]);
        });
    }

    public void update(Order order) throws SQLException {
        executor.execUpdate(String.format("update Order set cost=%d, client_name='%s', client_phone='%s', date='%s' where id=%d",
                order.getCost(), order.getClientName(), order.getClientPhone(), sqlFormat.format(order.getDate()), order.getId()));
    }

    public void delete(int id) throws SQLException {
        executor.execUpdate("delete from Order where id=" + id);
    }
}
