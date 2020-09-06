import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(Main.class);
        TelegramBot.settings();

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            logger.error("ERROR TELEGRAM BOT TOKEN: " + e);
            System.exit(0);
        }
        logger.info("SYSTEM: BOT IS WORKING");
    }
}
