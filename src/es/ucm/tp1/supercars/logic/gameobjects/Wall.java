package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Game;

public class Wall extends Obstacle {
	
    private static final String NAME = "WALL";
    private static final String DETAILS = "hard obstacle";
    private static final int LIFE=3;
    private static final int VALUE=5;

    public Wall(Game game, int x, int y) {
        super(game, x, y);
        hp = LIFE;
        name=NAME;
        details=DETAILS;
    }

    public static String getInfo() {
        return "[" + NAME + "]" + " " + DETAILS;
    }

    @Override
    public String toString() {
        if (hp==3) {
        	return "█";
        }
        else if (hp==2) {
        	return "▒";
        }
        else if (hp==1) {
        	return "░";
        }
        
        return " ";
    }
    @Override
	public void onDelete() {
    	game.increaseCoinCounter(VALUE);
		nObstacles = nObstacles - 1;
		
	}

	@Override
	public boolean receiveShoot() {
		
		hp--;
		return true;
	}
	
public boolean receiveThunder() {
		
		killObject();
		return true;
	}

}
