package com.noname.SpringEmailSender.command;

import com.noname.SpringEmailSender.Operation;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static final Map<Operation, Command> allKnownCommandsMap = new HashMap<>();

    static {
        allKnownCommandsMap.put(Operation.SEND_MESSAGE, new SendMessageCommand());
        allKnownCommandsMap.put(Operation.VIEW_MAILING_LIST, new ViewMailingListCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
    }

    private CommandExecutor() {
    }

    public static final void execute(Operation operation) {
        allKnownCommandsMap.get(operation).execute();
    }
}
