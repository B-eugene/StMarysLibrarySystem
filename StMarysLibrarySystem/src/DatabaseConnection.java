import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;

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
}





