package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;


public class Antiban implements Strategy{
	int random;
	@Override
	public boolean isValid() {
		return (random = Random.nextInt(0, 70)) > 65; 
	}
	
	@Override
	public void execute() {
		switch(random) {
		case 66:
			Camera.setAngle('n');
			break;
		case 67:
			Camera.setAngle('e');
			break;
		case 68:
			Camera.setAngle('s');
			break;
		case 69:
			Camera.setAngle('w');
			break;
		}
		
	}

	@Override
	public String getState() {
		return "Anti-ban";
	}

}
