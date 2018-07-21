import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

/**
 * Created by nmiran on 04/02/2018.
 */
public class HiMessageProcessor {

    public SendMessage process(Message message) {
            SendMessage request = new SendMessage(message.chat().id(), "bye")
                    .parseMode(ParseMode.HTML)
                    .disableWebPagePreview(true)
                    .disableNotification(true);
            return request;
    }
}
