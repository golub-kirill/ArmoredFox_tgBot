import config.ConfigParser;

import java.sql.*;

public class DbManager {

    static final String URL = "jdbc:mysql://"+ ConfigParser.getDbUrl()+"?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone= Europe/Kiev";

    boolean isUserExists;

    Connection connection = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;

    public void DbConnection() throws SQLException {

        connection = DriverManager.getConnection(URL, ConfigParser.getDbUser(), ConfigParser.getDbPassword());
        statement = connection.createStatement();

    }

    void DbCRUD() throws SQLException {

        String sql = "INSERT INTO users (author_id, chat_id, name, phoneNumber) VALUE (?, ?, ?, ?)";
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
        isUserExists = resultSet.next();
    }

}
