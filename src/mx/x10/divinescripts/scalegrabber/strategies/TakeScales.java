package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;

public class TakeScales implements Strategy {

	@Override
	public boolean isValid() {
		return !Inventory.isFull()
				&& GroundItems.getNearest(SCALE_ID) != null
				&& DRAG_AREA.contains(Players.getLocal())
				&& !Players.getLocal().isMoving()
				&& !Players.getLocal().isInCombat();
	}

	@Override
	public void execute() {
		GroundItems.getNearest(SCALE_ID);
			if (GroundItems.getNearest(SCALE_ID).isOnScreen()) {
				GroundItems.getNearest(SCALE_ID).interact("Take");
			} else {
				Walking.walk(GroundItems.getNearest(SCALE_ID));
			}
	}

	@Override
	public String getState() {
		return "Gathering scales";
	}

}
