import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseOrderSummary {
    private Connection connection;
    private double totalPrice;

    public DatabaseOrderSummary() {
        try {
            this.connection = DriverManager.getConnection(DbConnector.DB_URL, DbConnector.USER, DbConnector.PASS);
        } catch (SQLException ex) {
            System.out.println("Error connecting to database: " + ex.getMessage());
        }
    }

    public void showOrderSummary() {
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
                System.out.println(id + "\t" + name + "\t" + price + "\t" + quantity);
                totalPrice += price * quantity;
            }
            System.out.println("Podsumowanie zamowienia: ");
            System.out.println("Laczna cena zamowienia: " + totalPrice);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Czy chcesz potwierdzic zamowienie? (1 - tak, 2 - nie): ");
            int choice = scanner.nextInt();
            if(choice == 1) {
                confirmOrder();
            } else {
                System.out.println("Zamowienie nie zostalo potwierdzone.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void confirmOrder() {
        try {
            String query = "INSERT INTO orders (id, name, price, quantity) SELECT id, name, price, quantity FROM cart";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            query = "DELETE FROM cart";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Zamowienie zostalo potwierdzone i zapisane do bazy danych.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
