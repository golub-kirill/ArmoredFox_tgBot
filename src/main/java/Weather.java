import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Weather {

    public String WeatherParser() throws IOException {

        Document document = Jsoup.connect("https://www.msn.com/ru-ru/weather/today?gps=1&el=JmCimkUY%2bf4D7%2bSBaNIOXg%3d%3d").get();
        /*String currentWeatherText = "\uD83C\uDF21 Температура составляет " + document.select("span[class=current]").first().text() + "° градусов. \n";
        String feel = "✨ " + document.select("div[class=weather-info]").select("li").get(0).text();
        String velocity = "\uD83D\uDCA8 " + document.select("div[class=weather-info]").select("li").get(1).text();
        String pressure = "\uD83E\uDDF1 " + document.select("div[class=weather-info]").select("li").get(2).text();
        String visibility = "\uD83E\uDDFF " + document.select("div[class=weather-info]").select("li").get(3).text();
        String humidity = "\uD83D\uDCA7 " + document.select("div[class=weather-info]").select("li").get(4).text();

        return currentWeatherText + "\n" + feel + "\n" + velocity + "\n" + pressure + "\n" + visibility + "\n" + humidity;*/

        StringBuilder currentWeatherText = new StringBuilder();
        currentWeatherText
                .append("\uD83C\uDF21 Температура составляет " + document.select("span[class=current]").first().text() + "° градусов. \n")
                .append("\n✨ " + document.select("div[class=weather-info]").select("li").get(0).text())
                .append("\n\uD83D\uDCA8 " + document.select("div[class=weather-info]").select("li").get(1).text())
                .append("\n\uD83E\uDDF1 " + document.select("div[class=weather-info]").select("li").get(2).text())
                .append("\n\uD83E\uDDFF " + document.select("div[class=weather-info]").select("li").get(3).text())
                .append("\n\uD83D\uDCA7 " + document.select("div[class=weather-info]").select("li").get(4).text());

        return currentWeatherText.toString();
    }
}

