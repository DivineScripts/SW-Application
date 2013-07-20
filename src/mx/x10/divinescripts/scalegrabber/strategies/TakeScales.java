package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Constants;
import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.GroundItem;


public class TakeScales implements Strategy {

	GroundItem scale;

	@Override
	public boolean isValid() {
		return !Inventory.isFull()
				&& Constants.DRAG_AREA.contains(Players.getLocal())
				&& !Players.getLocal().isMoving() && !Players.getLocal().isInCombat();
	}

	@Override
	public void execute() {
		scale = GroundItems.getNearest(Constants.SCALE_ID);
		if (scale != null) {
			if (scale.isOnScreen()) {
				scale.interact("Take");
			} else {
				Walking.walk(scale);
			}
		}

	}

	@Override
	public String getState() {
		return "Gathering scales";
	}

}
