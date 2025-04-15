public abstract class User {
    protected String name;
    protected String username;
    protected String password;
    protected String role;

    public User(String name, String username, String password, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getName() { return name; }

    public abstract void displayInfo();
}