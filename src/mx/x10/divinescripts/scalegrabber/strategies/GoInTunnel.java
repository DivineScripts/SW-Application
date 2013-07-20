package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Constants;
import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class GoInTunnel implements Strategy {

	SceneObject tunnel;
	
	@Override
	public boolean isValid() {
		return (tunnel = SceneEntities.getNearest(Constants.TUNNEL_ID)) != null
				&& tunnel.isOnScreen()
				&& !Players.getLocal().isMoving();
	}

	@Override
	public void execute() {
		tunnel.interact("Climb-down");
	}

	@Override
	public String getState() {
		return "Going down tunnel";
	}

}
