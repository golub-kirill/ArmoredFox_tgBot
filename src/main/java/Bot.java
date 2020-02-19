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
    static final String BOT_USERNAME = "@ArmoredFox_bot";
    static final String BOT_TOKEN = "1017642766:AAGDA0446HoM8cLGzUTHmmmyQ2krLKXT3tA";

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    public void onUpdateReceived(Update update) {

        int messageID = update.getMessage().getMessageId();
        long authorID = update.getMessage().getFrom().getId().longValue();
        long chatID = update.getMessage().getChatId();
        String authorName = update.getMessage().getFrom().getFirstName();

        SendMessage sendMessage = new SendMessage().setChatId(chatID);
        String message = update.getMessage().getText();

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Есть ли сообщение и есть ли в нём текст.
        if (update.hasMessage() || update.getMessage().hasText()) {

            switch (message){
                //Поздоровались.
                case "/start":{
                    try {
                    execute(new SendMessage().setChatId(chatID).enableMarkdown(true).setText(
                            "Привет! Я бот @ArmoredFox."+ "\n"+"\n"+
                                    "Меня создал [Fox_x](tg://user?id=282614062), но пока он не знает для чего."+"\n"+
                                    "Сейчас я умею совсем немного, но постоянно учусь чему-то новому."));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                    //Задержка в пол секунды перед вторым сообщением.
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sendMessage.setText("Поделись номером");

                    keyboard.clear();
                    keyboardFirstRow.add(new KeyboardButton().setText("Share your number >").setRequestContact(true));
                    keyboard.add(keyboardFirstRow);
                    replyKeyboardMarkup.setKeyboard(keyboard);
                } break;
                //Во сколько на работу.
                case "На сколько мне сегодня":
                case "/time": {
                    Integer dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                        if (dayOfWeek.equals(6) || dayOfWeek.equals(7)) {
                            sendMessage.enableMarkdown(true).setText("Тебе сегодня на [21:00]" +
                                    "(https://docs.google.com/spreadsheets/d/1Fh72OSGcpNXXPduv734fC0x5CN3T4qzzJCuAkW6yNp4/)");
                        } else sendMessage.enableMarkdown(true).setText("Тебе сегодня на [20:00]" +
                                "(https://docs.google.com/spreadsheets/d/1Fh72OSGcpNXXPduv734fC0x5CN3T4qzzJCuAkW6yNp4/)");} break;
                //Твой ID.
                case "/id":{
                    sendMessage.setText(authorName + " ,твой ID: " + authorID).setReplyToMessageId(messageID);
                    System.out.println(authorName + "'s chat ID is - " + authorID);
                } break;
                default: sendMessage.setText("Я не понимаю \uD83D\uDE13");
            }
        }
        //Если в сообщении нет текста
        else sendMessage.setText("Я не понимаю \uD83D\uDE13");

        // Получаем номер телефона.
        if (update.getMessage().hasContact()){
            String phoneNumber = update.getMessage().getContact().getPhoneNumber();
            System.out.println(phoneNumber);
            sendMessage.setText(authorName+", твой номер телефона:" + phoneNumber);
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        try {execute(sendMessage); }

        catch (TelegramApiException e) { e.printStackTrace(); }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    public String getBotToken() {
        return BOT_TOKEN;
    }
}