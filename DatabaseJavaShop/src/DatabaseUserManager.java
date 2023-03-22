import java.sql.*;
import java.util.Scanner;
public class DatabaseUserManager



{


    Connection conn;
    PreparedStatement pstmt;
    Statement stmt;

    public DatabaseUserManager() {
        try {
            conn = DriverManager.getConnection(DbConnector.DB_URL, DbConnector.USER, DbConnector.PASS);
        } catch (SQLException ex) {
            System.out.println("Error connecting to database: " + ex.getMessage());
        }
    }

    public void addUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadz imie i nazwisko: ");
        String name = scanner.nextLine();
        System.out.println("Wprowadz ID: ");
        int ID = scanner.nextInt();
        System.out.println("Wprowadz numer telefonu: ");
        int PhoneNumber = scanner.nextInt();

        try {
            pstmt = conn.prepareStatement("INSERT INTO client (Name_client, ID_client, PhoneNumber_client) VALUES (?, ?, ?)");
            pstmt.setString(1, name);
            pstmt.setInt(2, ID);
            pstmt.setInt(3, PhoneNumber);
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Poprawnie zaladowano rekord od bazy danych!");
            }
        } catch (SQLException ex) {
            System.out.println("Error inserting record: " + ex.getMessage());
        }
    }

    public void removeUser() {
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Podaj ID klienta do usuniecia: ");
        int id = sc.nextInt();
        String name = "";
        try {
            String sql = "SELECT Name_client FROM client WHERE ID_client = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                name = rs.getString("Name_client");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        String sql = "DELETE FROM client WHERE ID_client = " + id;
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        System.out.println("Klient o ID " + id + " (" + name + ") zostal usuniety z bazy danych.");
    }

    public void showUsers() {
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        try {
            String sql = "SELECT * FROM client";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int ID = rs.getInt("ID_client");
                String name = rs.getString("Name_client");
                int phoneNumber = rs.getInt("PhoneNumber_client");
                System.out.println("ID: " + ID + ", Imie i nazwisko: " + name + ", Numer telefonu: " + phoneNumber);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    }