package com.noname.SpringEmailSender.command;

import com.noname.SpringEmailSender.ConsoleHelper;
import com.noname.SpringEmailSender.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ViewMailingListCommand implements Command {
    @Autowired
    private EmailSenderService emailSenderService;
    @Override
    public void execute() {
        ConsoleHelper.writeAddressesList(emailSenderService
                .getAddresses());
    }
}
