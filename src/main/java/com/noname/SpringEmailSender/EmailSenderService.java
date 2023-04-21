package com.noname.SpringEmailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
        ConsoleHelper.writeMessage("Message was send!");
    }

    public Map<String, String> getEmailContent() {
        Map<String, String> result = new HashMap<>();
        StringBuilder subject = new StringBuilder();
        StringBuilder body = new StringBuilder();
        try {
            Path pathText = Paths.get("src/main/resources/text.txt");
            if (Files.notExists(pathText)) {
                Files.createFile(pathText);
            }
            Path pathSubject = Paths.get("src/main/resources/subject.txt");
            if (Files.notExists(pathSubject)) {
                Files.createFile(pathSubject);
            }
            try (BufferedReader readerSubject = new BufferedReader(new FileReader(pathSubject.toFile()));
                 BufferedReader readerText = new BufferedReader(new FileReader(pathText.toFile()))) {
                while (readerSubject.ready()) {
                    subject.append(readerSubject.readLine());
                }
                while (readerText.ready()) {
                    body.append(readerText.readLine());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (subject.isEmpty()) {
            subject.append("(No subject)");
        }
        if (body.isEmpty()) {
            body.append("(No text)");
        }
        result.put("Subject", subject.toString());
        result.put("Body", body.toString());
        return result;
    }

    public List<String> getAddresses() {
        List<String> result = new ArrayList<>();
        try {
            Path pathAddressees = Paths.get("src/main/resources/addressees.txt");
            if (Files.notExists(pathAddressees)) {
                Files.createFile(pathAddressees);
            }
            try (BufferedReader readerAddressees = new BufferedReader(new FileReader(pathAddressees.toFile()))) {
                while (readerAddressees.ready()) {
                    result.add(readerAddressees.readLine());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
