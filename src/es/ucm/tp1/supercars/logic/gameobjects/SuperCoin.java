package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class SuperCoin extends Coin {

	private static String SYMBOL = "$";
	private static String NAME = "SUPERCOIN";
	private static String DETAILS = "gives 1000 coins";
	private static final int VALUE = 1000;
	private static final int LIFE = 1;
	private static boolean exists= false;
	

	public SuperCoin(Game game, int x, int y) {
		super(game, x, y);
		alive = true;
		hp = LIFE;
		symbol = SYMBOL;
		name=NAME;
		details=DETAILS;
		
		
		
	}
	
	public String toString() {
		if(hp<=0) {
			return "";
		}
		return SYMBOL;
	}
	
	@Override
	public void onEnter() {
		
		exists=true;
		
	}

	@Override
	public void onDelete() {
		
		exists=false;
		
	}


	public static boolean hasSuperCoin() {
		
		return exists;
		
	}
	

	public static String getInfo() {
        return "[" + NAME + "]" + " " + DETAILS;
    }
	@Override
	public boolean receiveCollision(Player player) {
		game.increaseCoinCounter(VALUE);
		hp--;
		return false;
	}


}
