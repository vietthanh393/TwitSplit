package com.example.twitsplit.chat.model;

public class Message {
    private String content;

    public Message(final String message) {
        this.content = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
