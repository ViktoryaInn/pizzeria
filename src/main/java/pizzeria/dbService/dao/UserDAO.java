package pizzeria.dbService.dao;

import pizzeria.dbService.dataSets.User;
import pizzeria.dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDAO {
    private final Executor executor;

    public UserDAO(Connection connection) {
        executor = new Executor(connection);
    }

    public void insert(User user) throws SQLException {
        executor.execUpdate(String.format("insert into User (login, password, role) values ('%s', '%s', '%s')",
                user.getLogin(), user.getPassword(), user.getRole()));
    }

    public User get(String login) {
        try{
            return executor.execQuery(String.format("select * from User where login='%s'", login), result -> {
                result.next();
                return new User(
                        result.getInt("id"),
                        result.getString("login"),
                        result.getString("password"),
                        result.getString("role"));
            });
        }catch (SQLException e){
            return null;
        }
    }

    public boolean checkUserExists(String login) throws SQLException {
        return executor.execQuery(String.format("select exists (select * from User where login='%s')", login), result -> {
            result.next();
            return result.getBoolean(1);
        });
    }
}
