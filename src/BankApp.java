import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        int accID;
        int depositValue;

        accountsDAO acc = new accountsDAO();
        Transactions trans = new Transactions();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== ADMIN ====");
            System.out.println("1. Dodaj konto bankowe");
            System.out.println("2. Usuń konto bankowe");
            System.out.println("3. Pokaż wszystkie konta bankowe");
            System.out.println("\n==== MENU ====");
            System.out.println("4. Przelej pieniadze");
            System.out.println("5. Wplac pieniadze");
            System.out.println("6. Wyplac pieniadze");
            System.out.println("7. Wyjdź");
            System.out.print("Wybierz numer -> ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n==== Dodawanie konta bankowego ====");
                    System.out.print("Podaj imię: ");
                    String imie = sc.nextLine();
                    System.out.print("Podaj nazwisko: ");
                    String nazwisko = sc.nextLine();
                    System.out.print("Podaj saldo początkowe: ");
                    int saldo = sc.nextInt();
                    acc.addAcc(imie, nazwisko, saldo);
                    break;
                case 2:
                    System.out.println("\n==== Usuwanie konta bankowego ====");
                    System.out.print("Podaj id konta do usuniecia: ");
                    accID = sc.nextInt();
                    acc.deleteAcc(accID);
                    break;
                case 3:
                    System.out.println("\n==== Lista kont bankowych ====");
                    acc.showAllAcc();
                    break;
                case 4:
                    System.out.println("4");
                    break;
                case 5:
                    System.out.println("\n==== Wpłacanie gotowki ====");
                    System.out.print("Podaj id swojego konta: ");
                    accID = sc.nextInt();
                    System.out.print("Podaj ilosc jaka chcesz wpłacic: ");
                    depositValue = sc.nextInt();
                    trans.deposit(accID, depositValue);
                    break;
                case 6:
                    System.out.println("6");
                    break;
                case 7:
                    System.out.println("Wyjście z programu.");
                    sc.close();
                    return;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
                    break;
            }
        }
    }
}
