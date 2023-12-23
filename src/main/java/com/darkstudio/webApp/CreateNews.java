package com.darkstudio.webApp;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Objects;

public class CreateNews {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting");

        String day = LocalDate.now().getDayOfMonth() < 10 ? "0"+LocalDate.now().getDayOfMonth(): String.valueOf(LocalDate.now().getDayOfMonth());
        String month = LocalDate.now().getMonthValue() < 10 ? "0"+LocalDate.now().getMonthValue(): String.valueOf(LocalDate.now().getMonthValue());

        JSONObject jsonObject = new JSONObject();
        String date = day + "." + month + "." + LocalDate.now().getYear();

        String caption = "Начало";

        jsonObject.put("title", caption + " (" + date + ")");
        jsonObject.put("text", "Это начало сайта Messenger. Здесь будет показана вся информация о продукте.\n" +
                "Здесь будут выкладывать новости о продукте");

        int filesCount = Objects.requireNonNull(new File("texts/news").listFiles()).length;
        if(filesCount == 0) filesCount++;
        filesCount++;

        PrintWriter printWriter = new PrintWriter("texts/news/news_"+filesCount+".json");
        jsonObject.writeJSONString(printWriter);
        printWriter.close();
        
        System.out.println("Done");
    }
}
