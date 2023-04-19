package com.noname.SpringEmailSender.command;

import com.noname.SpringEmailSender.ConsoleHelper;

public class ExitCommand implements Command{
    @Override
    public void execute() {
        ConsoleHelper.writeMessage(SendMessageCommand.getReport());
        ConsoleHelper.writeMessage("See you!");
        System.exit(0);
    }
}
