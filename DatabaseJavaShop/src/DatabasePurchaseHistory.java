import java.sql.*;

public class DatabasePurchaseHistory {
    private Connection connection;

    public DatabasePurchaseHistory() {
        try {
            this.connection = DriverManager.getConnection(DbConnector.DB_URL, DbConnector.USER, DbConnector.PASS);
        } catch (SQLException ex) {
            System.out.println("Error connecting to database: " + ex.getMessage());
            return;
        }
    }

    public void showPurchaseHistory() {
            try {
                String query = "SELECT * FROM orders";
                Statement statement = null;
                try {
                    statement = connection.createStatement();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                ResultSet resultSet = null;
                try {
                    resultSet = statement.executeQuery(query);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("ID\tNazwa\tCena\tIlosc");
                while (true) {
                    try {
                        if (!resultSet.next()) break;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    int id = 0;
                    try {
                        id = resultSet.getInt("id");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    String name = null;
                    try {
                        name = resultSet.getString("name");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    double price = 0;
                    try {
                        price = resultSet.getDouble("price");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    int quantity = 0;
                    try {
                        quantity = resultSet.getInt("quantity");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(id + "\t" + name + "\t" + price + "       " + quantity);
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
    }
