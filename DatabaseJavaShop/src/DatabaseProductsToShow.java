import java.sql.*;

public class DatabaseProductsToShow {
    private Connection connection;

    public DatabaseProductsToShow() {
        try {
            this.connection = DriverManager.getConnection(DbConnector.DB_URL, DbConnector.USER, DbConnector.PASS);
        } catch (SQLException ex) {
            System.out.println("Error connecting to database: " + ex.getMessage());
            return;
        }
    }

    public void showProducts() {
        try {
            String query = "SELECT Name_product, ID_product, Price_product FROM product";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("Name_product");
                int id = resultSet.getInt("ID_product");
                double price = resultSet.getDouble("Price_product");
                System.out.println("Name_product: " + name + " | ID_product: " + id + " | Price_product: " + price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
