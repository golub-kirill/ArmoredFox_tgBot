import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Calendar;

public class Bot extends TelegramLongPollingBot {

    private static final String BOT_USERNAME = "@ArmoredFox_bot";
    private static final String BOT_TOKEN = "1017642766:AAGDA0446HoM8cLGzUTHmmmyQ2krLKXT3tA";

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    public void onUpdateReceived(Update update) {

        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());
        String message = update.getMessage().getText();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        int messageID = update.getMessage().getMessageId();

        if (message.equals("/replykeyboard")) {
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
        }
        //Поздоровались
        if (message.equals("/start")) {
            sendMessage.enableMarkdown(true).setText
                    ("Привет! Я бот @ArmoredFox. " + "\n" + "\n" +
                            "Меня создал [Fox_x](tg://user?id=282614062), но пока он не знает для чего." + "\n" +
                            "Сейчас я умею совсем немного, но постоянно учусь чему-то новому.");}



        if (message.contains("ривет") && !message.contains(" ")) {
            sendMessage.setText("Приветик \uD83D\uDC4B").setReplyToMessageId(messageID);
        }
        //Во сколько на работу.
        // TODO разлбраться с установкой будильника.
        if (message.equalsIgnoreCase("На сколько мне сегодня")){
            Integer dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek.equals(6)||dayOfWeek.equals(7)){
                sendMessage.setText("Тебе сегодня на 21:00");

            }
            else sendMessage.setText("Тебе сегодня на 20:00");
        }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
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