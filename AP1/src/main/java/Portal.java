import java.util.List;
import java.util.Scanner;

public class Portal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Librarian librarian = new Librarian();
        Member currentMember = null;

        while (true) {
            System.out.println("Library Portal Initialized…");
            System.out.println("1. Enter as a librarian");
            System.out.println("2. Enter as a member");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {

                LibActions(librarian, scanner);

            } else if (choice == 2) {

                System.out.print("Enter your member ID: ");
                String mId = scanner.next();
                currentMember = librarian.findMember(mId);

                if (currentMember != null) {

                    MemberActions(currentMember, scanner, librarian);

                } else {

                    System.out.println("Member not found.");

                }
            } else if (choice == 3) {

                System.out.println("Exiting the library portal.");
                scanner.close();
                System.exit(0);

            } else {

                System.out.println("Invalid choice. Please enter a valid option (1, 2, or 3).");

            }
        }
    }

    private static void MemberActions(Member member, Scanner scanner, Librarian librarian) {
        while (true) {
            System.out.println("Member Actions:");
            System.out.println("1. List Available Books");
            System.out.println("2. List My Books");
            System.out.println("3. Issue book");
            System.out.println("4. Return book");
            System.out.println("5. Pay Fine");
            System.out.println("6. Back");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                librarian.viewAllBooks();
            } else if (choice == 2) {

                List<String> borrowedBookIds = member.getBorrowedBookIds();
                System.out.println("Your Borrowed Books:");


                for (String bookId : borrowedBookIds) {
                    System.out.println("Book ID: " + bookId);
                }

            } else if (choice == 3) {

                System.out.print("Enter book ID to borrow: ");
                String bookIdToBorrow = scanner.next();


                member.borrowBook(bookIdToBorrow, librarian);

            } else if (choice == 4) {
                // Return a book
                System.out.print("Enter book ID to return: ");
                String bookIdToReturn = scanner.next();

                member.returnBook(bookIdToReturn, librarian);

            } else if (choice == 5) {

                System.out.print("Enter the amount to pay (if any): ");

                member.payFine();

                System.out.println("Fine paid successfully.");

            } else if (choice == 6) {

                return;

            } else {

                System.out.println("Invalid choice. Please enter a valid option.");

            }
        }
    }

    private static void LibActions(Librarian librarian, Scanner scanner) {
        while (true) {
            System.out.println("Librarian Actions:");
            System.out.println("1. Register a member");
            System.out.println("2. Remove a member");
            System.out.println("3. Add a book");
            System.out.println("4. Remove a book");
            System.out.println("5. View all members");
            System.out.println("6. View all books");
            System.out.println("7. Back");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {

                System.out.print("Enter member ID: ");
                String mId = scanner.next();

                System.out.print("Enter member name: ");
                String mName = scanner.next();

                System.out.print("Enter member age: ");
                int age = scanner.nextInt();

                System.out.print("Enter member phone number: ");
                String contactnumber = scanner.next();

                librarian.registerMember(mId, mName, age, contactnumber);

            } else if (choice == 2) {

                System.out.print("Enter member ID to remove: ");
                String mIdToRemove = scanner.next();

                librarian.removeMember(mIdToRemove);

            } else if (choice == 3) {

                System.out.print("Enter book title: ");
                String bookTitle = scanner.next();

                System.out.print("Enter book author: ");
                String bookAuthor = scanner.next();

                System.out.print("Enter the number of copies to add: ");
                int copies = scanner.nextInt();

                librarian.addBook(bookTitle, bookAuthor, copies);

            } else if (choice == 4) {

                System.out.print("Enter book ID to remove: ");
                String bookIdToRemove = scanner.next();

                librarian.removeBook(bookIdToRemove);

            } else if (choice == 5) {

                librarian.viewAllMembers();

            } else if (choice == 6) {

                librarian.viewAllBooks();

            } else if (choice == 7) {

                return;

            } else {

                System.out.println("Invalid choice. Please enter a valid option.");

            }
        }
    }
}
