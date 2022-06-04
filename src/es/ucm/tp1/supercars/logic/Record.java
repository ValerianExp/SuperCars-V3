package es.ucm.tp1.supercars.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import es.ucm.tp1.supercars.control.exceptions.InputOutputRecordException;

public class Record {
	private Game game;
	private static final String FILE_NAME = "record";
	private double record;

	public Record(Game game) {
		this.game = game;
		record = 0;

	}

	public void setNewRecord(double r) {
		this.record = r;
	}

	public double getRecord() {
		return record;
	}

	public void readRecord() throws InputOutputRecordException {
		try {
			Scanner scanner = new Scanner(new FileReader(FILE_NAME + ".txt"));
			while (scanner.hasNext()) {
				String s = scanner.nextLine();
				String[] parameters = s.split(":");
				if (parameters[0].equals(game.levelName())) {
					game.setRecord(Math.round(Double.parseDouble(parameters[1]) / 10) / 100.0);
					System.out.println(game.levelName().toUpperCase() + " record is " + game.getRecord() + " s");
				}
			}
			scanner.close();
		} catch (IOException ex) {
			throw new InputOutputRecordException("An error ocurred on reading a file");
		}
	}

	public void setFileRecord(double r) throws InputOutputRecordException {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME + ".txt"));
			Scanner scanner = new Scanner(new FileReader(FILE_NAME + ".txt"));
			String line = "";
			while (scanner.hasNextLine()) {
				if (line.contains(game.levelName())) {
					line.replace(game.levelName(), game.levelName() + " " + getRecord());
				} else {
					line += game.levelName() + " " + getRecord();
				}
				bw.write(line + "\n");
			}
			if (!scanner.hasNextLine()) {
				bw.write(game.levelName() + " " + (int) (getRecord() * 10000) + "\n");
			}
			bw.close();
			scanner.close();
		} catch (IOException ex) {
			throw new InputOutputRecordException("An error ocurred on writing a file");
		}
	}
}
