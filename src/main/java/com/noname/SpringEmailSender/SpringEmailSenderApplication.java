package com.noname.SpringEmailSender;

import com.noname.SpringEmailSender.command.CommandExecutor;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
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
	private CommandExecutor commandExecutor;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringEmailSenderApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void mainMenu() {
		while (true) {
			commandExecutor.execute(ConsoleHelper.askOperation());
		}
	}
}
