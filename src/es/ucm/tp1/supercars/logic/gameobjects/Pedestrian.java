package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;


public class Pedestrian extends Obstacle {
	
	private static final String SYMBOL = "☺";
	private static final String NAME = "PEDESTRIAN";
	private static final String DETAILS = "person crossing the road up and down";
	private boolean bajar;
	private static final int LIFE = 1;

	public Pedestrian(Game game, int x, int y) {
		super(game, x, y);
		bajar = true;
		name = NAME;
		details = DETAILS;
		hp = LIFE;
		symbol = SYMBOL;
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
		player.decreaseCoins(player.getnCoins());
		killObject();
		return false;
	}

	@Override
	public void update() {

		if (this.getY() == 2) {
			bajar = false;
		} else if (this.getY() == 0) {
			bajar = true;
		}

		if (bajar && this.getY() != 2) {
			this.y++;
		} else if (!bajar && this.getY() != 0) {
			this.y--;
		}

	}

	public static String getInfo() {
		return "[" + NAME + "]" + " " + DETAILS;
	}

	@Override
	public boolean receiveShoot() {
		hp--;
		game.removeAllCoin();
		return true;
	}

	@Override
	public boolean receiveWave() {
		this.x++;
		return true;
	}

}
