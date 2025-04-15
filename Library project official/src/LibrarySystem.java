import java.io.*;
import java.util.*;

public class LibrarySystem {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Book> books = new ArrayList<>();

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static ArrayList<Book> getBooks() {
        return books;
    }

    public static void setUsers(ArrayList<User> users) {
        LibrarySystem.users = users;
    }

    public static void setBooks(ArrayList<Book> books) {
        LibrarySystem.books = books;
    }

    public static void loadUsers(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                switch (parts[0]) {
                    case "A" -> users.add(new Admin(parts[1], parts[2], parts[3]));
                    case "L" -> users.add(new Librarian(parts[1], parts[2], parts[3]));
                    case "R" -> users.add(new Reader(parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to load users.");
        }
    }

    public static void saveUsers(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (User u : users) {
                String prefix = switch (u.getRole()) {
                    case "Admin" -> "A";
                    case "Librarian" -> "L";
                    case "Reader" -> "R";
                    default -> "X";
                };
                bw.write(prefix + "," + u.getName() + "," + u.getUsername() + "," + u.getPassword());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save users.");
        }
    }

    public static void loadBooks(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                if (type.equals("Printed")) {
                    books.add(new PrintedBook(parts[1], parts[2], parts[3], parts[4], Boolean.parseBoolean(parts[5]), Integer.parseInt(parts[6])));
                } else if (type.equals("Ebook")) {
                    books.add(new Ebook(parts[1], parts[2], parts[3], parts[4], false, parts[6]));
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to load books.");
        }
    }

    public static void saveBooks(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Book b : books) {
                if (b instanceof PrintedBook pb) {
                    bw.write("Printed," + pb.getTitle() + "," + pb.getAuthor() + "," + pb.getGenre() + "," +
                        pb.getIsbn() + "," + pb.isAvailable() + "," + pb.getPages());
                } else if (b instanceof Ebook eb) {
                    bw.write("Ebook," + eb.getTitle() + "," + eb.getAuthor() + "," + eb.getGenre() + "," +
                             eb.getIsbn() + "," + false + "," + eb.getFileFormat());
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save books.");
        }
    }

    public static void logTransaction(String user, String isbn, String type, String date, String due) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("transactions.txt", true))) {
            bw.write(user + "," + isbn + "," + type + "," + date + "," + due);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Failed to write transaction.");
        }
    }

}