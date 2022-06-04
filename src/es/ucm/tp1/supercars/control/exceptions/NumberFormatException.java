package es.ucm.tp1.supercars.control.exceptions;

public class NumberFormatException extends CommandParseException {

    public NumberFormatException() {
        super();
    }

    public NumberFormatException(String message) {
        super(message);
    }

    public NumberFormatException(Throwable cause) {
        super(cause);
    }

    public NumberFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public NumberFormatException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
