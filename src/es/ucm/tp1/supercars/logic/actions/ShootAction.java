package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.InstantAction;

public class ShootAction implements InstantAction {

	@Override
	public void execute(Game game) {
		
		for (int i = 0; i < game.getVisibility(); i++) {

			Collider other = game.getObjectInPosition(game.getX() + i, game.getY());
			if (other != null) {
				if (other.receiveShoot())
				break;
			}

		}
		
	}

}
