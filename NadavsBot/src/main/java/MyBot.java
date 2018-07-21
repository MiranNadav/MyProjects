import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.impl.TelegramBotClient;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ForceReply;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetWebhook;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

import java.util.List;

/**
 * Created by nmiran on 03/02/2018.
 */
public class MyBot{

    String BotToken;
    String BotUsername;
    public static final String BASEURL = "https://api.telegram.org/bot";
    public static String BOT_TOKEN = "450527622:AAHryh1740PjqLzURLskloeIhXv83cksrnQ";
    public static String BOT_USERNAME= "NadavsBot";

    public static void main (String [] args) {
        final TelegramBot bot = new TelegramBot(BOT_TOKEN);
//        GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);

//        GetUpdatesResponse updatesResponse = bot.execute(getUpdates);
//        SendMessage request = new SendMessage(235452433, "eggplant")
//                .parseMode(ParseMode.HTML)
//                .disableWebPagePreview(true)
//                .disableNotification(true)
//                .replyToMessageId(1)
//                .replyMarkup(new ForceReply());


        bot.setUpdatesListener(new MainListener(bot));

    }
    public MyBot (){
        this.BotToken = BOT_TOKEN;
        this.BotUsername= BOT_USERNAME;
    }

}
