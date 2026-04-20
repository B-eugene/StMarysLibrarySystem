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
            System.out.println("5. Exit");
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
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 5);

        scanner.close();
    }
}