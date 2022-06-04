package es.ucm.tp1.supercars.control.exceptions;

public class CommandExecuteException extends GameException {
	
	public CommandExecuteException() {
		super();
	}
	public CommandExecuteException(String message) {
		super(message);
	}
	public CommandExecuteException(Throwable cause) {
		super(cause);
	}
	public CommandExecuteException(String message, Throwable cause) {
		super (message,cause);
	}


}
