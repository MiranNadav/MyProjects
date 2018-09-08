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
    private TeamProcessor teamProcessor = new TeamProcessor();

    public MainListener(TelegramBot bot) {
        this.bot = bot;
    }

    public int process(List<Update> updates){
        // process updates
        try {
            for (Update update : updates) {
                System.out.println(update);
                Message message = update.message();
                if (message.text() != null && message.text().toLowerCase().equals("hi")) {
                    bot.execute(hiProcessor.process(message));
                } else if (message.text() != null) {
                    bot.execute(teamProcessor.process(message));
                }

            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }
        catch (Exception e){
            System.out.println(e);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }
    }
}