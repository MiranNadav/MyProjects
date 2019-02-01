package com.RemindersBot;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;

public class SearchMessageProcessor {

    public SendMessage process(Message message) {
        SendMessage request = new SendMessage(message.chat().id(), "Do you really want to search?")
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true)
                .disableNotification(true);
        return request;
    }
}
