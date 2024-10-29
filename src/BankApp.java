import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        accountsDAO acc = new accountsDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== Menu ====");
            System.out.println("1. Dodaj konto bankowe");
            System.out.println("2. Usuń konto bankowe");
            System.out.println("3. Pokaż wszystkie konta bankowe");
            System.out.println("4. Wyjdź");
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
                    int accID = sc.nextInt();
                    acc.deleteAcc(accID);
                    break;
                case 3:
                    System.out.println("\n==== Lista kont bankowych ====");
                    acc.showAllAcc();
                    break;
                case 4:
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
