import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.List;

/**
 * Created by nmiran on 04/02/2018.
 */
public class MainListener implements UpdatesListener {
    private TelegramBot bot;
    private HiMessageProcessor hiProcessor = new HiMessageProcessor();
    private AudioMessageProcessor audioProcessor = new AudioMessageProcessor();
    private InlineMenu inlineMenu = new InlineMenu();
    private SearchMessageProcessor searchMessageProcessor = new SearchMessageProcessor();

    public MainListener(TelegramBot bot) {
        this.bot = bot;
    }


    public int process(List<Update> updates) {
            // process updates
        try {
            for (Update update : updates) {
                System.out.println(update);
                if (update.callbackQuery() != null){
                    callBackExecutor(update);
                }
                Message message = update.message();
                if (message.text() != null && message.text().toLowerCase().equals("start")) {
                    bot.execute(inlineMenu.process(message));
                } else if (message.text() != null && message.text().toLowerCase().equals("hi")) {
                    bot.execute(hiProcessor.process(message));
                }
//                else if (message.text() != null && message.text().toLowerCase().equals("search")){
//                    bot.execute(searchMessageProcessor.process(message));
//                }
                else if (message.voice() != null) {
                    bot.execute(audioProcessor.process(message));
//                .parseMode(ParseMode.HTML)
//                        .disableWebPagePreview(true)
//                        .disableNotification(true);;
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }
        catch (Exception e) {
            System.out.println(e);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }
    }

    private void callBackExecutor(Update update) {
        if (update.callbackQuery().data().equals("search")){
            bot.execute(searchMessageProcessor.process(update.callbackQuery().message()));
        }
    }
}
