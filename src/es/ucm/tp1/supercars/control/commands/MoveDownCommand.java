package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.control.exceptions.GameException;
import es.ucm.tp1.supercars.logic.Game;

public class MoveDownCommand extends Command {

	private static final String NAME = "go down";

	private static final String DETAILS = "[a]";

	private static final String SHORTCUT = "a";

	private static final String HELP = "go down";

	public MoveDownCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		game.playerCollision();
		if (game.canPlayerMoveDown()) {
			game.goDown();
			game.advance();
			game.update();

		} else {

			game.advance();
			game.update();
		}
		return true;
	}

	@Override
	protected Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length > 1) {
				throw new CommandParseException(
						String.format("[ERROR]:Command %s: %s", NAME, INCORRECT_NUMBER_OF_ARGS_MSG));
			} else {
				return this;
			}
		}
		return null;
	}
}
