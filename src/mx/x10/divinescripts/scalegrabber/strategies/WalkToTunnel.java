package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Constants;
import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;

public class WalkToTunnel implements Strategy {

	@Override
	public boolean isValid() {
		return Inventory.getCount(Constants.SCALE_ID) == 0
				&& SceneEntities.getNearest(Constants.SHORTCUT_ID) == null
				&& !Players.getLocal().isMoving();
	}

	@Override
	public void execute() {
		Constants.PATH_TO_TUNNEL.traverse();
	}

	@Override
	public String getState() {
		return "Walking to tunnel";
	}

}
