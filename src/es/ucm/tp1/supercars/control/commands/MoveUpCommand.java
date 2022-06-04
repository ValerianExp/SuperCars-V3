package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.Game;

public class MoveUpCommand extends Command {

	private static final String NAME = "up";

	private static final String DETAILS = "[q]";

	private static final String SHORTCUT = "q";

	private static final String HELP = "go up";

	public MoveUpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) {
		game.playerCollision();
		if (game.canPlayerMoveUp()) {
			game.goUp();
			game.advance();
			game.update();
		} else {
			game.advance();
			game.update();
		}
		return true;
	}

}
