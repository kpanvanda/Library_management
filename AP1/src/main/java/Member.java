import java.util.ArrayList;
import java.util.List;

class Member {
    private String mId;
    private String mName;
    private List<String> borrowedBookIds;
    private double fines = 0;
    private int age;
    private String contactnumber;
    private boolean available = true;

    public Member(String mId, String mName, int age, String contactnumber) {
        this.mId = mId;
        this.mName = mName;
        this.age = age;
        this.contactnumber = contactnumber;
        borrowedBookIds = new ArrayList<>();
    }

    public int getAge() {
        return age;
    }

    public String getcontactnumber() {
        return contactnumber;
    }

    public String getMId() {
        return mId;
    }

    public String getMName() {
        return mName;
    }

    public List<String> getBorrowedBookIds() {
        return borrowedBookIds;
    }

    public double getFines() {
        return fines;
    }

    public void borrowBook(String bookId, Librarian librarian) {
        long currentTimeSec = System.currentTimeMillis() / 1000;

        Book book = librarian.findbookbyId(bookId);

        if (book == null) {

            System.out.println("Book with ID " + bookId + " not found.");

        } else if (book.isAvailable()) {

            book.setBorrowedTime(currentTimeSec);
            book.setAvailable(false);
            borrowedBookIds.add(bookId);
            System.out.println("Book with ID " + bookId + " borrowed successfully.");

        } else {
            System.out.println("Book with ID " + bookId + " is already borrowed.");
        }
    }

    public void returnBook(String bookId, Librarian librarian) {
        Book bookToReturn = librarian.findbookbyId(bookId);

        if (bookToReturn != null) {
            borrowedBookIds.remove(bookId);
            bookToReturn.setAvailable(true);

            long currentTimeSec = System.currentTimeMillis() / 1000;
            long dueDateSec = bookToReturn.getBorrowedTime() + 10;

            if (currentTimeSec > dueDateSec) {

                long secondsLate = currentTimeSec - dueDateSec;
                double fine = secondsLate * 3.0;
                fines += fine;
                System.out.println("Book ID:" + bookId + " successfully returned." + fine
                        + "Rupees has been charged for a delay of " + secondsLate + "days");

            } else {

                System.out.println("Book ID:" + bookId + " successfully returned.");

            }
        } else {

            System.out.println("Book with ID " + bookId + " not found or not borrowed by you.");

        }
    }

    public void payFine() {
        System.out.println("You had a total fine of Rs " + fines + "It has been paid successfully!");
    }
}
