package com.denchik.demo.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import com.denchik.demo.utils.Emojis;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Locale;

@Service
public class ReplyMessagesService {

    private LocaleMessageService localeMessageService;

    public ReplyMessagesService(LocaleMessageService messageService) {
        this.localeMessageService = messageService;
    }

    public SendMessage getReplyMessage(long chatId, String replyMessage) {
        return new SendMessage(chatId, localeMessageService.getMessage(replyMessage));
    }

    public SendMessage getReplyMessage(long chatId, String replyMessage, Object... args) {
        return new SendMessage(chatId, localeMessageService.getMessage(replyMessage, args));
    }

    public SendMessage getWarningReplyMessage(long chatId, String replyMessage) {
        return new SendMessage(chatId, getEmojiReplyText(replyMessage, Emojis.WARNING));
    }
    public void setLocaleMessageService(String localeTag) {
        localeMessageService.setLocale(Locale.forLanguageTag(localeTag));
    }
   

    public AnswerCallbackQuery getPopUpAnswer(String callbackId, String text) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackId);
        answerCallbackQuery.setText(text);
        answerCallbackQuery.setShowAlert(false);
        return answerCallbackQuery;
    }
    public String getReplyText(String replyText) {
        return localeMessageService.getMessage(replyText);
    }

    public String getReplyText(String replyText, Object... args) {
        return localeMessageService.getMessage(replyText, args);
    }

    public String getEmojiReplyText(String replyText, Emojis emoji) {
        return localeMessageService.getMessage(replyText, emoji);
    }
}
