import java.util.ArrayList;

public class Admin extends User {

    public Admin(String name, String username, String password) {
        super(name, username, password, "Admin");
    }

    public void addUser(User u, ArrayList<User> users) {
        for(User user : users){
            if(u.getUsername().equals(user.getUsername())){
                System.out.println("User existed.");
                return;
            }
        }
        users.add(u);
        System.out.println("User added.");
    }

    public void removeUser(String uname, ArrayList<User> users) {
        for(User u : users){
            if(u.getUsername().equals(uname)){
                users.remove(u);
                System.out.println("User removed.");
                return;
            }
        }System.out.println("User does not exist.");
        
    }

    public void listUsers(ArrayList<User> users) {
        System.out.println("\n--- Admins ---");
        for (User u : users) {
            if (u instanceof Admin) {
                u.displayInfo();
            }
        }
    
        System.out.println("\n--- Librarians ---");
        for (User u : users) {
            if (u instanceof Librarian) {
                u.displayInfo();
            }
        }
    
        System.out.println("\n--- Readers ---");
        for (User u : users) {
            if (u instanceof Reader) {
                u.displayInfo();
            }
        }
    }
    
    @Override
    public void displayInfo() {
        System.out.println("Admin: " + name);
    }
}