import java.util.ArrayList;

public class Reader extends User {
    private ArrayList<String> borrowedBooks = new ArrayList<>();

    public Reader(String name, String username, String password) {
        super(name, username, password, "Reader");
    }

    public void borrowBook(String isbn, ArrayList<Book> books) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn) && b.isAvailable() && b instanceof PrintedBook) {
                b.setAvailable(false);
                borrowedBooks.add(isbn);
                System.out.println("Book borrowed!");
                return;
            }
        }
        System.out.println("Book not available or not borrowable.");
    }

    public void returnBook(String isbn, ArrayList<Book> books) {
        if (!borrowedBooks.contains(isbn)) {
            System.out.println("You didn't borrow this book.");
            return;
        }

        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                b.setAvailable(true);
                borrowedBooks.remove(isbn);
                System.out.println("Book returned.");
                return;
            }
        }
    }

    public void viewBooks(ArrayList<Book> books) {
        for (Book b : books) {
            b.display();
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Reader: " + name);
    }
}