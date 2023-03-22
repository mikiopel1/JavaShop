import java.sql.*;
import java.util.Scanner;

public class DatabaseCartManager {
    private Connection connection;
    private Scanner scanner;
    private DatabaseProductsToShow productsToShow;
    private DatabaseCartToShow CartToShow;

    public DatabaseCartManager() {
        scanner = new Scanner(System.in);
        productsToShow = new DatabaseProductsToShow();
        CartToShow = new DatabaseCartToShow();
        try {
            this.connection = DriverManager.getConnection(DbConnector.DB_URL, DbConnector.USER, DbConnector.PASS);
        } catch (SQLException ex) {
            System.out.println("Error connecting to database: " + ex.getMessage());
            return;
        }
    }

    public void addProduct() {
        productsToShow.showProducts();
        System.out.print("Podaj ID produktu, ktory chcesz dodac: ");
        int productId = scanner.nextInt();
        System.out.print("Podaj ilosc produktu, ktora chcesz dodac: ");
        int quantity = scanner.nextInt();

        try {
            String query = "SELECT Name_product, Price_product FROM product WHERE ID_product = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String productName = resultSet.getString("Name_product");
                double productPrice = resultSet.getDouble("Price_product");
                query = "INSERT INTO cart (id, name, price, quantity) VALUES (?, ?, ?, ?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, productId);
                statement.setString(2, productName);
                statement.setDouble(3, productPrice);
                statement.setInt(4, quantity);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeProduct() {
        CartToShow.showCart();
        System.out.print("Podaj ID produktu, ktory chcesz usunac: ");
        int productId = scanner.nextInt();
        System.out.print("Podaj ilosc produktu, ktora chcesz usunac: ");
        int quantity = scanner.nextInt();
        try {
            String query = "SELECT quantity FROM cart WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int currentQuantity = resultSet.getInt("quantity");
                if (quantity == currentQuantity) {
                    query = "DELETE FROM cart WHERE id = ?";
                    statement = connection.prepareStatement(query);
                    statement.setInt(1, productId);
                    statement.executeUpdate();
                } else {
                    int updatedQuantity = currentQuantity - quantity;
                    query = "UPDATE cart SET quantity = ? WHERE id = ?";
                    statement = connection.prepareStatement(query);
                    statement.setInt(1, updatedQuantity);
                    statement.setInt(2, productId);
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}