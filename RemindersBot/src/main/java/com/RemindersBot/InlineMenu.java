package com.RemindersBot;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;

/**
 * Created by nmiran on 05/02/2018.
 */
public class InlineMenu {

//    public SendMessage process (Message message, TelegramBot bot) {
//        InlineKeyboardButton[][] buttons = new InlineKeyboardButton[2][2];
//        buttons[0][0] = new InlineKeyboardButton("Top-Left");
//        buttons[0][1] = new InlineKeyboardButton("Top-Right");
//        buttons[1][0] = new InlineKeyboardButton("Bottom-Left");
//        buttons[1][1] = new InlineKeyboardButton("Bottom-Right");
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(buttons);
//        SendMessage m = new SendMessage(message.chat().id(),"");
//        return new SendMessage(message.chat().id(),"My Menu");
//
//    }

    public SendMessage process (Message message){
        InlineKeyboardButton[][] buttons = new InlineKeyboardButton[1][1];
        buttons[0] = new InlineKeyboardButton[] {new InlineKeyboardButton("Top-Left").callbackData("search")};
//        buttons[0][1] = new InlineKeyboardButton("Top-Right");
//        buttons[1][0] = new InlineKeyboardButton("Bottom-Left");
//        buttons[1][1] = new InlineKeyboardButton("Bottom-Right");
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(buttons);
        SendMessage replyMessage = new SendMessage(message.chat().id(), "First Menu").replyMarkup(inlineKeyboardMarkup);
//        replyMessage.replyMarkup(inlineKeyboardMarkup);
        return replyMessage;
    }

}
