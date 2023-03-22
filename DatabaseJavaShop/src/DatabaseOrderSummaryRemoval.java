import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseOrderSummaryRemoval {
    private Connection connection;

    public DatabaseOrderSummaryRemoval() {
        try {
            this.connection = DriverManager.getConnection(DbConnector.DB_URL, DbConnector.USER, DbConnector.PASS);
        } catch (SQLException ex) {
            System.out.println("Error connecting to database: " + ex.getMessage());
            return;
        }
    }

    public void clearOrderSummary() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Czy na pewno chcesz wyczyscic swoja historie zakupow? (1 - tak, 2 - nie): ");
        int choice = scanner.nextInt();
        if(choice == 1) {
            try {
                String query = "DELETE FROM orders";
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                System.out.println("Historia zakupow zostala wyczyszczona.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Operacja anulowana.");
        }
    }
}