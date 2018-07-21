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

    public MainListener(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public int process(List<Update> updates) {
        // process updates
        for (Update update : updates) {
            Message message = update.message();
            if (message.text() != null && message.text().equals("hi")){
                bot.execute(hiProcessor.process(message));
                
            } else if (message.voice() != null){
                bot.execute(audioProcessor.process(message));
//                .parseMode(ParseMode.HTML)
//                        .disableWebPagePreview(true)
//                        .disableNotification(true);;
            }
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
