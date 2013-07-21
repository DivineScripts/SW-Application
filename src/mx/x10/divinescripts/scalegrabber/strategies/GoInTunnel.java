package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;

public class GoInTunnel implements Strategy {

	@Override
	public boolean isValid() {
		return (SceneEntities.getNearest(TUNNEL_ID)) != null
				&& SceneEntities.getNearest(TUNNEL_ID).isOnScreen()
				&& !Players.getLocal().isMoving();
	}

	@Override
	public void execute() {
		SceneEntities.getNearest(TUNNEL_ID).interact("Climb-down");
	}

	@Override
	public String getState() {
		return "Going down tunnel";
	}

}
