import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class Bot extends TelegramLongPollingBot {
    static final String BOT_USERNAME = "@ArmoredFox_bot";
    static final String BOT_TOKEN = "1017642766:AAGDA0446HoM8cLGzUTHmmmyQ2krLKXT3tA";

    String message;
    static String authorName;
    static String authorPhoneNumber;
    static int messageID = 0;
    static int authorID = 0;
    static long chatID = 0;

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    public void onUpdateReceived(Update update) {

        authorName = update.getMessage().getFrom().getFirstName();
        messageID = update.getMessage().getMessageId();
        authorID = update.getMessage().getFrom().getId();
        chatID = update.getMessage().getChatId().intValue();

        ConnectionManager connectionManager = new ConnectionManager();

        SendMessage sendMessage = new SendMessage().setChatId(chatID);
        message = update.getMessage().getText();

        ArrayList<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        //Есть ли сообщение и есть ли в нём текст.
        if (update.hasMessage() && update.getMessage().hasText()) {

            switch (message) {
                //Поздоровались.
                case "/start": {
                    //Проверяем на наличие authorID в базе данных.
                    try {
                        connectionManager.DbConnection();
                        connectionManager.DbExist(authorID);
                        connectionManager.connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //Если authorID нет в БД, то просим номер.
                    if (!connectionManager.isUserExists) {
                        try {
                            execute(new SendMessage().setChatId(chatID).enableMarkdown(true).setText(
                                    "Привет! Я бот @ArmoredFox." + "\n" + "\n" +
                                            "Меня создал [Fox_x](tg://user?id=282614062), но пока он не знает для чего." + "\n" +
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
                        keyboardFirstRow.add(new KeyboardButton().setText("Поделится номером \uD83D\uDCF1").setRequestContact(true));
                        keyboardSecondRow.add(new KeyboardButton().setText("Не буду \uD83D\uDD12"));
                        keyboard.add(keyboardFirstRow);
                        keyboard.add(keyboardSecondRow);
                        replyKeyboardMarkup.setKeyboard(keyboard);
                        sendMessage.setReplyMarkup(replyKeyboardMarkup);
                    }
                    //Если authorID есть в БД, то кидаем приветствие.
                    else {
                        sendMessage.setText("Привет, " + authorName + "! Рад снова тебя видеть \uD83D\uDE0D");
                    }
                }
                break;
                //Во сколько на работу.
                case "На сколько мне сегодня":
                case "/time": {
                    Integer dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                    if (dayOfWeek.equals(6) || dayOfWeek.equals(7)) {
                        sendMessage.enableMarkdown(true).setText("Тебе сегодня на [21:00]" +
                                "(https://docs.google.com/spreadsheets/d/1Fh72OSGcpNXXPduv734fC0x5CN3T4qzzJCuAkW6yNp4/)");
                    } else sendMessage.enableMarkdown(true).setText("Тебе сегодня на [20:00]" +
                            "(https://docs.google.com/spreadsheets/d/1Fh72OSGcpNXXPduv734fC0x5CN3T4qzzJCuAkW6yNp4/)");
                }
                break;
                //Твой ID.
                case "/id": {
                    sendMessage.setText(authorName + " ,твой ID: " + authorID).setReplyToMessageId(messageID);
                }
                break;
                case "Не буду \uD83D\uDD12": {
                    sendMessage.setText("Окей \uD83D\uDE14").setReplyMarkup(new ReplyKeyboardRemove().setSelective(true));
                }
                break;
                default:
                    sendMessage.setText("Я не понимаю \uD83D\uDE13");
            }
        }
        //Если в сообщении нет текста
        else {
            sendMessage.setText("Я не понимаю \uD83D\uDE13");
        }
        // Получаем номер телефона и пишем в БД.
        if (update.getMessage().hasContact()) {
            authorPhoneNumber = update.getMessage().getContact().getPhoneNumber();

            try {
                connectionManager.DbConnection();
                connectionManager.DbCRUD();
                connectionManager.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //отправляем ответ и удаляем кнопки.
            sendMessage.setText(authorName + ",я запомнил твой номер телефона:" + authorPhoneNumber).setReplyMarkup(new ReplyKeyboardRemove().setSelective(true));

        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Пробуем отправить сообщение.
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    public String getBotUsername() {
        return BOT_USERNAME;
    }

    public String getBotToken() {
        return BOT_TOKEN;
    }
}