import java.sql.*;

public class accountsDAO {
    private static final Connection conn = DatabaseConnection.getConnection();

    public void addAcc(String imie,String nazwisko, int saldo){
        String queryAdd = "INSERT INTO `accounts` (`imie`, `nazwisko`, `saldo`) VALUES (?, ?, ?)";
        try (PreparedStatement pstmtAddAcc = conn.prepareStatement(queryAdd)) {
            pstmtAddAcc.setString(1, imie);
            pstmtAddAcc.setString(2, nazwisko);
            pstmtAddAcc.setInt(3, saldo);
            pstmtAddAcc.executeUpdate();
            System.out.println("Użytkownik został dodany.");
        } catch (SQLException e) {
            System.out.println("Błąd dodania użytkownika");
        }
    }

    public void showAllAcc(){
        String queryShow = "SELECT * FROM `accounts`";
        try(PreparedStatement pstmtShowAcc = conn.prepareStatement(queryShow)) {
            ResultSet resultSet = pstmtShowAcc.executeQuery();
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Imię: " + resultSet.getString("imie"));
                System.out.println("Nazwisko: " + resultSet.getString("nazwisko"));
                System.out.println("Saldo: " + resultSet.getString("saldo") + "zł");
                System.out.println("\n");
            }
        }
        catch (SQLException e) {
            System.out.println("Błąd pokazania wszystkich użytkowników");
        }
    }

    public void deleteAcc(int id){
        String queryDelete = "DELETE FROM `accounts` WHERE id = ?;";
        try(PreparedStatement pstmtDeleteAcc = conn.prepareStatement(queryDelete)){
            pstmtDeleteAcc.setInt(1,id);
            pstmtDeleteAcc.executeUpdate();
            System.out.println("Użytkownik został usunięty");
        }catch (SQLException e) {
            System.out.println("Błąd usuwania użytkownika");
        }
    }


}
