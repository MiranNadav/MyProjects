import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

/**
 * Created by nmiran on 05/02/2018.
 */
public class AudioMessageProcessor {

    public SendMessage process(Message message) {
        SendMessage request;
        if (message.voice().duration() < 10) {
            request = new SendMessage(message.chat().id(), "This audio is too short!! btw, I don't handle audio yet");
        } else {
            request = new SendMessage(message.chat().id(), "This audio is too long!! btw, I don't handle audio yet");
        }
        return request;
    }
}
