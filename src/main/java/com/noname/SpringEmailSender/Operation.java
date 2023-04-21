package com.noname.SpringEmailSender;

public enum Operation {
    SEND_MESSAGE,
    VIEW_MAILING_LIST,
    EXIT;

    // ID #0 = main menu
    // ID #1 = message type menu
    public static Operation getAllowableOperationByOrdinal(Integer i) {
        switch (i) {
            case 1:
                return Operation.SEND_MESSAGE;
            case 2:
                return Operation.VIEW_MAILING_LIST;
            case 3:
                return Operation.EXIT;
            default:
                throw new IllegalArgumentException();
        }
    }
}
