package com.noname.SpringEmailSender.command;

import com.noname.SpringEmailSender.ConsoleHelper;
import com.noname.SpringEmailSender.EmailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class SendMessageCommand implements Command {
    @Autowired
    private EmailSenderService emailSenderService;
    @Override
    public void execute() {
        try {
            ConsoleHelper.writeMessage("It will take some time!\n...");
            sendMail();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void sendMail() throws MessagingException {
        int emailsCounter = emailSenderService.sendSimpleEmailForAll();
        String emailWord = "emails";
        if (emailsCounter <= 1) {
            emailWord = "email";
        }
        ConsoleHelper.writeMessage(String.format("-> %d %s successfully sent", emailsCounter, emailWord));
    }
}
