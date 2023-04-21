package com.noname.SpringEmailSender.command;

import com.noname.SpringEmailSender.ConsoleHelper;

public class SendMessageCommand implements Command {

    private static int messagesSent = 0;
    @Override
    public void execute() {
    }

    public static String getReport() {
        String emailWord = "emails";
        if (messagesSent <= 1) {
            emailWord = "email";
        }
        return String.format("You sent %d %s in this session.", messagesSent, emailWord);
    }
}
