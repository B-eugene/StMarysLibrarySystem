import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:library.db";

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            System.out.println("Connected to SQLite database");
            return conn;
        } catch (Exception e) {
            System.out.println("Connection failed");
            e.printStackTrace();
            return null;
        }
    }



    //Creating the table
    public static void createTables() {
    try {
        Connection conn = connect();
        Statement stmt = conn.createStatement();

        String booksTable = "CREATE TABLE IF NOT EXISTS books (" +
                "book_id INTEGER PRIMARY KEY," +
                "title TEXT NOT NULL," +
                "author TEXT NOT NULL," +
                "category TEXT NOT NULL," +
                "availability_status TEXT NOT NULL" +
                ");";

        String membersTable = "CREATE TABLE IF NOT EXISTS members (" +
                "member_id INTEGER PRIMARY KEY," +
                "member_name TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "membership_type TEXT NOT NULL" +
                ");";

        String borrowTable = "CREATE TABLE IF NOT EXISTS borrow_records (" +
                "record_id INTEGER PRIMARY KEY," +
                "book_id INTEGER NOT NULL," +
                "member_id INTEGER NOT NULL," +
                "borrow_date TEXT NOT NULL," +
                "due_date TEXT NOT NULL," +
                "return_status TEXT NOT NULL" +
                ");";

        stmt.execute(booksTable);
        stmt.execute(membersTable);
        stmt.execute(borrowTable);

        System.out.println("Tables created successfully");

    } catch (Exception e) {
        System.out.println("Error creating tables");
        e.printStackTrace();
    }
    }

    
    
    //Adding books
    public static void addBook(int id, String title, String author, String category, String status) {
    try {
        Connection conn = connect();

        String sql = "INSERT INTO books (book_id, title, author, category, availability_status) VALUES (?, ?, ?, ?, ?)";
        
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);
        pstmt.setString(2, title);
        pstmt.setString(3, author);
        pstmt.setString(4, category);
        pstmt.setString(5, status);

        pstmt.executeUpdate();

        System.out.println("Book added successfully");

    } catch (Exception e) {
        System.out.println("Error adding book");
        e.printStackTrace();
    }
    }



    //Viewing books
    public static void viewBooks() {
        try {
            Connection conn = connect();

            String sql = "SELECT * FROM books";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n--- Book List ---");

            while (rs.next()) {
                System.out.println(
                    "ID: " + rs.getInt("book_id") +
                    ", Title: " + rs.getString("title") +
                    ", Author: " + rs.getString("author") +
                    ", Category: " + rs.getString("category") +
                    ", Status: " + rs.getString("availability_status")
            );
        }
       

        } catch (Exception e) {
            System.out.println("Error retrieving books");
            e.printStackTrace();
        }
    }



    //deleting books
    public static void deleteBook(int id) {
    try {
        Connection conn = connect();

        String sql = "DELETE FROM books WHERE book_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Book deleted successfully");
        } else {
            System.out.println("Book not found");
        }

    } catch (Exception e) {
        System.out.println("Error deleting book");
        e.printStackTrace();
    }
    }



    //updating books
    public static void updateBook(int id, String title, String author, String category, String status) {
    try {
        Connection conn = connect();

        String sql = "UPDATE books SET title = ?, author = ?, category = ?, availability_status = ? WHERE book_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, title);
        pstmt.setString(2, author);
        pstmt.setString(3, category);
        pstmt.setString(4, status);
        pstmt.setInt(5, id);

        int rowsAffected = pstmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Book updated successfully");
        } else {
            System.out.println("Book not found");
        }

    } catch (Exception e) {
        System.out.println("Error updating book");
        e.printStackTrace();
    }
    }



    //Adding members
    public static void addMember(int id, String name, String email, String type) {
    try {
        Connection conn = connect();

        String sql = "INSERT INTO members (member_id, member_name, email, membership_type) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);
        pstmt.setString(2, name);
        pstmt.setString(3, email);
        pstmt.setString(4, type);

        pstmt.executeUpdate();

        System.out.println("Member added successfully");

    } catch (Exception e) {
        System.out.println("Error adding member");
        e.printStackTrace();
    }
    }



    //Viewing members
    public static void viewMembers() {
    try {
        Connection conn = connect();

        String sql = "SELECT * FROM members";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\n--- Member List ---");

        while (rs.next()) {
            System.out.println(
                "ID: " + rs.getInt("member_id") +
                ", Name: " + rs.getString("member_name") +
                ", Email: " + rs.getString("email") +
                ", Type: " + rs.getString("membership_type")
            );
        }

    } catch (Exception e) {
        System.out.println("Error retrieving members");
        e.printStackTrace();
    }
    }



    //Stores book borrowing information
    public static void borrowBook(int recordId, int bookId, int memberId, String borrowDate, String dueDate, String status) {
    try {
        Connection conn = connect();

        String sql = "INSERT INTO borrow_records (record_id, book_id, member_id, borrow_date, due_date, return_status) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, recordId);
        pstmt.setInt(2, bookId);
        pstmt.setInt(3, memberId);
        pstmt.setString(4, borrowDate);
        pstmt.setString(5, dueDate);
        pstmt.setString(6, status);

        pstmt.executeUpdate();

        System.out.println("Book borrowed successfully");

    } catch (Exception e) {
        System.out.println("Error borrowing book");
        e.printStackTrace();
    }
    }



    //View book borrowing information
    public static void viewBorrowRecords() {
    try {
        Connection conn = connect();

        String sql = "SELECT * FROM borrow_records";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\n--- Borrow Records ---");

        while (rs.next()) {
            System.out.println(
                "Record ID: " + rs.getInt("record_id") +
                ", Book ID: " + rs.getInt("book_id") +
                ", Member ID: " + rs.getInt("member_id") +
                ", Borrow Date: " + rs.getString("borrow_date") +
                ", Due Date: " + rs.getString("due_date") +
                ", Status: " + rs.getString("return_status")
            );
        }

    } catch (Exception e) {
        System.out.println("Error retrieving borrow records");
        e.printStackTrace();
    }
    

}
}





