public abstract class Book {
    protected String title, author, genre, isbn;
    protected boolean isAvailable;

    public Book(String title, String author, String genre, String isbn, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    public abstract void display();

    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { this.isAvailable = available; }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
}