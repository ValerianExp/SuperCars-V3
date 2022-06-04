package es.ucm.tp1.supercars.logic.actions;

import es.ucm.tp1.supercars.logic.Collider;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.logic.InstantAction;

public class WaveAction implements InstantAction {

	@Override
	public void execute(Game game) {
		
		for (int i = game.getX() + game.getVisibility() - 1 ; i >= game.getX(); i--) {
			for (int j = game.getRoadWidth(); j >= 0; j--) {
				Collider other = game.getObjectInPosition(i, j);

				if (other != null && game.getObjectInPosition(i+1, j)==null)
					other.receiveWave();
			}	
		}
		
	}
	

}
