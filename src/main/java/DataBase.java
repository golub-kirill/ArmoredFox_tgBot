import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private final String URL = "jdbc:mysql://localhost:3306/armoredfox_db?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USER = "Fox0x";
    private final String PASS = "root";

    public void DbConnector() throws SQLException {

          Connection connection = DriverManager.getConnection(URL, USER, PASS);

        if (!connection.isClosed()){
                System.out.println("Соединение с БД установлено");
            }
        connection.close();

        if (connection.isClosed()){
            System.out.println("Соединение с БД закрыто");
        }
    }
}
