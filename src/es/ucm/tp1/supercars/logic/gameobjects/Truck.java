package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Truck extends Obstacle {

	// ←
	private static final String SYMBOL = "←";
	private static final String NAME = "TRUCK";
	private static final String DETAILS = "moves towards the player";
	private static final int LIFE = 1;

	public Truck(Game game, int x, int y) {
		super(game, x, y);
		hp = LIFE;
		symbol = SYMBOL;
		name = NAME;
		details = DETAILS;
	}

	public String toString() {
		if (hp <= 0) {
			return "";
		}
		return SYMBOL;
	}

	@Override
	public boolean receiveCollision(Player player) {
		game.playerCrash();
		return false;
	}

	@Override
	public void update() {
		this.x--;
		//		game.playerCollision();

	}

	public static String getInfo() {
		return "[" + NAME + "]" + " " + DETAILS;
	}

	@Override
	public boolean receiveShoot() {
		hp--;
		return true;
	}

	@Override
	public boolean receiveWave() {
		this.x++;
		return true;
	}

	public boolean receiveThunder() {
		
		killObject();
		return true;
	}

}
