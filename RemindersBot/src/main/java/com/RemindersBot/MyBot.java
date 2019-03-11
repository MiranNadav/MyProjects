package com.RemindersBot;

import com.pengrad.telegrambot.TelegramBot;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by nmiran on 03/02/2018.
 */
//@Configuration
//@EnableScheduling
@Component
public class MyBot {

    @Required
    @Setter
    public String getBotToken() {
        return BotToken;
    }

    String BotToken;
    String BotUsername;
    public static final String BASEURL = "https://api.telegram.org/bot";
    public static String BOT_TOKEN = "726948928:AAGmPDO7HUq4gl1_tR15n8J7NedZxFEe7Ds";
    public static String BOT_USERNAME = "BillsReminderBot";
    final TelegramBot bot;


    public MyBot() {
        bot = new TelegramBot(BOT_TOKEN);
        bot.setUpdatesListener(new MainListener(bot));
    }

    public static void main(String[] args) {
        MyBot mybot = new MyBot();
        mybot.Start();
        //final TelegramBot bot = new TelegramBot(BOT_TOKEN);
        //Main.scheduled();
        //bot.setUpdatesListener(new MainListener(bot));


    }

    public void Start() {
        Timer timer = new Timer();
        String sDate1 = "02/02/2019 01:31:20";
        String sDate2 = "02/02/2019 01:31:21";
        SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try{
            Date date1 = formatter.parse(sDate1);
            Date date2 = formatter.parse(sDate2);
            timer.schedule(new MyTask(),date1);
            timer.schedule(new MyTask(), date2);
        }
        catch (Exception e){
        }


//        timer.schedule(new MyTask(),Date.from(Instant.now().plusSeconds(5)));
    }
//    @Scheduled(fixedRate = 1000, initialDelay = 1000)
    public void scheduled() {
        System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
    }
//    public MyBot (){
//        this.BotToken = BOT_TOKEN;
//        this.BotUsername= BOT_USERNAME;
//    }

    public class MyTask extends TimerTask {

        public void run() {
            System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
        }
    }
}
