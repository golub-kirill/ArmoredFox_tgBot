import java.sql.*;

public class ConnectionManager {

    static final String URL = "jdbc:mysql://localhost:3306/armoredfox_db?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone= Europe/Kiev";
    static final String user = "Fox0x";
    static final String psw = "root";

    boolean isUserExists = false;

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;

    public void DbConnection() throws SQLException {

        connection = DriverManager.getConnection(URL, user, psw);
        statement = connection.createStatement();

    }

    void DbCRUD() throws SQLException {

        String sql = "INSERT INTO users (author_id, chat_id, name, phoneNunber) VALUE (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, Bot.authorID);
        preparedStatement.setLong(2, Bot.chatID);
        preparedStatement.setString(3, Bot.authorName);
        preparedStatement.setString(4, Bot.authorPhoneNumber);
        preparedStatement.execute();
    }

    void DbExist(int authorID) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE author_id = " + authorID + "");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            isUserExists = true;
        }
    }

}
