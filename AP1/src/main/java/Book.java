class Book {
    private String bookId;
    private String title;
    private String author;
    private long borrowedTime;
    private boolean available = true;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public long getBorrowedTime() {
        return borrowedTime;
    }

    public void setBorrowedTime(long borrowedTime) {
        this.borrowedTime = borrowedTime;
    }
}