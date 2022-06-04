package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Coin extends GameObject {

	private static String SYMBOL = "¢";
	private static final String NAME = "Coin";
	private static final String DETAILS = "gives 1 coin to the player";
	private static final int VALUE = 1;
	private static final int LIFE = 1;
	public static int nCoins = 0;

	public Coin(Game game, int x, int y) {
		super(game, x, y);
		name = NAME;
		details = DETAILS;
		alive = true;
		hp = LIFE;
		symbol = SYMBOL;
	}

	public static int getNumberOfCoins() {
		return nCoins;
	}

	@Override
	public String toString() {
		if (hp <= 0) {
			return "";
		}
		return SYMBOL;
	}

	@Override
	public boolean receiveCollision(Player player) {
		game.increaseCoinCounter(VALUE);
		hp--;
		return false;
	}

	@Override
	public void onEnter() {
		nCoins = nCoins + 1;
	}

	@Override
	public void onDelete() {
		nCoins--;
	}

	@Override
	public void update() {
	}

	public static void reset() {
		nCoins = 0;
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

	public boolean receiveExplosion() {
		return false;
	}


}
