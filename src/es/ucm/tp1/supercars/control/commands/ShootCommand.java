package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.logic.actions.ShootAction;
import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.control.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.supercars.logic.Game;

public class ShootCommand extends Command implements Buyable {

	private static final String NAME = "Shoot";

	private static final String DETAILS = "[s]hoot";

	private static final String SHORTCUT = "s";

	private static final String HELP = "shoot bullet";

	private static final String FAILED_MSG = "Failed to shoot";

	private final int PENALITATION = 1;

	public ShootCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			if (buy(game)) {
				game.execute(new ShootAction());
				game.update();
				return true;
			}
			return false;
		} catch (NotEnoughCoinsException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: %s", FAILED_MSG, e));
		}
	}

	@Override
	public int cost() {
		return PENALITATION;
	}

}