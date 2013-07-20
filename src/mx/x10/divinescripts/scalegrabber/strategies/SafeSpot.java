package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Constants;
import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;


public class SafeSpot implements Strategy{

	@Override
	public boolean isValid() {
		return Players.getLocal().isInCombat();
	}

	@Override
	public void execute() {
		Walking.walk(Constants.SAFE_SPOT);
		Task.sleep(1000);
	}

	@Override
	public String getState() {
		return "Under Attack, failsafe activated";
	}

}
