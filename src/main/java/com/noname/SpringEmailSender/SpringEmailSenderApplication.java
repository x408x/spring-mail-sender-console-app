package com.noname.SpringEmailSender;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class SpringEmailSenderApplication {
	@Autowired
	private EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(SpringEmailSenderApplication.class, args);
		/*
        while (true) {
            CommandExecutor.execute(ConsoleHelper.askOperation());
        }
        */
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendMail() throws MessagingException {
		senderService.sendSimpleEmailForAll();
		ConsoleHelper.writeMessage("Messages were send!");
	}
}
