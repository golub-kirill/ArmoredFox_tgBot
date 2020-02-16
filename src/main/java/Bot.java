import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingBot {

    private static final String BOT_USERNAME = "@ArmoredFox_bot";
    private static final String BOT_TOKEN = "1017642766:AAGDA0446HoM8cLGzUTHmmmyQ2krLKXT3tA";

    public void onUpdateReceived(Update update) {

        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        String message = update.getMessage().getText();

        if (message.equals("/start")) {
            sendMessage.enableMarkdown(true).setText
                    ("Привет! Я бот @ArmoredFox. " + "\n" + "\n" +
                            "Меня создал [Fox_x](tg://user?id=282614062), но пока он не знает для чего." + "\n" +
                            "Сейчас я умею совсем немного, но постоянно учусь чему-то новому.");
        } else if (message.contains("ривет") && !message.contains(" ")) {
            sendMessage.setText("Приветик \uD83D\uDC4B");
        } else sendMessage.setText("Я не понимаю, но очень пытаюсь.");


        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return BOT_USERNAME;
    }

    public String getBotToken() {
        return BOT_TOKEN;
    }
        /*if (message.equals("/start")){
                sendMessage.enableMarkdown(true).setText
                        ("Привет! Я бот @ArmoredFox. " + "\n" + "\n" +
                        "Меня создал [Fox_x](tg://user?id=282614062), но пока он не знает для чего." + "\n" +
                        "Сейчас я умею совсем немного, но постоянно учусь чему-то новому.");
        }*/
}