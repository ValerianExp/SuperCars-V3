package es.ucm.tp1.supercars.control.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.control.exceptions.InputOutputRecordException;
import es.ucm.tp1.supercars.logic.Game;

public class DumpCommand extends Command {
	private static final String NAME = "dump";

	private static final String DETAILS = "[d]ump <filename>";

	private static final String SHORTCUT = "d";

	private static final String HELP = "Show the content of a saved file";

	private String fileName = "";

	public DumpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName + ".txt"));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String everything = sb.toString();
			System.out.println(everything);
			br.close();
		} catch (IOException ex) {
			throw new InputOutputRecordException("An error ocurred on reading a file");
		}
		return false;
	}

	@Override
	protected Command parse(String[] words) throws CommandParseException {
		if (matchCommandName(words[0])) {
			if (words.length == 2) {
				fileName = words[1];
				return this;
			} else {
				throw new CommandParseException(
						String.format("[ERROR]:Command %s: %s", words[0], INCORRECT_NUMBER_OF_ARGS_MSG));
			}
		}
		return null;
	}
}
