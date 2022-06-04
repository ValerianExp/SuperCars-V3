package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.control.exceptions.InvalidPositionException;
import es.ucm.tp1.supercars.control.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.Grenade;

public class GrenadeCommand extends Command implements Buyable {

	private static final String NAME = "grenade";

	private static final String DETAILS = "[g]renade <x> <y>";

	private static final String SHORTCUT = "g";

	private static final String HELP = "add a grenade in position x, y";

	private static final int PENALITATION = 3;

	private static final String FAILED_MSG = "Failed to add grenade";

	private int x;

	private int y;

	public GrenadeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			if (game.tryToAddGrenade(new Grenade(game, x + game.getX(), y)) && buy(game))
				game.update();
			else {
				throw new InvalidPositionException("Invalid position. \n"
						+ String.format("[ERROR]: %s", FAILED_MSG));
			}
		} catch (NotEnoughCoinsException e) {
			System.out.println(e.getMessage());
			throw new CommandExecuteException(String.format("[ERROR]: %s", FAILED_MSG, e));
		}

		return true;
	}

	@Override
	protected Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {

			if (words.length == 3) {
				x = Integer.parseInt(words[1]);
				y = Integer.parseInt(words[2]);
				return this;
			} else if (words.length != 3) {
				//[ERROR]: Incorrect number of arguments for grenade command: [g]renade <x> <y>
				throw new CommandParseException(
						"[ERROR]: " + INCORRECT_NUMBER_OF_ARGS_MSG + " for " + NAME + " commmand: " + DETAILS);
				//System.out.format("[ERROR]: Command %s: %s%n%n", NAME, INCORRECT_NUMBER_OF_ARGS_MSG);
				//return null;
			}
		}
		return null;
	}

	@Override
	public int cost() {
		return PENALITATION;
	}

}
