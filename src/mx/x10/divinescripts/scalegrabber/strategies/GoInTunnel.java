package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Constants;
import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;


public class GoInTunnel implements Strategy {
	
	@Override
	public boolean isValid() {
		return SceneEntities.getNearest(Constants.TUNNEL_ID) != null && SceneEntities.getNearest(Constants.TUNNEL_ID).isOnScreen() && !Players.getLocal().isMoving();
	}

	@Override
	public void execute() {
		SceneEntities.getNearest(Constants.TUNNEL_ID).interact("Climb-down");
		Task.sleep(500, 1000);
	}

	@Override
	public String getState() {
		return "Going down tunnel";
	}

}
