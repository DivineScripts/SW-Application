package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Constants;
import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Timer;


public class SafeSpot implements Strategy{

	Timer t;
	
	@Override
	public boolean isValid() {
		return Players.getLocal().isInCombat();
	}

	@Override
	public void execute() {
		t = new Timer(3000);
		Walking.walk(Constants.SAFE_SPOT);
		while(t.isRunning() | Players.getLocal().isInCombat()) {
			Task.sleep(40);
		}
	}

	@Override
	public String getState() {
		return "Under Attack, failsafe activated";
	}

}
