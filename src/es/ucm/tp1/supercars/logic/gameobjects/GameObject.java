package es.ucm.tp1.supercars.logic.gameobjects;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;

public abstract class GameObject implements Collider {

	protected int hp, x, y;

	protected Game game;

	protected String symbol;

	protected boolean alive;

	protected static String name;

	protected static String details;

	public GameObject(Game game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;
	}

	protected String getSymbol() {
		return symbol;
	}

	
	public String toString() {
		if (isAlive()) {
			return getSymbol();
		}

		return "";
	}

	public boolean isInPosition(int x, int y) {
		return this.x == x && this.y == y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isAlive() {
		if(hp<=0) {
			return false;
		}
		return true;
	}
	
	public static String getInfo() {
        return "[" + name + "]" + " " + details;
    }
	
	public boolean receiveThunder() {
		
		killObject();
		
		return true;
	}

	public abstract void onEnter();

	public abstract void update();

	public abstract void onDelete();
	
	public boolean reciveWave() {
		this.x++;
		return true;
	}
	
	public void killObject() {
		hp=0;
	}
	
	public boolean receiveExplosion() {
		
		killObject();
		
		return true;
	}
	
	public String serialize() {
		return symbol + " " + "(" + x + "," +  y + ")"; 
	}
}
