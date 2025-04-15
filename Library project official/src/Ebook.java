public class Ebook extends Book {
    private String fileFormat;

    public Ebook(String title, String author, String genre, String isbn, boolean isAvailable, String fileFormat) {
        super(title, author, genre, isbn, false); 
        this.fileFormat = fileFormat;
    }

    @Override
    public void display() {
        System.out.println("[Ebook] " + title + " - " + author + " - " + genre + " - " + isbn + 
                           " - Format: " + fileFormat);
    }

    public String getFileFormat() { return fileFormat; }
}