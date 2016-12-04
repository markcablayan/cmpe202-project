package com.deadlock.objectholdmodel;

public class ChatModel {
    String content = "";

    public ChatModel() {
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void addContent(String content) {
        this.content = this.content + content + "\n";
    }
}
