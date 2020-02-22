package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigParser {
    private static final String CONFIG_BOT_FILE = "config/config.properties";

    private static String BOT_NAME;
    private static String BOT_TOKEN;
    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PASSWORD;

    public static void load() {
        Properties properties = new Properties();
        try {
            InputStream is = new FileInputStream(new File(CONFIG_BOT_FILE));
            properties.load(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setBotUserName(properties.getProperty("BotUserName"));
        setBotToken(properties.getProperty("BotToken"));
        setDbUrl(properties.getProperty("DbURL"));
        setDbUser(properties.getProperty("DbUser"));
        setDbPassword(properties.getProperty("DbPassword"));
    }

    public static String getBotUserName() {
        return BOT_NAME;
    }

    public static void setBotUserName(String botName) {
        BOT_NAME = botName;
    }

    public static String getBotToken() {
        return BOT_TOKEN;
    }

    public static void setBotToken(String botToken) {
        BOT_TOKEN = botToken;
    }

    public static String getDbUrl() {
        return DB_URL;
    }

    public static void setDbUrl(String dbUrl) {
        DB_URL = dbUrl;
    }

    public static String getDbUser() {
        return DB_USER;
    }

    public static void setDbUser(String dbUser) {
        DB_USER = dbUser;
    }

    public static String getDbPassword() {
        return DB_PASSWORD;
    }

    public static void setDbPassword(String dbPassword) {
        DB_PASSWORD = dbPassword;
    }
}
