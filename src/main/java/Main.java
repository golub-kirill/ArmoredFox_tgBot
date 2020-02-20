import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        ApiContextInitializer.init();

        DataBase dataBase = new DataBase();
        try {
            dataBase.DbConnector();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
