package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Obstacle extends GameObject {

	private static final String SYMBOL = "░";
	private static final String NAME = "Obstacle";
	private static final String DETAILS = "hits car";
	private static final int LIFE = 1;
	public static int nObstacles = 0;

	public Obstacle(Game game, int x, int y) {
		super(game, x, y);
		name = NAME;
		details = DETAILS;
		hp = LIFE;
		symbol = SYMBOL;
	}

	public boolean isInPosition(int x, int y) {
		return this.x == x && this.y == y;
	}

	public static int getNumberOfObstacles() {
		return nObstacles;
	}

	@Override
	public String toString() {
		if (hp<=0) {
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
	public void onEnter() {
		nObstacles = nObstacles + 1;
	}

	@Override
	public void update() {
	}

	@Override
	public void onDelete() {
		nObstacles = nObstacles - 1;
	}

	public static String getInfo() {
		return "[" + NAME + "]" + " " + DETAILS;
	}

	public static void reset() {
		nObstacles = 0;
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

	public boolean receiveExplosion() {

		killObject();

		return true;
	}


}
