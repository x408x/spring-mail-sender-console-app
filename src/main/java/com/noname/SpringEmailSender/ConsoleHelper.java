package com.noname.SpringEmailSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static final String OPERATIONS = "1) Send message\n2) View mailing list\n3) Exit";
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

    public static Operation askOperation() {
        while (true) {
            writeMessage("----------------------------------------------------------------------------------");
            writeMessage(SELECT_NUMBER);
            writeMessage(OPERATIONS);
            String iString = ConsoleHelper.readString().trim().toUpperCase();
            if (iString.equals("EXIT") || iString.equals("CLOSE") || iString.equals("CANCEL")) {
                return Operation.EXIT;
            }
            try {
                Integer i = Integer.parseInt(iString);
                return Operation.getAllowableOperationByOrdinal(i);
            } catch (IllegalArgumentException e) {
                ConsoleHelper.writeMessage("Please specify valid data.");
            }
        }
    }

    public static void writeAddressesList(List<String> addresses) {
        for (String address : addresses) {
            writeMessage(address);
        }
    }
}
