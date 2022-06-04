package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.actions.ExplosionAction;

public class Grenade extends GameObject {

	private static final String SYMBOL = "ð";
	private static final String NAME = "GRENADE";
	private static final String DETAILS = "Explodes in 3 cycles, harming everyone around";
	private int timeExplosion;
	private final int LIFE = 4;

	public Grenade(Game game, int x, int y) {
		super(game, x, y);
		symbol = SYMBOL;
		hp = LIFE;
		name = NAME;
		details = DETAILS;
		timeExplosion = 4;
	}

	public boolean isInPosition(int x, int y) {
		return this.x == x && this.y == y;
	}

	@Override
	public String toString() {

		if (hp == 0) {
			return "";
		}

		return SYMBOL + "[" + hp  + "]";
	}

	@Override
	public boolean receiveCollision(Player player) {
		game.playerCrash();
		return false;
	}

	@Override
	public void onEnter() {
	}

	@Override
	public void update() {
//		timeExplosion--;
		hp--;
		if (hp == 0) {
			game.execute(new ExplosionAction(x, y));
			
		}
	}

	public static String getInfo() {
		return "[" + NAME + "]" + " " + DETAILS;
	}

	@Override
	public void onDelete() {
	}

	public static void reset() {

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

	@Override
	public boolean receiveExplosion() {

		return false;
	}

	public boolean receiveThunder() {

		return false;
	}
	
	@Override
	public String serialize() {
		return symbol + "[" + hp + "]" + "(" + x + "," +  y + ")";
	}

}
