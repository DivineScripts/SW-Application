package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;

public class WalkToTunnel implements Strategy {

	@Override
	public boolean isValid() {
		return Inventory.getCount(SCALE_ID) == 0
				&& SceneEntities.getNearest(SHORTCUT_ID) == null
				&& !Players.getLocal().isMoving();
	}

	@Override
	public void execute() {
		PATH_TO_TUNNEL.traverse();
	}

	@Override
	public String getState() {
		return "Walking to tunnel";
	}

}
