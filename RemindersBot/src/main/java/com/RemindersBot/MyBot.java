package com.RemindersBot;

import com.pengrad.telegrambot.TelegramBot;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by nmiran on 03/02/2018.
 */
//@Configuration
//@EnableScheduling
@Component
public class MyBot{

    @Required
    @Setter
    public String getBotToken (){ return BotToken;}

    String BotToken;
    String BotUsername;
    public static final String BASEURL = "https://api.telegram.org/bot";
    public static String BOT_TOKEN = "726948928:AAGmPDO7HUq4gl1_tR15n8J7NedZxFEe7Ds";
    public static String BOT_USERNAME= "BillsReminderBot";
    final TelegramBot bot;


    public MyBot() {
        bot = new TelegramBot(BOT_TOKEN);
        bot.setUpdatesListener(new MainListener(bot));
    }

    public static void main (String [] args) {
        //final TelegramBot bot = new TelegramBot(BOT_TOKEN);
        //Main.scheduled();
        //bot.setUpdatesListener(new MainListener(bot));


    }

    @Scheduled(fixedRate = 1000, initialDelay = 1000)
    public void scheduled (){
        System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
    }
//    public MyBot (){
//        this.BotToken = BOT_TOKEN;
//        this.BotUsername= BOT_USERNAME;
//    }

}
