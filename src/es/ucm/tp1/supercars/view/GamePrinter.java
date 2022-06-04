package es.ucm.tp1.supercars.view;

import es.ucm.tp1.supercars.control.exceptions.InputOutputRecordException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.gameobjects.Coin;
import es.ucm.tp1.supercars.logic.gameobjects.Grenade;
import es.ucm.tp1.supercars.logic.gameobjects.Obstacle;
import es.ucm.tp1.supercars.logic.gameobjects.Pedestrian;
import es.ucm.tp1.supercars.logic.gameobjects.Player;
import es.ucm.tp1.supercars.logic.gameobjects.SuperCoin;
import es.ucm.tp1.supercars.logic.gameobjects.Truck;
import es.ucm.tp1.supercars.logic.gameobjects.Turbo;
import es.ucm.tp1.supercars.logic.gameobjects.Wall;
import es.ucm.tp1.supercars.utils.StringUtils;

public class GamePrinter {

	private static final String SPACE = " ";

	private static final String ROAD_BORDER_PATTERN = "═";

	private static final String LANE_DELIMITER_PATTERN = "─";

	private static final int CELL_SIZE = 7;

	private static final int MARGIN_SIZE = 2;

	private String indentedRoadBorder;

	private String indentedLlanesSeparator;

	private String margin;

	private static final String CRASH_MSG = String.format("Player crashed!%n");

	private static final String WIN_MSG = String.format("Player wins!%n");

	private static final String DO_EXIT_MSG = "Player leaves the game";

	private static final String GAME_OVER_MSG = "[GAME OVER] ";

	public String newLine;

	protected Game game;

	public GamePrinter(Game game) {
		this.game = game;

		margin = StringUtils.repeat(SPACE, MARGIN_SIZE);

		setRoad();

	}

	private String getInfo() {
		/* @formatter:off */
		String str = "";
		str +=  "Distance: " + (game.getActualDistanceToGoal()) + "\n" +
				"Coins: " + game.getPlayerCoinsCounter() + "\n" +
				"Cycle: " + game.getNumberCycles() + "\n" +
				"Total obstacles: " + Obstacle.nObstacles + "\n" +
				"Total coins: " +  Coin.nCoins ;
		if (SuperCoin.hasSuperCoin()) {
			str += "\n" + "Supercoin is present";
		}
				
		

		if(game.getTimeAppears()) {
			str += "\n" + "Elapsed Time: " + String.format("%.2f",game.showTimeSeconds() ) + " s";
			
		}
		
		/* @formatter:on */
		return str;
	}

	public static String infoObj() {
		String infoMessage = "";
		infoMessage += Player.getInfo() + "\n";
		infoMessage += Coin.getInfo() + "\n";
		infoMessage += Obstacle.getInfo() + "\n";
		infoMessage += Grenade.getInfo() + "\n";
		infoMessage += Wall.getInfo() + "\n";
		infoMessage += Turbo.getInfo() + "\n";
		infoMessage += SuperCoin.getInfo() + "\n";
		infoMessage += Truck.getInfo() + "\n";
		infoMessage += Pedestrian.getInfo();
		return infoMessage;
	}

	@Override
	public String toString() {
		setRoad();
		StringBuilder str = new StringBuilder();

		// Game Status

		str.append(getInfo());

		// Paint game

		str.append(indentedRoadBorder);

		String verticalDelimiter = SPACE;

		for (int y = 0; y < game.getRoadWidth(); y++) {
			str.append(this.margin).append(verticalDelimiter);
			for (int x = 0; x < game.getVisibility(); x++) {
				str.append(StringUtils.centre(game.positionToString(x, y), CELL_SIZE)).append(verticalDelimiter);
			}
			if (y < game.getRoadWidth() - 1) {
				str.append(this.indentedLlanesSeparator);
			}
		}
		str.append(this.indentedRoadBorder);

		return str.toString();
	}

	public String endMessage() {

		String s = GAME_OVER_MSG;

		if (game.playerHasCrashed()) {
			s += CRASH_MSG;
		} else if (game.playerHasArrived()) {
			s += WIN_MSG;// + "New record!: " + String.format("%.2f", game.showTimeSeconds()) + " s";

			if (game.getRecord() <= game.showTimeSeconds() && game.getTimeAppears()) {
				game.setRecord(game.showTimeSeconds());

				s += "New record!: " + String.format("%.2f", game.showTimeSeconds()) + " s";
			}
		} else {
			s += DO_EXIT_MSG;
		}
		return s;
	}

	private void setRoad() {
		String roadBorder = ROAD_BORDER_PATTERN
				+ StringUtils.repeat(ROAD_BORDER_PATTERN, (CELL_SIZE + 1) * game.getVisibility());
		indentedRoadBorder = String.format("%n%s%s%n", margin, roadBorder);

		String laneDelimiter = StringUtils.repeat(LANE_DELIMITER_PATTERN, CELL_SIZE);
		String lanesSeparator = SPACE + StringUtils.repeat(laneDelimiter + SPACE, game.getVisibility() - 1)
				+ laneDelimiter + SPACE;

		indentedLlanesSeparator = String.format("%n%s%s%n", margin, lanesSeparator);
	}

}
