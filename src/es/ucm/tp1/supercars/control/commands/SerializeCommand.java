package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.logic.Game;

public class SerializeCommand extends Command {

	private static final String NAME = "serialize";

	private static final String DETAILS = "[z]";

	private static final String SHORTCUT = "z";

	private static final String HELP = "";

	public SerializeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		game.serialize(game);
		System.out.println(game.serializerToString());
		return false;
	}

}
