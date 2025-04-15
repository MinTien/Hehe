import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        LibrarySystem.loadUsers("users.txt");
        LibrarySystem.loadBooks("book.txt");

        System.out.println("===== LIBRARY MANAGEMENT SYSTEM =====");
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        User currentUser = null;
        for (User u : LibrarySystem.getUsers()) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                currentUser = u;
                break;
            }
        }

        if (currentUser == null) {
            System.out.println("Invalid credentials.");
            return;
        }

        System.out.println("Welcome, " + currentUser.getName() + " (" + currentUser.getRole() + ")");
        currentUser.displayInfo();
2

        switch (currentUser.getRole()) {
            case "Admin" -> adminMenu((Admin) currentUser);
            case "Librarian" -> librarianMenu((Librarian) currentUser);
            case "Reader" -> readerMenu((Reader) currentUser);
        }
    }

    static void adminMenu(Admin admin) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add user");
            System.out.println("2. Remove user");
            System.out.println("3. List users");
            System.out.println("0. Logout");
            System.out.print("Your choice: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter username: ");
                    String uname = sc.nextLine();
                    System.out.print("Enter password: ");
                    String pass = sc.nextLine();
                    System.out.print("Role (A/L/R): ");
                    String role = sc.nextLine().toUpperCase();

                    User newUser = switch (role) {
                        case "A" -> new Admin(name, uname, pass);
                        case "L" -> new Librarian(name, uname, pass);
                        case "R" -> new Reader(name, uname, pass);
                        default -> null;
                    };

                    if (newUser != null) {
                        admin.addUser(newUser, LibrarySystem.getUsers());
                        LibrarySystem.saveUsers("users.txt"); 
                    } else {
                        System.out.println("Invalid role.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter username to remove: ");
                    String uname = sc.nextLine();
                    admin.removeUser(uname, LibrarySystem.getUsers());
                    LibrarySystem.saveUsers("users.txt");
                }
                case 3 -> admin.listUsers(LibrarySystem.getUsers());
                case 0 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void librarianMenu(Librarian librarian) {
        while (true) {
            System.out.println("\n--- Librarian Menu ---");
            System.out.println("1. Add book");
            System.out.println("2. Remove book");
            System.out.println("3. List books");
            System.out.println("0. Logout");
            System.out.print("Your choice: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Type (Printed/Ebook): ");
                    String type = sc.nextLine();
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    System.out.print("Genre: ");
                    String genre = sc.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();

                    if (type.equalsIgnoreCase("Printed")) {
                        System.out.print("Pages: ");
                        int pages = sc.nextInt(); sc.nextLine();
                        librarian.addBook(new PrintedBook(title, author, genre, isbn, true, pages), LibrarySystem.getBooks());
                    } else if (type.equalsIgnoreCase("Ebook")) {
                        System.out.print("File Format: ");
                        String format = sc.nextLine();
                        librarian.addBook(new Ebook(title, author, genre, isbn, false, format), LibrarySystem.getBooks());
                    } else {
                        System.out.println("Invalid type.");
                    }

                    LibrarySystem.saveBooks("book.txt");
                }
                case 2 -> {
                    System.out.print("Enter ISBN to remove: ");
                    String isbn = sc.nextLine();
                    librarian.removeBook(isbn, LibrarySystem.getBooks());
                    LibrarySystem.saveBooks("book.txt");
                }
                case 3 -> librarian.listBooks(LibrarySystem.getBooks());
                case 0 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void readerMenu(Reader reader) {
        while (true) {
            System.out.println("\n--- Reader Menu ---");
            System.out.println("1. Borrow book");
            System.out.println("2. Return book");
            System.out.println("3. View books");
            System.out.println("0. Logout");
            System.out.print("Your choice: ");
            int choice = sc.nextInt(); sc.nextLine();
    
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter ISBN to borrow: ");
                    String isbn = sc.nextLine();
                    reader.borrowBook(isbn, LibrarySystem.getBooks());
                    LibrarySystem.saveBooks("book.txt");
    
                    // Ghi log mượn
                    String today = java.time.LocalDate.now().toString();
                    String due = java.time.LocalDate.now().plusDays(14).toString();
                    LibrarySystem.logTransaction(reader.getUsername(), isbn, "borrow", today, due);
                }
                case 2 -> {
                    System.out.print("Enter ISBN to return: ");
                    String isbn = sc.nextLine();
                    reader.returnBook(isbn, LibrarySystem.getBooks());
                    LibrarySystem.saveBooks("book.txt");
    
                    String today = java.time.LocalDate.now().toString();
                    LibrarySystem.logTransaction(reader.getUsername(), isbn, "return", today, "onTime");
                }
                case 3 -> reader.viewBooks(LibrarySystem.getBooks());
                case 0 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
    
}
