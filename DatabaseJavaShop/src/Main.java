import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseUserManager userManager = new DatabaseUserManager();
        DatabaseCartManager cartManager = new DatabaseCartManager();
        DatabaseProductsToShow productsToShow = new DatabaseProductsToShow();
        DatabaseOrderSummary orderSummary = new DatabaseOrderSummary();
        DatabasePurchaseHistory purchaseHistory = new DatabasePurchaseHistory();
        DatabaseOrderSummaryRemoval orderSummaryRemoval = new DatabaseOrderSummaryRemoval();


        while (true) {
            System.out.println("Wybierz opcje:");
            System.out.println("1. Zarzadzanie uzytkownikami");
            System.out.println("2. Zarzadzanie koszykiem");
            System.out.println("3. Zarzadzanie produktami");
            System.out.println("4. Podsumowanie zamowienia");
            System.out.println("5. Historia zakupow");
            System.out.println("6. Usuniecie historii zakupow");
            System.out.println("7. Koniec programu");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    manageUsers(scanner, userManager);
                    break;
                case 2:
                    manageCart(scanner, cartManager, productsToShow);
                    break;
                case 3:
                    manageProducts(scanner, productsToShow, cartManager);
                    break;
                case 4:
                    orderSummary.showOrderSummary();
                    break;
                case 5:
                    purchaseHistory.showPurchaseHistory();
                    break;
                case 6:
                    orderSummaryRemoval.clearOrderSummary();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Niepoprawny wybor, wybierz ponownie.");
                    break;
            }
        }
    }

    private static void manageUsers(Scanner scanner, DatabaseUserManager userManager) {
        while (true) {
            System.out.println("1. Dodaj uzytkownika");
            System.out.println("2. Usun uzytkownika");
            System.out.println("3. Wyswietl liste uzytkownikow");
            System.out.println("4. Powrot do menu glownego");
            System.out.print("Wybierz opcje: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    userManager.addUser();
                    break;
                case 2:
                    userManager.removeUser();
                    break;
                case 3:
                    userManager.showUsers();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Niepoprawny wybor, wybierz ponownie.");
                    break;
            }
        }
    }

    private static void manageProducts(Scanner scanner, DatabaseProductsToShow productsToShow, DatabaseCartManager cartManager) {
        while (true) {
            DatabaseProductRemoval productRemoval = new DatabaseProductRemoval();
            DatabaseProductInsertion productInsertion = new DatabaseProductInsertion();
            System.out.println("1. Dodaj produkt");
            System.out.println("2. Usun produkt");
            System.out.println("3. Wyswietl liste produktow");
            System.out.println("4. Powrot do menu glownego");
            System.out.print("Wybierz opcje: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    productInsertion.insertRecord();
                    break;
                case 2:
                    productRemoval.usunProduct();
                    break;
                case 3:
                    productsToShow.showProducts();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Niepoprawny wybor, wybierz ponownie.");
                    break;
            }
        }
    }

    private static void manageCart(Scanner scanner, DatabaseCartManager cartManager, DatabaseProductsToShow productsToShow) {
        while (true) {
            DatabaseCartToShow cartToShow = new DatabaseCartToShow();
            System.out.println("1. Dodaj produkt do koszyka");
            System.out.println("2. Usun produkt z koszyka");
            System.out.println("3. Pokaz koszyk");
            System.out.println("4. Potwierdz zamowienie");
            System.out.println("5. Powrot do menu glownego");
            System.out.print("Wybierz opcje: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    cartManager.addProduct();
                    break;
                case 2:
                    cartManager.removeProduct();
                    break;
                case 3:
                    cartToShow.showCart();
                    break;
                case 4:
                    DatabaseOrderSummary orderSummary = new DatabaseOrderSummary();
                    orderSummary.showOrderSummary();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Niepoprawny wybor, wybierz ponownie.");
                    break;
            }
        }
    }

    private static void purchaseHistory(Scanner scanner, DatabasePurchaseHistory purchaseHistory) {
        while (true) {
            System.out.println("1. Pokaz historie zakupow");
            System.out.println("2. Wyczysc historie zakupow");
            System.out.println("3. Powrot do menu glownego");
            System.out.print("Wybierz opcje: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    purchaseHistory.showPurchaseHistory();
                    break;
                case 2:
                    DatabaseOrderSummaryRemoval orderSummaryRemoval = new
                            DatabaseOrderSummaryRemoval();
                    orderSummaryRemoval.clearOrderSummary();
                    break;
                case 4:
                    DatabaseOrderSummary orderSummary = new DatabaseOrderSummary();
                    orderSummary.showOrderSummary();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Niepoprawny wybor, wybierz ponownie.");
                    break;
            }
        }
    }
}

