package com.noname.SpringEmailSender.command;

import com.noname.SpringEmailSender.Operation;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static final Map<Operation, Command> allKnownCommandsMap = new HashMap<>();

    static {
        allKnownCommandsMap.put(Operation.SEND_MESSAGE, new SendMessageCommand());
        allKnownCommandsMap.put(Operation.VIEW_MAILING_LIST, new ViewMailingListCommand());
        allKnownCommandsMap.put(Operation.UPDATE_PROPERTIES, new UpdatePropertiesCommand());
        allKnownCommandsMap.put(Operation.EXIT, new ExitCommand());
        allKnownCommandsMap.put(Operation.MESSAGE_WITH_ATTACHMENTS, new MessageWithAttachmentsCommand());
        allKnownCommandsMap.put(Operation.TEXT_MESSAGE, new TextMessageCommand());
        allKnownCommandsMap.put(Operation.CANCEL, new CancelCommand());
    }

    private CommandExecutor() {
    }

    public static final void execute(Operation operation) {
        allKnownCommandsMap.get(operation).execute();
    }
}
