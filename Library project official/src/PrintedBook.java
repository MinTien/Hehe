public class PrintedBook extends Book {
    private int pages;

    public PrintedBook(String title, String author, String genre, String isbn, boolean isAvailable, int pages) {
        super(title, author, genre, isbn, isAvailable);
        this.pages = pages;
    }

    @Override
    public void display() {
        System.out.println("[Printed] " + title + " - " + author + " - " + genre + " - " + isbn + 
                           " - Available: " + isAvailable + " - Pages: " + pages);
    }

    public int getPages() { return pages; }
}