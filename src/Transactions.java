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
            System.out.println("\nWpłacono " + ilosc + " zł na konto o ID = { " + id + " }");

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
                pstmtWithdraw.setString(3, "Wypłata");
                pstmtWithdraw.executeUpdate();
            }

            try (PreparedStatement pstmtAccUpdate = conn.prepareStatement(queryAccUpdate)) {
                pstmtAccUpdate.setInt(1, ilosc);
                pstmtAccUpdate.setInt(2, id);
                pstmtAccUpdate.executeUpdate();
            }

            conn.commit();
            System.out.println("\nWypłacono " + ilosc + " zł z konta o ID = { " + id + " }");

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

    public void transfer(int from_account_id, int to_account_id, int ilosc) {
        String queryTransfer = "INSERT INTO transfers(from_account_id, to_account_id, ilosc, transfer_type) VALUES (?, ?, ?, ?)";
        String queryAccUpdate1 = "UPDATE accounts SET saldo = saldo - ? WHERE id = ?";
        String queryAccUpdate2 = "UPDATE accounts SET saldo = saldo + ? WHERE id = ?";

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtTransfer = conn.prepareStatement(queryTransfer)) {
                pstmtTransfer.setInt(1, from_account_id);
                pstmtTransfer.setInt(2, to_account_id);
                pstmtTransfer.setInt(3, ilosc);
                pstmtTransfer.setString(4, "Przelew");
                pstmtTransfer.executeUpdate();
            }

            try (PreparedStatement pstmtAccUpdate = conn.prepareStatement(queryAccUpdate1)) {
                pstmtAccUpdate.setInt(1, ilosc);
                pstmtAccUpdate.setInt(2, from_account_id);
                pstmtAccUpdate.executeUpdate();
            }

            try (PreparedStatement pstmtAccUpdate = conn.prepareStatement(queryAccUpdate2)) {
                pstmtAccUpdate.setInt(1, ilosc);
                pstmtAccUpdate.setInt(2, to_account_id);
                pstmtAccUpdate.executeUpdate();
            }

            conn.commit();
            System.out.println("\nPrzelano " + ilosc + " zł z konta o ID = { " + from_account_id + " } na konto o ID = { " + to_account_id + " }");

        } catch (SQLException e) {
            try {
                conn.rollback();
                System.out.println("Transakcja cofnięta. Błąd przelania pieniędzy: " + e.getMessage());
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
