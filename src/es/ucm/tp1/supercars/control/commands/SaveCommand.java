package es.ucm.tp1.supercars.control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.control.exceptions.InputOutputRecordException;
import es.ucm.tp1.supercars.logic.Game;

public class SaveCommand extends Command {
	private static final String NAME = "Save";

	private static final String DETAILS = "sa[v]e <filename>";

	private static final String SHORTCUT = "v";

	private static final String HELP = "Save filename";

	private static final String SUCCESS_MSG = "Game successfully saved in ";

	private String filename = "";

	public SaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename + ".txt"));
			game.serialize(game);
			bw.write(game.serializerToString());
			bw.close();
			System.out.println(SUCCESS_MSG + filename + ".txt");
		} catch (IOException e) {
			throw new InputOutputRecordException("ERROR EN LA ESCRITURA DEL ARCHIVO");
		}
		return false;
	}

	@Override
	protected Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length == 2) {
				filename = words[1];
				return this;
			} else {
				throw new CommandParseException(
						String.format("[ERROR]:Command %s: %s", words[0], INCORRECT_NUMBER_OF_ARGS_MSG));
			}
		}
		return null;
	}
}
