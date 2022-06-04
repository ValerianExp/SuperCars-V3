package es.ucm.tp1.supercars.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import es.ucm.tp1.supercars.logic.gameobjects.GameObject;

public class GameObjectContainer {
	private List<GameObject> gameobjects;

	public GameObjectContainer() {
		gameobjects = new ArrayList<>();
	}

	public int getSize() {
		return gameobjects.size();
	}

	public void add(GameObject obj) {
		gameobjects.add(obj);
		obj.onEnter();
	}

	public GameObject isInPosition(int x, int y) {
		for (GameObject obj : gameobjects) {
			if (obj.isInPosition(x, y)) {
				return obj;
			}
		}
		return null;
	}

	public boolean isObjectInPosition(int x, int y) {
		return isInPosition(x, y) != null;
	}

	public void updateObjects() {

		for (GameObject obj : gameobjects) {
			obj.update();

		}

	}

	public String toString(int x, int y) {
		String s = "";

		for (GameObject obj : gameobjects) {
			if (obj.isInPosition(x, y)) {
				s += obj.toString() + " ";
			}
		}

		return s;
	}

	public void removeDeadObjects() {
		for (int i = 0; i < getSize(); i++) {
			if (!gameobjects.get(i).isAlive()) {
				gameobjects.get(i).onDelete();
				gameobjects.remove(i);

			}
		}
	}

	public void reset() {

		for (GameObject obj : gameobjects) {
			obj.onDelete();
		}

		gameobjects.clear();
	}

	public String toSerialize(int x, int y) {
		String s = "";
		for (GameObject obj : gameobjects) {
			if (obj.isInPosition(x, y)) {
				s += obj.serialize() + "\n";
			}
		}
		return s;
	}

}
