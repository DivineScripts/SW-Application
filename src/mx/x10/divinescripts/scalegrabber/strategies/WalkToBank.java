package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Constants;
import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

import sk.action.ActionBar;


public class WalkToBank implements Strategy {

	@Override
	public boolean isValid() {
		return Inventory.getCount(Constants.SCALE_ID) > 0 && !Constants.DRAG_AREA.contains(Players.getLocal());
	}

	@Override
	public void execute() {
		if(ActionBar.isExpanded()) {
			ActionBar.expand(false);
		}
			Constants.PATH_TO_BANK.traverse();
		Task.sleep(300);
	}

	@Override
	public String getState() {
		return "Walking to bank";
	}

}
