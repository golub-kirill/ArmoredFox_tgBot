import config.ConfigParser;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class Main {
    public static void main(String[] args) {
        
        ConfigParser.load(); //Загружаем данные из конфига.
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        ApiContextInitializer.init();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
