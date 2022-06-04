package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Collider;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.InstantAction;

public class ThunderAction implements InstantAction {

	@Override
	public void execute(Game game) {

		int x = game.getRandomColum();
		int y = game.getRandomLane();
		boolean hit = false;

		Collider other = game.getObjectInPosition(x + game.getPlayerX(), y);
		String objString = "";

		if (other != null) {
			objString = other.toString();
			hit = other.receiveThunder();

		}

		String line = "Thunder hit position: (" + (x) + " , " + y + ")";

		if (hit) {
			line += " -> " + objString;
		}

		if (!game.isFinished()) {
			System.out.println(line);
		}
	}

}
