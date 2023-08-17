package org.example;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, TelegramApiException {
        new MyTelegramBot("MyNASAbot", "6349843446:AAEygkvAaEC4HDrsuKb3BcRU82KoLNmPJp4", "https://api.nasa.gov/planetary/apod?api_key=AdVVzjVlNwHokJT6Sv1ANSbg3AqGiB0hcFB8I06d");
    }
}