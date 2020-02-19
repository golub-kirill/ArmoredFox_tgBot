import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Calendar;

public class Bot extends TelegramLongPollingBot {

    private static final String BOT_USERNAME = "@ArmoredFox_bot";
    private static final String BOT_TOKEN = "1017642766:AAGDA0446HoM8cLGzUTHmmmyQ2krLKXT3tA";

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    public void onUpdateReceived(Update update) {

        int messageID = update.getMessage().getMessageId();
        long authorID = update.getMessage().getFrom().getId().longValue();
        long chatID = update.getMessage().getChatId();
        String authorName = update.getMessage().getFrom().getFirstName();

        SendMessage sendMessage = new SendMessage().setChatId(chatID);
        String message = update.getMessage().getText();

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        if (update.hasMessage() && update.getMessage().hasText()) {
            //replykeyboard
        /*if (message.equals("/replykeyboard")) {
            sendMessage.setText("Выбери самую красивую:");

            ArrayList<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
            KeyboardRow keyboardFirstRow = new KeyboardRow();
            KeyboardRow keyboardSecondRow = new KeyboardRow();
            keyboard.clear();
            keyboardFirstRow.add(new KeyboardButton().setText("Share your number >").setRequestContact(true)); //TODO починить это блэд.
            keyboardSecondRow.add(new KeyboardButton().setText("А я втарая кнапка"));
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);

            replyKeyboardMarkup.setKeyboard(keyboard);
        }*/
            //Поздоровались
            if (message.contains("start")) {
                sendMessage.enableMarkdown(true).setText
                        ("Привет! Я бот @ArmoredFox. " + "\n" + "\n" +
                                "Меня создал [Fox_x](tg://user?id=282614062), но пока он не знает для чего." + "\n" +
                                "Сейчас я умею совсем немного, но постоянно учусь чему-то новому.");
            }
            //Во сколько на работу.
            else if (message.contains("На сколько мне сегодня") || message.equals("/time")) {
                Integer dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                if (dayOfWeek.equals(6) || dayOfWeek.equals(7)) {
                    sendMessage.enableMarkdown(true).setText("Тебе сегодня на [21:00]" +
                            "(https://docs.google.com/spreadsheets/d/1Fh72OSGcpNXXPduv734fC0x5CN3T4qzzJCuAkW6yNp4/)");
                } else sendMessage.enableMarkdown(true).setText("Тебе сегодня на [20:00]" +
                        "(https://docs.google.com/spreadsheets/d/1Fh72OSGcpNXXPduv734fC0x5CN3T4qzzJCuAkW6yNp4/)");
            }
            //Твой id.
            else if (message.contains("/id")){
                sendMessage.setText(authorName + " ,твой ID: " + authorID);
                System.out.println(authorName + "'s chat ID is - " + authorID);
            }

            else sendMessage.setText("Я не понимаю \uD83D\uDE13");
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        try {execute(sendMessage); }

        catch (TelegramApiException e) { e.printStackTrace(); }
        }

    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    public String getBotToken() {
        return BOT_TOKEN;
    }
}