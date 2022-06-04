package es.ucm.tp1.supercars.logic;

import java.util.Random;

import es.ucm.tp1.supercars.control.exceptions.InputOutputRecordException;
import es.ucm.tp1.supercars.logic.gameobjects.GameObject;
import es.ucm.tp1.supercars.logic.gameobjects.Player;
import es.ucm.tp1.supercars.view.GameSerializer;

public class Game {
	private Player player;
	private Level level;
	private Random rand;
	private int nCycles;
	private boolean exit;
	private long SEED;
	// Timer
	private double startTime;
	private double elapsedTime;
	private double elapsedSeconds;
	private double secondsDisplay;
	private boolean timeAppears = true;
	// Record
	//private double record;
	private Record record;
	// La Lista
	private GameObjectContainer objectContainer;
	//Serialize
	private GameSerializer serializer;

	public Game(long seed, Level level) {
		startTimer();
		SEED = seed;
		this.rand = new Random(SEED);
		this.level = level;

		inicialice();
	}

	public void inicialice() {
		nCycles = 0;
		this.rand = new Random(SEED);
		GameObjectGenerator.reset(level);
		player = new Player(this, 0, (getRoadWidth() / 2));
		objectContainer = new GameObjectContainer();
		record = new Record(this);
		//setRecord(0);
		tryToGenerate();
	}

	public void inicialice(Level level, long seed) {
		this.level = level;
		SEED = seed;
		inicialice();
	}

	public void startTimer() {
		startTime = System.currentTimeMillis();
	}

	public double showTimeSeconds() {
		elapsedTime = System.currentTimeMillis() - startTime;
		elapsedSeconds = elapsedTime / 1000;
		secondsDisplay = elapsedSeconds % 60;
		if (nCycles == 0) {
			return 0;
		}
		return secondsDisplay;
	}

	public void toggleTest() {
		timeAppears = false;
	}

	public int getVisibility() {
		return level.getVisibility();
	}

	public int getRoadWidth() {
		return level.getWidth();
	}

	public int getRoadLength() {
		return level.getLength();
	}

	public double getObstacleFrequency() {
		return level.getObstacleFrequency();
	}

	public double getCoinFrequency() {
		return level.getCoinFrequency();
	}

	public String positionToString(int x, int y) {
		int relativeX = player.getX() + x;
		String s = "";
		if (player.isInPosition(relativeX, y)) {
			s += player.toString() + " ";
		}
		s += objectContainer.toString(relativeX, y);

		if (relativeX == getRoadLength()) {
			s += "¦";
		}

		return s.trim();
	}

	public boolean canPlayerMoveDown() {
		return getPlayerY() < getRoadWidth() - 1;
	}

	public void goUp() {
		player.goUp();
	}

	public void goDown() {
		player.goDown();
	}

	public void update() {
		objectContainer.removeDeadObjects();
		player.doCollision();
		objectContainer.updateObjects();
		GameObjectGenerator.generateRuntimeObjects(this);
		objectContainer.removeDeadObjects();
		nCycles++;

	}

	public void advance() {
		player.advance();
	}

	public void removeAllObjects() {
		objectContainer.reset();
	}

	public boolean playerHasCrashed() {
		return !player.isAlive();
	}

	public boolean playerHasArrived() {
		return player.hasArrived();
	}

	public int getPlayerX() {
		return player.getX();
	}

	public int getPlayerY() {
		return player.getY();
	}

	public int getActualDistanceToGoal() {
		return getRoadLength() - getPlayerX();
	}

	public int getRandomLane() {
		return (int) (getrandomNumber() * getRoadWidth());
	}

	private double getrandomNumber() {
		return rand.nextDouble();
	}

	public int getPlayerCoins() {
		return player.getnCoins();
	}

	public boolean tryToAddObject(GameObject obj, double objectFrequency) {
		if (rand.nextDouble() < objectFrequency && !objectContainer.isObjectInPosition(obj.getX(), obj.getY())) {
			objectContainer.add(obj);
			return true;
		}
		return false;
	}

	public boolean tryToAddGrenade(GameObject obj) {
		if (!objectContainer.isObjectInPosition(obj.getX(), obj.getY())
				&& isObjectOnVisiblePosition(obj)) {
			objectContainer.add(obj);

			return true;
		}
		return false;
	}

	public boolean isObjectOnVisiblePosition(GameObject obj) {
		return (obj.getX() < level.getVisibility() + getPlayerX() && obj.getY() < getRoadWidth() - 1);
	}

	public void tryToGenerate() {
		GameObjectGenerator.generateGameObjects(this, level);
	}

	public String levelName() {
		return level.toString();
	}

	public Level getLevel() {
		return level;
	}

	public int getPlayerCoinsCounter() {
		return player.getnCoins();
	}

	public int getNumberCycles() {
		return nCycles;
	}

	public void increaseCoinCounter(int coin) {
		player.increaseCoinCounter(coin);
	}

	public void reset(Level level, long seed) {
		GameObjectGenerator.reset(level);
		if (level == null) {
			inicialice(this.level, SEED);
			//			toggleTest();
		} else {
			inicialice(level, seed);
			//			toggleTest();
		}

	}

	public void setRecord(double r) {
		record.setNewRecord(r);
		try {
			record.setFileRecord(r);
		} catch (InputOutputRecordException e) {
			e.getMessage();
		}
	}

	public double getRecord() {
		return record.getRecord();
	}

	public boolean isFinished() {
		return playerHasCrashed() || playerHasArrived() || getExit();
	}

	public void exit() {
		exit = true;
	}

	public boolean getExit() {
		return exit;
	}

	public GameObject getObjectInPosition(int x, int y) {
		return objectContainer.isInPosition(x, y);
	}

	public void playerCrash() {
		player.crashDamage();
	}

	public static int getInitialCoins() {
		return Player.getInitialCoins();
	}

	public void subCoins(int coins) {
		player.decreaseCoins(coins);
	}

	public void execute(InstantAction action) {
		action.execute(this);

	}

	public int getX() {
		return player.getX();
	}

	public int getY() {
		return player.getY();
	}

	public void forceAddObject(GameObject o) {
		for (int i = 0; i <= getRoadWidth(); i++) {
			objectContainer.isInPosition((getVisibility() + getX() - 1), i).killObject();
		}
		objectContainer.removeDeadObjects();
		objectContainer.add(o);
		o.onEnter();
	}

	public boolean playerCollision() {

		return player.doCollision();

	}

	public void addPlayerCoins(int i) {
		player.increaseCoinCounter(i);

	}

	public boolean canPlayerMoveUp() {
		return getPlayerY() > 0;

	}

	public int getRandomColum() {

		return (int) (getrandomNumber() * getVisibility());
	}

	public void removeAllCoin() {
		player.decreaseCoins(player.getnCoins());
	}

	public boolean getTimeAppears() {
		return timeAppears;
	}

	public void serialize(Game game) {
		serializer = new GameSerializer(this);
		//System.out.println(serializer.toString());
	}

	public void save() {
		serializer = new GameSerializer(this);
	}

	public String serializerToString() {
		return serializer.toString();
	}

	public String playerToSerialize() {
		return player.serialize();
	}

	public String positionToSerialize(int x, int y) {
		if (x == getPlayerX() && y == getPlayerY()) {
			return playerToSerialize() + "\n";
		}

		if (getObjectInPosition(x, y) != null) {
			return getObjectInPosition(x, y).serialize() + "\n";
		}
		return "";
	}

	public void showRecord() throws InputOutputRecordException {
		record = new Record(this);
		record.readRecord();
	}

}
