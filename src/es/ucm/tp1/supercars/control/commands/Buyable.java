
package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.control.exceptions.NotEnoughCoinsException;
import es.ucm.tp1.supercars.logic.Game;

public interface Buyable {
	public int cost();

	public default boolean buy(Game game) throws NotEnoughCoinsException {

		if ((game.getPlayerCoinsCounter() - cost()) >= 0) {

			game.subCoins(cost());

			return true;
		}

		else {
			throw new NotEnoughCoinsException("Not enough coins");
		}
	}

}
