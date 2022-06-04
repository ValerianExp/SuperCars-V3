package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;

public class Player extends GameObject {
	private static final String NAME = "Car";
	private static final String DETAILS = "the racing car";
	private static final String SYMBOL = ">";
	private static final String DEAD_SYMBOL = "@";
	private static final int INITIAL_COINS = 5;
	private int LIFE = 1;
	private int nCoins;

	public Player(Game game, int x, int y) {

		super(game, x, y);
		this.nCoins = INITIAL_COINS;
		name = NAME;
		details = DETAILS;
		hp = LIFE;
		alive = true;
		symbol = toString();
	}

	public static int getInitialCoins() {
		return INITIAL_COINS;
	}

	public void increaseCoinCounter(int coin) {
		nCoins += coin;
	}

	public void decreaseCoins(int coins) {
		nCoins -= coins;
	}
	
	@Override
	public String toString() {
		if (isAlive())
			return SYMBOL;
		return DEAD_SYMBOL;
	}

	public static String getInfo() {
		return "[" + NAME + "]" + " " + DETAILS;
	}

	public void advance() {
		x++;
	}

	public void goUp() {
		y--;
	}

	public void goDown() {
		y++;
	}

	public boolean hasArrived() {
		return (game.getRoadLength() - getX() + 1 == 0);
	}

	public boolean doCollision() {
		Collider other = game.getObjectInPosition(x, y);
		if (other != null) {
			return other.receiveCollision(this);
		}
		return false;
	}

	@Override
	public boolean receiveCollision(Player player) {
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

	public void crashDamage() {
		hp--;
	}

	@Override
	public boolean receiveShoot() {

		return false;
	}

	public int getnCoins() {
		return nCoins;
	}

	@Override
	public boolean receiveWave() {
		return false;
	}

	@Override
	public boolean receiveExplosion() {

		return false;
	}

	@Override
	public boolean receiveThunder() {
		return false;
	}

}
