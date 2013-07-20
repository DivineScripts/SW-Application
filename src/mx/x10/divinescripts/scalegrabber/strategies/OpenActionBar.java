package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Constants;
import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

import sk.action.ActionBar;


public class OpenActionBar implements Strategy {

	@Override
	public boolean isValid() {
		return !ActionBar.isExpanded()
				&& Constants.DRAG_AREA.contains(Players.getLocal())
				&& Inventory.isFull() && !Widgets.get(Constants.LODESTONE_PARENT_ID)
				.getChild(Constants.TAVERLY_TELE_ID).validate() && !Players.getLocal().isInCombat();
	}

	@Override
	public void execute() {
		ActionBar.expand(true);
		Task.sleep(1500);
	}

	@Override
	public String getState() {
		return "Opening action bar";
	}

}
