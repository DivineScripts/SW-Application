package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

import sk.action.ActionBar;

public class WalkToBank implements Strategy {

	@Override
	public boolean isValid() {
		return Inventory.getCount(SCALE_ID) > 0
				&& !DRAG_AREA.contains(Players.getLocal())
				&& !Players.getLocal().isMoving();
	}

	@Override
	public void execute() {
		if (ActionBar.isExpanded()) {
			ActionBar.expand(false);
		}
		PATH_TO_BANK.traverse();
	}

	@Override
	public String getState() {
		return "Walking to bank";
	}

}
