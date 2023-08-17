package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

public class MyTelegramBot extends TelegramLongPollingBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;
    private final String URL;

    public MyTelegramBot(String botName, String botToken, String url) throws TelegramApiException {
        BOT_NAME = botName;
        BOT_TOKEN = botToken;
        URL = url;
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            String[] separatedAnswer = update.getMessage().getText().split(" ");
            long chatId = update.getMessage().getChatId();
            String action= separatedAnswer[0];
            switch (action){
                case "/help":
                    try {
                        sendMessage("Привет, я бот NASA, введи /start, чтобы получить картинку дня", chatId);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "/start":
                    try {
                        String url = Utils.getURL(URL);
                        sendMessage(url, chatId);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                case "/give":
                    try {
                        String url = Utils.getURL(URL);
                        sendMessage(url, chatId);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                case "/date":
                    try {

                        String url = Utils.getURL(URL + "&date=" + separatedAnswer[1]);
                        sendMessage(url, chatId);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    try {
                        sendMessage("Я не понимаю твоей команды", chatId);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
            }
        }
    }

    private void sendMessage(String msg, long ChatId) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setText(msg);
        message.setChatId(ChatId);
        execute(message);
    }

    @Override
    public String getBotUsername() {
        // TODO
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        // TODO
        return BOT_TOKEN;
    }
}
