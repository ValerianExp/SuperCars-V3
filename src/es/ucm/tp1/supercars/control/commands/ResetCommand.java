package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.Level;

public class ResetCommand extends Command {

	private static final String NAME = "reset game";

	private static final String DETAILS = "[r]eset [<level> <seed>]";

	private static final String SHORTCUT = "r";

	private static final String HELP = "reset game";

	private long seed;
	private Level level;

	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {

		game.reset(level, seed);
		if (level != null) {
			System.out.println("Level: " + level.name());
			System.out.println("Random generator initialized with seed: " + seed);
		}
		return true;
	}

	@Override
	protected Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length == 3) {
				if (Level.valueOfIgnoreCase(words[1]) != null) {
					try {
						seed = Long.parseLong(words[2]);
						level = Level.valueOfIgnoreCase(words[1]);
						return this;

					} catch (NumberFormatException nfe) {
						throw new CommandParseException("[ERROR] : no numero");
					}
				} else {
					// System.out.println("[ERROR]: Command r: Level must be one of: " + Level.all(", ") + "\n");
					// return null;
					throw new CommandParseException("[ERROR]: Command r: Level must be one of: " + Level.all(", "));
				}
			} else if (words.length == 1) {
				return super.parse(words);
			} else if (words.length >= 3) {
				throw new CommandParseException("[ERROR]: Command " + words[0] + " " + INCORRECT_NUMBER_OF_ARGS_MSG);
			}
		}
		return null;
	}
}