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
		List<String> addressees = new ArrayList<>();
		StringBuilder subject = new StringBuilder();
		StringBuilder text = new StringBuilder();
		try {
			Path pathAddressees = Paths.get("src/main/resources/addressees.txt");
			if (Files.notExists(pathAddressees)) {
				Files.createFile(pathAddressees);
			}
			Path pathText = Paths.get("src/main/resources/text.txt");
			if (Files.notExists(pathText)) {
				Files.createFile(pathText);
			}
			Path pathSubject = Paths.get("src/main/resources/subject.txt");
			if (Files.notExists(pathSubject)) {
				Files.createFile(pathSubject);
			}

			try (BufferedReader readerAddressees = new BufferedReader(new FileReader(pathAddressees.toFile()));
				 BufferedReader readerSubject = new BufferedReader(new FileReader(pathSubject.toFile()));
				 BufferedReader readerText = new BufferedReader(new FileReader(pathText.toFile()))) {

				while (readerAddressees.ready()) {
					addressees.add(readerAddressees.readLine());
				}

				while (readerSubject.ready()) {
					subject.append(readerSubject.readLine());
				}

				while (readerText.ready()) {
					text.append(readerText.readLine());
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if (!(addressees.isEmpty())) {
			if (subject.isEmpty()) {
				subject.append("(No subject)");
			}
			if (text.isEmpty()) {
				text.append("(No text)");
			}
			for (String address : addressees) {
				senderService.sendSimpleEmail(address, subject.toString(), text.toString());
			}
		}
	}
}
