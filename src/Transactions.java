import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Transactions {
    private static final Connection conn = DatabaseConnection.getConnection();

    public void deposit(int id, int ilosc) {
        String queryDeposit = "INSERT INTO transactions (account_id, ilosc, transakcja_typ) VALUES (?, ?, ?)";
        String queryAccUpdate = "UPDATE accounts SET saldo = saldo + ? WHERE id = ?";

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtDeposit = conn.prepareStatement(queryDeposit)) {
                pstmtDeposit.setInt(1, id);
                pstmtDeposit.setInt(2, ilosc);
                pstmtDeposit.setString(3, "Depozyt");
                pstmtDeposit.executeUpdate();
            }

            try (PreparedStatement pstmtAccUpdate = conn.prepareStatement(queryAccUpdate)) {
                pstmtAccUpdate.setInt(1, ilosc);
                pstmtAccUpdate.setInt(2, id);
                pstmtAccUpdate.executeUpdate();
            }

            conn.commit();
            System.out.println("\nWpłacono " + ilosc + " zł na konto o id={ " + id + " }");

        } catch (SQLException e) {
            try {
                conn.rollback();
                System.out.println("Transakcja cofnięta. Błąd wpłaty pieniędzy: " + e.getMessage());
            } catch (SQLException rollbackEx) {
                System.out.println("Błąd przy wycofywaniu transakcji: " + rollbackEx.getMessage());
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Błąd przy przywracaniu automatycznego zatwierdzania: " + e.getMessage());
            }
        }
    }

    public void withdraw(int id, int ilosc) {
        String queryWithdraw = "INSERT INTO transactions (account_id, ilosc, transakcja_typ) VALUES (?, ?, ?)";
        String queryAccUpdate = "UPDATE accounts SET saldo = saldo - ? WHERE id = ?";

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtWithdraw = conn.prepareStatement(queryWithdraw)) {
                pstmtWithdraw.setInt(1, id);
                pstmtWithdraw.setInt(2, -ilosc);
                pstmtWithdraw.setString(3, "Wyplata");
                pstmtWithdraw.executeUpdate();
            }

            try (PreparedStatement pstmtAccUpdate = conn.prepareStatement(queryAccUpdate)) {
                pstmtAccUpdate.setInt(1, ilosc);
                pstmtAccUpdate.setInt(2, id);
                pstmtAccUpdate.executeUpdate();
            }

            conn.commit();
            System.out.println("\nWypłacono " + ilosc + " zł z konta o id={ " + id + " }");

        } catch (SQLException e) {
            try {
                conn.rollback();
                System.out.println("Transakcja cofnięta. Błąd wypłaty pieniędzy: " + e.getMessage());
            } catch (SQLException rollbackEx) {
                System.out.println("Błąd przy wycofywaniu transakcji: " + rollbackEx.getMessage());
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Błąd przy przywracaniu automatycznego zatwierdzania: " + e.getMessage());
            }
        }
    }
}