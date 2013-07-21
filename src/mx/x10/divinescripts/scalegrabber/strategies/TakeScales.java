package mx.x10.divinescripts.scalegrabber.strategies;

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
				&& (scale = GroundItems.getNearest(SCALE_ID)) != null
				&& DRAG_AREA.contains(Players.getLocal())
				&& !Players.getLocal().isMoving()
				&& !Players.getLocal().isInCombat();
	}

	@Override
	public void execute() {
		if(scale != null) {
			if (scale.isOnScreen()) {
				scale.interact("Take");
			} else {
				Walking.walk(scale); //walks using minimap.
			}
		}
	}

	@Override
	public String getState() {
		return "Gathering scales";
	}

}
