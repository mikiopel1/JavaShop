import java.sql.*;
import java.util.Scanner;

public class DatabaseProductRemoval {
    Connection conn;
    Statement stmt;

    public void usunProduct() {
        try {
            conn = DriverManager.getConnection(DbConnector.DB_URL, DbConnector.USER, DbConnector.PASS);
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Podaj ID produktu do usuniecia: ");
        int id = sc.nextInt();
        String name = "";
        try {
            String sql = "SELECT Name_product FROM product WHERE ID_product = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                name = rs.getString("Name_product");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        String sql = "DELETE FROM product WHERE ID_product = " + id;
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        System.out.println("Produkt o ID " + id + " (" + name + ") zostal usuniety z bazy danych.");

        try {
            conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}