import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Librarian {
    private Map<String, Member> members;
    private List<Book> books;
    private Map<String, Book> librarybooks = new HashMap<>();
    private Map<String, List<String>> memberborrowedbooks;

    public Librarian() {
        members = new HashMap<>();
        books = new ArrayList<>();
        librarybooks = new HashMap<>();
        memberborrowedbooks = new HashMap<>();
    }

    public boolean borrowBook(String mId, String bookId) {

        if (!members.containsKey(mId)) {
            System.out.println("Member with ID " + mId + " not found.");
            return false;
        }

        if (!librarybooks.containsKey(bookId)) {
            System.out.println("Book with ID " + bookId + " not found.");
            return false;
        }

        Book book = librarybooks.get(bookId);
        if (!book.isAvailable()) {
            System.out.println("Book with ID " + bookId + " is already borrowed.");
            return false;
        }

        book.setAvailable(false);
        long currentTimeSec = System.currentTimeMillis() / 1000;
        book.setBorrowedTime(currentTimeSec);

        List<String> borrowedBooks = memberborrowedbooks.getOrDefault(mId, new ArrayList<>());
        borrowedBooks.add(bookId);

        memberborrowedbooks.put(mId, borrowedBooks);

        System.out.println("Book with ID " + bookId + " borrowed successfully.");
        return true;
    }

    public Member findMember(String mId) {
        return members.get(mId);
    }

    public Book findBook(String title, String author) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.getAuthor().equalsIgnoreCase(author)) {
                return book;
            }
        }
        return null;
    }

    public void registerMember(String mId, String memberName, int age, String contactnumber) {

        if (!members.containsKey(mId)) {

            members.put(mId, new Member(mId, memberName, age, contactnumber));
            System.out.println("Member registered successfully.");

        } else {

            System.out.println("Member with ID " + mId + " already exists.");
        }

    }

    public void removeMember(String mId) {
        if (members.containsKey(mId)) {

            members.remove(mId);
            System.out.println("Member removed successfully.");

        } else {

            System.out.println("Member not found.");
        }
    }

    private Map<String, Book> booksbyid = new HashMap<>();
    private int bookIdcount = 1;

    public void addBook(String title, String author, int copies) {
        for (int i = 0; i < copies; i++) {

            String bookId = String.valueOf(bookIdcount);
            bookIdcount++;

            Book newBook = new Book(bookId, title, author);

            librarybooks.put(bookId, newBook);
            booksbyid.put(bookId, newBook);
        }

        System.out.println(copies + " books added successfully.");
    }

    public void viewAllMembers() {
        System.out.println("List of all members:");

        for (String mId : members.keySet()) {

            Member member = members.get(mId);

            System.out.println("Member ID: " + mId + ", Member Name: " + member.getMName()
                    + ", Age: " + member.getAge() + ", Phone Number: " + member.getcontactnumber());

        }
    }

    public void viewAllBooks() {
        System.out.println("List of all books:");

        for (String bookId : librarybooks.keySet()) {

            Book book = librarybooks.get(bookId);
            System.out.println("Book ID: " + bookId + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor());

        }
    }

    public Book findbookbyId(String bookId) {
        return booksbyid.get(bookId);
    }

    public void removeBook(String bookId) {

        if (librarybooks.containsKey(bookId)) {

            librarybooks.remove(bookId);
            System.out.println("Book removed successfully.");

        } else {
            System.out.println("Book not found.");
        }
    }
}
