package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Turbo extends GameObject {

	private static final String SYMBOL = ">>>";
	private static final String NAME = "TURBO";
	private static final String DETAILS = "pushes the car: 3 columns";
	private static final int LIFE = 1;

	public Turbo(Game game, int x, int y) {
		super(game, x, y);
		name = NAME;
		details = DETAILS;
		symbol = SYMBOL;
		hp = LIFE;
	}

	public String toString() {
		if (hp == 0) {
			return "";
		}
		return SYMBOL;
	}

	@Override
	public boolean receiveCollision(Player player) {
		for (int i = 0; i < 3; i++) {
			player.advance();
		}
//		player.doCollision();
		return false;
	}

	@Override
	public void onEnter() {

	}

	@Override
	public void update() {

	}

	@Override
	public void onDelete() {

	}

	public static String getInfo() {
		return "[" + NAME + "]" + " " + DETAILS;
	}

	@Override
	public boolean receiveShoot() {

		return false;
	}

	@Override
	public boolean receiveWave() {
		this.x++;
		return true;
	}

	public boolean receiveThunder() {
		return false;
	}

}
