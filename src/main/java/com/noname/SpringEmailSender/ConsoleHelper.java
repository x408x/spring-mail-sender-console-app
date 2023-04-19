package com.noname.SpringEmailSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final String OPERATIONS0 = "1) Send message\n2) View mailing list\n3) Update properties\n4) Exit";
    public static final String OPERATIONS1 = "1) Simple text email\n2) Message with attachments\n3) Cancel";
    private static final String SELECT_NUMBER = "Select number:";

    private ConsoleHelper(){}

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        try {
            return reader.readLine();
        } catch (IOException ignored) {}
        return null;
    }

    public static Operation askOperation(int menuId) {
        while (true) {
            writeMessage(SELECT_NUMBER);
            switch (menuId) {
                case 0:
                    writeMessage(OPERATIONS0);
                    break;
                case 1:
                    writeMessage(OPERATIONS1);
                    break;
            }
            String iString = ConsoleHelper.readString().trim().toUpperCase();
            if (iString.equals("CANCEL")) {
                if (menuId == 0) {
                    return Operation.EXIT;
                }
                return Operation.CANCEL;
            }
            if (iString.equals("EXIT") || iString.equals("CLOSE")) {
                return Operation.EXIT;
            }
            try {
                Integer i = Integer.parseInt(iString);
                return Operation.getAllowableOperationByOrdinal(i, menuId);
            } catch (IllegalArgumentException e) {
                ConsoleHelper.writeMessage("Please specify valid data.");
            }
        }
    }
}
