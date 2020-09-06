import org.apache.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import keyboard.*;

public class TelegramBot extends TelegramLongPollingBot {
    private static final Logger logger = Logger.getLogger(TelegramBot.class);
    private Boolean isForwardMsg;
    private Message messageToDelete;
    private static String BotToken, BotName, ChannelName;
    private static String startMsg, whMsg, addressMsg, contactMsg, feedbackMsg;

    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            Message message = update.getMessage();
            if(message.hasText()){
                handleMessage(message);
            }
        } else if(update.hasCallbackQuery()){
            handleCallbackQuery(update);
        }
    }

    //Handling private messages
    private void handleMessage(Message message) {
        String messageText = message.getText();
        logger.info("Incoming message: " + messageText);
        switch (messageText){
            case "/start":
                sendMessage(
                        message,
                        String.format(startMsg, message.getFrom().getFirstName()),
                        Keyboards.firstMainKeyboard()
                        );
                isForwardMsg = false;
                break;
            default:
                if(isForwardMsg){
                    forwardMessage(message);
                    deleteMessage(messageToDelete);
                    sendMessage(message, "Thanks a lot, we appreciate this!");
                    sendMessage(
                            message,
                            String.format(startMsg, message.getFrom().getFirstName()),
                            Keyboards.firstMainKeyboard()
                    );
                }else {
                    deleteMessage(messageToDelete);
                    sendMessage(message, "Please use the given menu");
                    sendMessage(
                            message,
                            String.format(startMsg, message.getFrom().getFirstName()),
                            Keyboards.firstMainKeyboard()
                    );
                }
        }
    }

    //Handling CallbackQueries
    private void handleCallbackQuery(Update update) {
        CallbackQuery query = update.getCallbackQuery();
        String queryData = query.getData();
        logger.info("Incoming query: " + queryData);

        switch (queryData){
            case "1":
                answerCallbackQuery(query, "First page");
                break;
            case "2":
                answerCallbackQuery(query, "Second page");
                break;
            case "left_1":
            case "right_1":
            case "Back":
                isForwardMsg = false;
                editMessage(
                        update,
                        String.format(startMsg, update.getCallbackQuery().getFrom().getFirstName()),
                        Keyboards.secondMainKeyboard()
                );
                break;
            case "left_2":
            case "right_2":
            case "Home":
                editMessage(
                        update,
                        String.format(startMsg, update.getCallbackQuery().getFrom().getFirstName()),
                        Keyboards.firstMainKeyboard()
                );
                break;
            case "Reservation":
                editMessage(
                        update,
                        "Are you planning to make a reservation?",
                        Keyboards.setInlineKeyboard("Yes", "Home")
                );
                break;
            case "Menu":
                editMessage(
                        update,
                        "This is a menu of our cafeteria, Enjoy",
                        Keyboards.setInlineKeyboard("Home")
                );
                break;
            case "Working hours":
                editMessage(
                        update,
                        whMsg,
                        Keyboards.setInlineKeyboard("Home")
                );
                break;
            case "Degustation":
                editMessage(
                        update,
                        "Our coffee degustation",
                        Keyboards.setInlineKeyboard("SIGN", "Home")
                );
                break;
            case "Feedback":
                isForwardMsg = true;
                editMessage(
                        update,
                        feedbackMsg,
                        Keyboards.setInlineKeyboard("Back")
                );
                break;
            case "Address":
                editMessage(
                        update,
                        addressMsg,
                        Keyboards.addressKeyboard("Google Maps", "Apple Maps", "Back")
                );
                break;
            case "Contact":
                editMessage(
                        update,
                        contactMsg,
                        Keyboards.setInlineKeyboard("Back")
                );
                break;
        }
    }

    //Message without any keyboard
    private void sendMessage(Message message, String text) {
        SendMessage sendMessage = new SendMessage()
                .enableMarkdown(true)
                .setChatId(message.getChatId())
                .setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //Message with an InlineKeyboardMarkup
    private void sendMessage(Message message, String text, InlineKeyboardMarkup keyboard) {
        SendMessage sendMessage = new SendMessage()
                .enableMarkdown(true)
                .setChatId(message.getChatId())
                .setReplyMarkup(keyboard)
                .setText(text);
        try {
            messageToDelete = execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //Message with a ReplyKeyboardMarkup
    private void sendMessage(Message message, String text, ReplyKeyboardMarkup keyboard) {
        SendMessage sendMessage = new SendMessage()
                .enableMarkdown(true)
                .setChatId(message.getChatId())
                .setReplyMarkup(keyboard)
                .setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //Method edits message by using CallbackQuery
    private void editMessage(Update update, String text, InlineKeyboardMarkup keyboard){
        int message_id = update.getCallbackQuery().getMessage().getMessageId();
        long chat_id = update.getCallbackQuery().getMessage().getChatId();

        EditMessageText new_message = new EditMessageText()
                .setChatId(chat_id)
                .enableMarkdown(true)
                .setMessageId(message_id)
                .setReplyMarkup(keyboard)
                .setText(text);
        try {
            execute(new_message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //Deleting a specific message in the chat
    private void deleteMessage(Message message){
        DeleteMessage deleteMessage = new DeleteMessage()
                .setMessageId(message.getMessageId())
                .setChatId(message.getChatId());
        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //Message appearing at the top of the chat
    private void answerCallbackQuery(CallbackQuery callbackQuery, String text){
        AnswerCallbackQuery query = new AnswerCallbackQuery()
                .setCallbackQueryId(callbackQuery.getId())
                .setText(text)
                .setCacheTime(2);
        try {
            execute(query);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //Forwarding message to the chat
    private void forwardMessage(Message message){
        logger.info(message.getChatId());
        ForwardMessage forwardMessage = new ForwardMessage()
                .setMessageId(message.getMessageId())
                .setFromChatId(message.getChatId())
                .setChatId(ChannelName);
        try {
            execute(forwardMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //Getting the variables from the file
    public static void settings(){
        Properties properties = new Properties();
        FileInputStream in;
        try {
            in = new FileInputStream("config.properties");
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Uncomment, to run on your computer
//        BotToken = properties.getProperty("BotToken");
//        BotName = properties.getProperty("BotUsername", "Unknown Bot");
//        ChannelName = properties.getProperty("ChannelName");

        //Use this if you want to deploy your bot and secure this info
        BotToken = System.getenv("BotToken");
        BotName = System.getenv("BotUsername");
        ChannelName = System.getenv("ChannelName");

        startMsg = properties.getProperty("StartMsg");
        whMsg = properties.getProperty("WorkingHoursMsg");
        addressMsg = properties.getProperty("AddressMsg");
        contactMsg = properties.getProperty("ContactMsg");
        feedbackMsg = properties.getProperty("FeedbackMsg");
    }

    public String getBotUsername() {
        return BotName;
    }

    public String getBotToken() {
        return BotToken;
    }
}
