package com.noname.SpringEmailSender.command;

import com.noname.SpringEmailSender.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
@Scope("singleton")
public class CommandExecutor {
    private final Map<Operation, Command> allKnownCommandsMap = new HashMap<>();

    @Autowired
    private SendMessageCommand sendMessageCommand;
    @Autowired
    private ViewMailingListCommand viewMailingListCommand;
    @Autowired
    private ExitCommand exitCommand;
    private CommandExecutor() {
    }

    public final void execute(Operation operation) {
        if (allKnownCommandsMap.isEmpty()) {
            allKnownCommandsMap.put(Operation.SEND_MESSAGE, sendMessageCommand);
            allKnownCommandsMap.put(Operation.VIEW_MAILING_LIST, viewMailingListCommand);
            allKnownCommandsMap.put(Operation.EXIT, exitCommand);
        }
        allKnownCommandsMap.get(operation).execute();
    }
}
