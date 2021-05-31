package pizzeria.dbService.dao;

import pizzeria.dbService.dataSets.Usr;
import pizzeria.dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDAO {
    private final Executor executor;

    public UserDAO(Connection connection) {
        executor = new Executor(connection);
    }

    public void insert(Usr user) throws SQLException {
        executor.execUpdate(String.format("insert into USER_TABLE (login, password, role) values ('%s', '%s', '%s')",
                user.getLogin(), user.getPassword(), user.getRole()));
    }

    public Usr get(String login) {
        try{
            return executor.execQuery(String.format("select * from USER_TABLE where login='%s'", login), result -> {
                result.next();
                return new Usr(
                        result.getString("login"),
                        result.getString("password"),
                        result.getString("role"));
            });
        }catch (SQLException e){
            return null;
        }
    }

    public boolean checkUserExists(String login) throws SQLException {
        return executor.execQuery(String.format("select exists (select * from USER_TABLE where login='%s')", login), result -> {
            result.next();
            return result.getBoolean(1);
        });
    }
}
