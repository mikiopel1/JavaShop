import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCartToShow {
    private Connection connection;

    public DatabaseCartToShow() {
        try {
            this.connection = DriverManager.getConnection(DbConnector.DB_URL, DbConnector.USER, DbConnector.PASS);
        } catch (SQLException ex) {
            System.out.println("Error connecting to database: " + ex.getMessage());
        }
    }

    public void showCart() {
        try {
            String query = "SELECT id, name, price, quantity FROM cart";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("ID\tNazwa\tCena\tIlosc");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                System.out.println(id + "\t" + name + "\t" + price + "       " + quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}



