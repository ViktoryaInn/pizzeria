package pizzeria.dbService.dataSets;

public class User {
    private int id;
    private String login;
    private String password;
    private String role;

    public User(int id, String login, String password, String role){
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public int getId() { return id; }

    public String getLogin() { return login; }

    public String getPassword() { return password; }

    public String getRole() { return role; }

    public void setLogin(String login) { this.login = login; }

    public void setPassword(String password) { this.password = password; }

    public void setRole(String role) { this.role = role; }
}
