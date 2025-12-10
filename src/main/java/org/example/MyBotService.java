package org.example;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Yordamchi: barcha ReplyKeyboardMarkup menyularini qaytaradi.
 */
public class MyBotService{

    public ReplyKeyboardMarkup getPhoneRequestMarkup() {
        KeyboardRow row = new KeyboardRow();
        KeyboardButton contactBtn = new KeyboardButton(" Raqam yuborish");
        contactBtn.setRequestContact(true);
        row.add(contactBtn);

        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(row);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(rows);
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);
        return markup;
    }

    public ReplyKeyboardMarkup getMainMenuMarkup() {
        KeyboardRow r1 = new KeyboardRow();
        r1.add(new KeyboardButton(" Buyurtma berish"));
        r1.add(new KeyboardButton("🧾 Buyurtmalarim"));

        KeyboardRow r2 = new KeyboardRow();
        r2.add(new KeyboardButton("ℹ️ Bot haqida"));
        r2.add(new KeyboardButton("🤝 Hamkorlik"));

        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(r1);
        rows.add(r2);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(rows);
        markup.setResizeKeyboard(true);
        return markup;
    }

    public ReplyKeyboardMarkup getBuyurtmaMenuMarkup() {
        KeyboardRow r1 = new KeyboardRow();
        r1.add(new KeyboardButton(" Pizzalar"));
        r1.add(new KeyboardButton(" lavash"));

        KeyboardRow r2 = new KeyboardRow();
        r2.add(new KeyboardButton(" salqin ichimliklar "));
        r2.add(new KeyboardButton(" burgerlar "));

        KeyboardRow r3 = new KeyboardRow();
        r3.add(new KeyboardButton("⬅️ Orqaga"));

        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(r1);
        rows.add(r2);
        rows.add(r3);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(rows);
        markup.setResizeKeyboard(true);
        return markup;
    }

    public ReplyKeyboardMarkup getTolovMenuMarkup() {
        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton("💵 Naxt"));
        row.add(new KeyboardButton("💳 Click orqali to‘lov"));

        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(row);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(rows);
        markup.setResizeKeyboard(true);
        return markup;
    }

    public ReplyKeyboardMarkup getChekMenuMarkup() {
        KeyboardRow row = new KeyboardRow();
        row.add(new KeyboardButton(" Chek yuborish"));

        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(row);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(rows);
        markup.setResizeKeyboard(true);
        return markup;
    }
    public ReplyKeyboardMarkup getLocationRequestMarkup() {
        KeyboardRow row = new KeyboardRow();
        KeyboardButton locationBtn = new KeyboardButton("📍 Joylashuvni yuborish");
        locationBtn.setRequestLocation(true);
        row.add(locationBtn);

        List<KeyboardRow> rows = new ArrayList<>();
        rows.add(row);

        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(rows);
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);
        return markup;
    }

}


