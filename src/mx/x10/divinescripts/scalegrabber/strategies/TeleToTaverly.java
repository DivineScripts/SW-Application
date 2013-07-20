package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Constants;
import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

import sk.action.ActionBar;


public class TeleToTaverly implements Strategy {

	@Override
	public boolean isValid() {
		return Constants.DRAG_AREA.contains(Players.getLocal())
				&& Players.getLocal().getAnimation() != 16385
				&& Inventory.isFull() && !Players.getLocal().isInCombat();
	}

	@Override
	public void execute() {
		if (Widgets.get(Constants.LODESTONE_PARENT_ID)
				.getChild(Constants.TAVERLY_TELE_ID).validate()) {
			Widgets.get(Constants.LODESTONE_PARENT_ID)
					.getChild(Constants.TAVERLY_TELE_ID).click(true);
			Task.sleep(2000, 2500);
		} else {
			ActionBar.sendKey(0);
			Task.sleep(1500, 2000);
		}
	}

	@Override
	public String getState() {
		return "Tele-ing to Taverly";
	}

}
