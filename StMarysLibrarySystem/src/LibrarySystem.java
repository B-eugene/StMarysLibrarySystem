import java.util.Scanner;

public class LibrarySystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== St Mary's Library System ===");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Delete Book");
            System.out.println("4. Update Book");
            System.out.println("5. Add Member");
            System.out.println("6. View Members");
            System.out.println("7. Borrow Book");
            System.out.println("8. View Borrow Records");
            System.out.println("9. Return Book");
            System.out.println("10. Exit");
            System.out.println("11. Search Books");
            System.out.println("12. Search Members");
            System.out.println("13. Search Borrow Records");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();

                    System.out.print("Enter Category: ");
                    String category = scanner.nextLine();

                    System.out.print("Enter Status: ");
                    String status = scanner.nextLine();

                    DatabaseConnection.addBook(id, title, author, category, status);
                    break;

                case 2:
                    DatabaseConnection.viewBooks();
                    break;

                case 3:
                    System.out.print("Enter Book ID to delete: ");
                    int deleteId = scanner.nextInt();
                    DatabaseConnection.deleteBook(deleteId);
                    break;

                case 4:
                    System.out.print("Enter Book ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter new Title: ");
                    String newTitle = scanner.nextLine();

                    System.out.print("Enter new Author: ");
                    String newAuthor = scanner.nextLine();

                    System.out.print("Enter new Category: ");
                    String newCategory = scanner.nextLine();

                    System.out.print("Enter new Status: ");
                    String newStatus = scanner.nextLine();

                    DatabaseConnection.updateBook(updateId, newTitle, newAuthor, newCategory, newStatus);
                    break;

                case 5:
                    System.out.print("Enter Member ID: ");
                    int mId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter Membership Type: ");
                    String type = scanner.nextLine();

                    DatabaseConnection.addMember(mId, name, email, type);
                    break;

                case 6:
                    DatabaseConnection.viewMembers();
                    break;

                case 7:
                    System.out.print("Enter Record ID: ");
                    int rId = scanner.nextInt();

                    System.out.print("Enter Book ID: ");
                    int bId = scanner.nextInt();

                    System.out.print("Enter Member ID: ");
                    int memId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Borrow Date: ");
                    String borrowDate = scanner.nextLine();

                    System.out.print("Enter Due Date: ");
                    String dueDate = scanner.nextLine();

                    System.out.print("Enter Status: ");
                    String borrowstatus = scanner.nextLine();

                    DatabaseConnection.borrowBook(rId, bId, memId, borrowDate, dueDate, borrowstatus);
                    break;

                case 8:
                    DatabaseConnection.viewBorrowRecords();
                    break;

                case 9:
                    System.out.print("Enter Record ID: ");
                    int recordId = scanner.nextInt();

                    System.out.print("Enter Book ID: ");
                    int returnBookId = scanner.nextInt();

                    DatabaseConnection.returnBook(recordId, returnBookId);
                    break;

                case 11:
                    System.out.print("Enter keyword: ");
                    String keyword = scanner.next();
                    DatabaseConnection.searchBooks(keyword);
                    break;

                case 12:
                    System.out.print("Enter keyword: ");
                    String memberKeyword = scanner.next();
                    DatabaseConnection.searchMembers(memberKeyword);
                    break;
                
                case 13:
                    System.out.print("Enter ID (record/book/member): ");
                    String borrowKeyword = scanner.next();
                    DatabaseConnection.searchBorrowRecords(borrowKeyword);
                    break;


                case 10:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 10);

        scanner.close();
    }
}