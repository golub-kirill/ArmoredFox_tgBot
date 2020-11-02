import config.ConfigParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DbManager {

	static final String URL = "jdbc:postgresql://" + ConfigParser.getDbUrl();
	static final Logger logger = LoggerFactory.getLogger(DbManager.class);

	static Connection connection = null;
	static Statement statement = null;
	static PreparedStatement preparedStatement = null;

	public void DbConnection() {

		try {
			connection = DriverManager.getConnection(URL, ConfigParser.getDbUser(), ConfigParser.getDbPassword());
			statement = connection.createStatement();
			logger.debug("Открыли соединение с БД");
		} catch (SQLException e) {
			logger.error("Не получись подключиться к БД, проверьте правильность данных в config.properties", e);
		}
	}

	void DbCRUD() {
		try {
			String sql = "INSERT INTO users (author_id, chat_id, name, phonenumber) VALUES (?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Bot.authorID);
			preparedStatement.setLong(2, Bot.chatID);
			preparedStatement.setString(3, Bot.authorName);
			preparedStatement.setString(4, Bot.authorPhoneNumber);
			preparedStatement.execute();

			logger.debug("Added user to the database:" + "\n"
					+ "Name = " + Bot.authorName + "\n"
					+ "Author ID = " + Bot.authorID + "\n"
					+ "Chat ID = " + Bot.chatID + "\n"
					+ "Phone = " + Bot.authorPhoneNumber);

		} catch (SQLException e) {
			logger.error("Failed to add row to database.", e);
		}
	}

	boolean DbExist(int authorID) throws SQLException {
		logger.debug("Check for user presence in the database");
		preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE author_id = " + authorID + "");
		ResultSet resultSet = preparedStatement.executeQuery();
		return resultSet.next();
	}
}
