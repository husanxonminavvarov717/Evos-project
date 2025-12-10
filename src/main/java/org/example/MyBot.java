
        package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public class MyBot extends TelegramLongPollingBot {

    // O'zingning admin chat id va token/username ni shu yerga qo'y
    private final String ADMIN_CHAT_ID = "7038296036"; // o'zgartir
    @Override
    public String getBotUsername() {
        return "EvosByKhusannBot"; // o'zgartir
    }

    @Override
    public String getBotToken() {
        return "***"; // o'zgartir
    }

    private final MyBotService server = new MyBotService();

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update == null) return;

            // Xabar bo'lsa
            if (update.hasMessage()) {
                Message message = update.getMessage();
                long chatId = message.getChatId();

                // 1) /start: salom + raqam so'rash
                if (message.hasText() && "/start".equals(message.getText().trim())) {
                    sendText(chatId, "👋 Assalomu alaykum!\nIltimos, telefon raqamingizni yuboring.");
                    // yuborishda markup ni SendMessage ga o'rnatamiz
                    SendMessage ask = new SendMessage();
                    ask.setChatId(String.valueOf(chatId));
                    ask.setText("Raqamingizni bosing:");
                    ask.setReplyMarkup(server.getPhoneRequestMarkup());
                    execute(ask);
                    return;
                }

                // 2) Foydalanuvchi kontakt yubordi
                if (message.hasContact()) {
                    Contact contact = message.getContact();
                    String phone = contact.getPhoneNumber();
                    sendText(chatId, "✅ Raqamingiz qabul qilindi: " + phone + "\nEndi menyudan tanlang.");
                    SendMessage main = new SendMessage();
                    main.setChatId(String.valueOf(chatId));
                    main.setText("📋 Asosiy menyu:");
                    main.setReplyMarkup(server.getMainMenuMarkup());
                    execute(main);
                    return;
                }

                // 3) Matnli tugmalar / oddiy matn
                if (message.hasText()) {
                    String text = message.getText().trim();

                    switch (text) {
                        case "🍰 Buyurtma berish":
                            SendMessage buy = new SendMessage();
                            buy.setChatId(String.valueOf(chatId));
                            buy.setText("Qaysi turdagi mahsulotni xohlaysiz?");
                            buy.setReplyMarkup(server.getBuyurtmaMenuMarkup());
                            execute(buy);
                            break;

                        case "⬅️ Orqaga":
                            SendMessage main = new SendMessage();
                            main.setChatId(String.valueOf(chatId));
                            main.setText("📋 Asosiy menyu:");
                            main.setReplyMarkup(server.getMainMenuMarkup());
                            execute(main);
                            break;

                        case "🧾 Buyurtmalarim":
                            sendText(chatId, "📦 Sizning buyurtmalaringiz hali mavjud emas.");
                            break;

                        case "ℹ️ Bot haqida":
                            sendText(chatId, "🟢 Assalomu alaykum va rohmatullohi va barokatuh!\nBizning botdan foydalanganingizdan xursandmiz. Siz mazali fastfood va salqin ichimliklarimizdan bahraman olishingiz mumkin");
                            break;


                        case "🤝 Hamkorlik":
                            sendText(chatId, "📞 Biz bilan bog‘lanish: +998 93 717 07 28");
                            break;

                        // kategoriya tanlandi -> to'lov menyusi
                        case "🎂 pizzalr":
                        case "🥧 lavash":
                        case "🍪 salqin ichimliklar":
                        case "🍱 burgerlar":
                            SendMessage tolov = new SendMessage();
                            tolov.setChatId(String.valueOf(chatId));
                            tolov.setText("To‘lov turini tanlang:");
                            tolov.setReplyMarkup(server.getTolovMenuMarkup());
                            execute(tolov);
                            break;

                        // Naxt (kesh)
                        case "💵 Naxt":
                            // Buyurtma muvaffaqiyatli: qayta asosiy menyuga
                            sendText(chatId, "✅ Buyurtma muvaffaqiyatli qabul qilindi. Tez orada siz bilan bog'lanamiz.");
                            SendMessage main2 = new SendMessage();
                            main2.setChatId(String.valueOf(chatId));
                            main2.setText("📋 Asosiy menyu:");
                            main2.setReplyMarkup(server.getMainMenuMarkup());
                            execute(main2);
                            // ma'lumot admin ga ham yuborilsin (soddalashtirilgan)
                            sendTextToAdmin("Yangi buyurtma (Naxt) chatId: " + chatId);
                            break;

                        // Click orqali to'lov
                        case "💳 Click orqali to‘lov":
                            SendMessage card = new SendMessage();
                            card.setChatId(String.valueOf(chatId));
                            card.setText("💳 Karta raqami:\n5614 6825 1378 8143\nJumaeva S\n\nTo‘lovni amalga oshirib, pastdagi '📸 Chek yuborish' tugmasini bosing.");
                            card.setReplyMarkup(server.getChekMenuMarkup());
                            execute(card);
                            break;

                        // Chek yuborish
                        case "📸 Chek yuborish":
                            sendText(chatId, "✅ Chekingiz yuborildi, admin tomonidan tekshiriladi.");
                            // Adminga ham xabar yuborish: foydalanuvchi chatId va habarnoma
                            sendTextToAdmin("Foydalanuvchi " + chatId + " Click orqali to'lov uchun chek yubordi.");
                            break;

                        default:
                            sendText(chatId, "Iltimos, menyudan tanlang yoki /start yozing.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // oddiy yordamchi: foydalanuvchiga matn yuborish
    private void sendText(long chatId, String text) {
        try {
            SendMessage m = new SendMessage();
            m.setChatId(String.valueOf(chatId));
            m.setText(text);
            execute(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // admin ga matn yuborish
    private void sendTextToAdmin(String text) {
        try {
            SendMessage m = new SendMessage();
            m.setChatId(ADMIN_CHAT_ID);
            m.setText(text);
            execute(m);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
