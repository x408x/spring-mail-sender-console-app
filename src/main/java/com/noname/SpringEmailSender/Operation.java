package com.noname.SpringEmailSender;

public enum Operation {
    SEND_MESSAGE,
    VIEW_MAILING_LIST,
    UPDATE_PROPERTIES,
    EXIT,
    TEXT_MESSAGE,
    MESSAGE_WITH_ATTACHMENTS,
    CANCEL;

    // ID #0 = main menu
    // ID #1 = message type menu
    public static Operation getAllowableOperationByOrdinal(Integer i, int menuId) {
        switch (menuId) {
            case 0:
                switch (i) {
                    case 1:
                        return Operation.SEND_MESSAGE;
                    case 2:
                        return Operation.VIEW_MAILING_LIST;
                    case 3:
                        return Operation.UPDATE_PROPERTIES;
                    case 4:
                        return Operation.EXIT;
                    default:
                        throw new IllegalArgumentException();
                }
            case 1:
                switch (i) {
                    case 1:
                        return Operation.TEXT_MESSAGE;
                    case 2:
                        return Operation.MESSAGE_WITH_ATTACHMENTS;
                    case 3:
                        return Operation.CANCEL;
                    default:
                        throw new IllegalArgumentException();
                }
            default:
                throw new IllegalArgumentException();
        }
    }
}
