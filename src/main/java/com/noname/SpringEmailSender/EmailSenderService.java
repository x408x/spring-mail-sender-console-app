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
    private static final Path ADDRESSES_FILE_PATH = Paths.get("src/main/resources/addressees.txt");
    private static final Path SUBJECT_FILE_PATH = Paths.get("src/main/resources/subject.txt");
    private static final Path BODY_FILE_PATH = Paths.get("src/main/resources/body.txt");
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendSimpleEmailForAll() {
        List<String> addressees = getAddresses();
        Map<String, String> emailContent = getEmailContent();
        String subject = emailContent.get("Subject");
        String body = emailContent.get("Body");
        if (!(addressees.isEmpty())) {
            for (String address : addressees) {
                sendSimpleEmail(address, subject, body);
            }
        }
    }

    public Map<String, String> getEmailContent() {
        Map<String, String> result = new HashMap<>();
        StringBuilder subject = new StringBuilder();
        StringBuilder body = new StringBuilder();
        try {
            if (Files.notExists(SUBJECT_FILE_PATH)) {
                Files.createFile(SUBJECT_FILE_PATH);
            }
            if (Files.notExists(BODY_FILE_PATH)) {
                Files.createFile(BODY_FILE_PATH);
            }
            try (BufferedReader subjectReader = new BufferedReader(new FileReader(SUBJECT_FILE_PATH.toFile()));
                 BufferedReader bodyReader = new BufferedReader(new FileReader(BODY_FILE_PATH.toFile()))) {
                while (subjectReader.ready()) {
                    subject.append(subjectReader.readLine());
                }
                while (bodyReader.ready()) {
                    body.append(bodyReader.readLine());
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
            if (Files.notExists(ADDRESSES_FILE_PATH)) {
                Files.createFile(ADDRESSES_FILE_PATH);
            }
            try (BufferedReader readerAddressees = new BufferedReader(new FileReader(ADDRESSES_FILE_PATH.toFile()))) {
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
