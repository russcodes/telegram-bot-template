package keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class Keyboards {

    public static InlineKeyboardMarkup setInlineKeyboard(String text) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        firstRow.add(new InlineKeyboardButton().setText(text).setCallbackData(text));
        rowList.add(firstRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup setInlineKeyboard(String text1, String text2) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        List<InlineKeyboardButton> secondRow = new ArrayList<>();

        firstRow.add(new InlineKeyboardButton().setText(text1).setCallbackData(text1));
        secondRow.add(new InlineKeyboardButton().setText(text2).setCallbackData(text2));

        rowList.add(firstRow);
        rowList.add(secondRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup addressKeyboard(String text1, String text2, String text3) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        List<InlineKeyboardButton> thirdRow = new ArrayList<>();

        firstRow.add(
                new InlineKeyboardButton()
                        .setText(text1)
                        .setUrl("https://goo.gl/maps/k3aEkRDWPddz8S9v6")
        );
        firstRow.add(
                new InlineKeyboardButton()
                        .setText(text2)
                        .setUrl(
                                "https://maps.apple.com/?address=Marienplatz%2028,%2080331%20Munich,%20Germany&ll=48.136979,11.574793&q=Marienplatz%2028&_ext=EiYpPfQ4WPUQSEAxXlxcGNkiJ0A5u8letBsSSEBBooTncL0pJ0BQBA%3D%3D")
        );
        secondRow.add(new InlineKeyboardButton().setText(text3).setCallbackData(text3));

        rowList.add(firstRow);
        rowList.add(secondRow);
        rowList.add(thirdRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup secondMainKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        List<InlineKeyboardButton> thirdRow = new ArrayList<>();
        List<InlineKeyboardButton> fourthRow = new ArrayList<>();

        firstRow.add(
                new InlineKeyboardButton().setText("Leave a Feedback!").setCallbackData("Feedback")
        );
        secondRow.add(
                new InlineKeyboardButton().setText("Address").setCallbackData("Address")
        );
        thirdRow.add(
                new InlineKeyboardButton().setText("Contact").setCallbackData("Contact")
        );
        fourthRow.add(
                new InlineKeyboardButton().setText("⬅️").setCallbackData("left_2")
        );
        fourthRow.add(
                new InlineKeyboardButton().setText("2").setCallbackData("2")
        );
        fourthRow.add(
                new InlineKeyboardButton().setText("➡️").setCallbackData("right_2")
        );

        rowList.add(firstRow);
        rowList.add(secondRow);
        rowList.add(thirdRow);
        rowList.add(fourthRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup firstMainKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        List<InlineKeyboardButton> thirdRow = new ArrayList<>();
        List<InlineKeyboardButton> fourthRow = new ArrayList<>();
        List<InlineKeyboardButton> fifthRow = new ArrayList<>();

        firstRow.add(
                new InlineKeyboardButton()
                        .setText("Menu")
                        .setUrl(
                                "https://drive.google.com/drive/folders/1PaqrQa-4N-9KejZUXMXBKQenIXR_u9pL?usp=sharing"
                        )
        );
        firstRow.add(
                new InlineKeyboardButton().setText("Working hours").setCallbackData("Working hours")
        );
        secondRow.add(
                new InlineKeyboardButton().setText("Our Instagram").setUrl("https://www.instagram.com/russcodes")
        );
        thirdRow.add(
                new InlineKeyboardButton().setText("Share Us").setSwitchInlineQuery("Check out EL CASA")
        );
        fourthRow.add(
                new InlineKeyboardButton().setText("⬅️").setCallbackData("left_1")
        );
        fourthRow.add(
                new InlineKeyboardButton().setText("1").setCallbackData("1")
        );
        fourthRow.add(
                new InlineKeyboardButton().setText("➡️").setCallbackData("right_1")
        );

        rowList.add(firstRow);
        rowList.add(secondRow);
        rowList.add(thirdRow);
        rowList.add(fourthRow);
        rowList.add(fifthRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}
