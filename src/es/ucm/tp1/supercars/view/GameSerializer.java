package es.ucm.tp1.supercars.view;

import es.ucm.tp1.supercars.logic.Game;

public class GameSerializer {
	private Game game;

	public GameSerializer(Game game) {
		this.game = game;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int y = 0; y < game.getRoadLength(); y++) {
			for (int x = 0; x < game.getRoadWidth(); x++) {
				str.append(game.positionToSerialize(y, x));
			}
		}
		return str.toString();
	}
}
