package com.noname.SpringEmailSender;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringEmailSenderApplication {
	@Autowired
	private EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(SpringEmailSenderApplication.class, args);
		/*
        while (true) {
            CommandExecutor.execute(ConsoleHelper.askOperation(0));
        }
        */
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendMail() throws MessagingException {
		senderService.sendSimpleEmail("thehitman2703@gmail.com", "test", "test");
	}
}
