import java.util.ArrayList;

public class Librarian extends User {

    public Librarian(String name, String username, String password) {
        super(name, username, password, "Librarian");
    }

    public void addBook(Book b, ArrayList<Book> books) {
        for(Book book : books){
            if(b.getIsbn().equals(book.getIsbn())){
                System.out.println("The ISBN existed. The book cannot be added.");
                return;
            }
        }
        books.add(b);
        System.out.println("Book added.");
    }

    public void removeBook(String isbn, ArrayList<Book> books) {
        for(Book book : books){
            if(isbn.equals(book.getIsbn())){
                books.remove(book);
                System.out.println("Book removed.");
                return;
            }
        }
        System.out.println("Book does not exist.");
    }

    public void listBooks(ArrayList<Book> books) {
        for (Book b : books) {
            b.display();
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Librarian: " + name);
    }
}