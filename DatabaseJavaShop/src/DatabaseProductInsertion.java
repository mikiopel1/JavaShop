import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DatabaseProductInsertion {
    Connection conn;
    PreparedStatement pstmt;

    public DatabaseProductInsertion() {
        try {
            conn = DriverManager.getConnection(DbConnector.DB_URL, DbConnector.USER, DbConnector.PASS);
        } catch (SQLException ex) {
            System.out.println("Error connecting to database: " + ex.getMessage());
        }
    }

    public void insertRecord() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadz nazwe produktu: ");
        String name = scanner.nextLine();
        System.out.println("Wprowadz ID produktu: ");
        int ID = scanner.nextInt();
        System.out.println("Wprowadz cene produktu: ");
        double Price = scanner.nextDouble();

        try {
            pstmt = conn.prepareStatement("INSERT INTO product (Name_product, ID_product, Price_product) VALUES (?, ?, ?)");
            pstmt.setString(1, name);
            pstmt.setInt(2, ID);
            pstmt.setDouble(3, Price);
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Poprawnie zaladowano rekord od bazy danych!");
            }
        } catch (SQLException ex) {
            System.out.println("Error inserting record: " + ex.getMessage());
        }
    }
}
