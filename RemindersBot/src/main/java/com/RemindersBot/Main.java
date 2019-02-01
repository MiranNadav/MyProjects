package com.RemindersBot;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by nmiran on 03/02/2018.
 */
@Configuration
@EnableScheduling
public class Main {
    public static void main (String [] args){
        TelegramBot bot = new TelegramBot("450527622:AAHryh1740PjqLzURLskloeIhXv83cksrnQ");
        scheduled();
        
    }

    public static void scheduled(){
        System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);

    }

}
