package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.GameObjectGenerator;

public class CheatCommand extends Command {

	private static final String NAME = "cheat";

	private static final String DETAILS = "Cheat [1..5]";

	private static final String SHORTCUT = "";

	private static final String HELP = "Removes all elements of last visible column, and adds an Advanced Object";

	private static final int INI_NUM = 0;

	private static final int END_NUM = 5;

	private int id;

	public CheatCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	protected boolean matchCommandName(String name) {

		try {

			Integer.parseInt(name);
			return (Integer.parseInt(name) <= END_NUM && Integer.parseInt(name) >= INI_NUM);
		} catch (NumberFormatException excepcion) {
			return false;
		}

	}

	@Override
	public boolean execute(Game game) {

		GameObjectGenerator.forceAdvanceObject(game, id, (game.getVisibility() + game.getX() - 1));

		return true;
	}

	protected Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length != 1) {
				System.out.format("[ERROR]: Command %s: %s%n%n", NAME, INCORRECT_NUMBER_OF_ARGS_MSG);
				return null;
			} else {
				id = Integer.parseInt(words[0]);
				return this;
			}
		}
		return null;
	}
}
