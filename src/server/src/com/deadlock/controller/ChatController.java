package com.deadlock.controller;

import com.deadlock.objectholdmodel.ChatModel;

public class ChatController {
    ChatModel chatModel = new ChatModel();
    private static ChatController controller;

    public ChatController() {
    }

    public String getChatContent() {
        return this.chatModel.getContent();
    }

    public void addChatContent(String content) {
        this.chatModel.addContent(content);
    }

    public static ChatController getInstance() {
        if(controller == null) {
            controller = new ChatController();
        }

        return controller;
    }
}
