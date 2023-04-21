package com.noname.SpringEmailSender.command;

import com.noname.SpringEmailSender.ConsoleHelper;
import com.noname.SpringEmailSender.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ExitCommand implements Command{
    @Autowired
    private EmailSenderService emailSenderService;
    @Override
    public void execute() {
        ConsoleHelper.writeMessage(emailSenderService.getReport());
        ConsoleHelper.writeMessage("See you!");
        System.exit(0);
    }
}
