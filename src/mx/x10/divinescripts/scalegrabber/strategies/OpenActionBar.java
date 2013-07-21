package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

import sk.action.ActionBar;


public class OpenActionBar implements Strategy {
	
	
	@Override
	public boolean isValid() {
		return DRAG_AREA.contains(Players.getLocal())
				&& Inventory.isFull() && !Widgets.get(LODESTONE_PARENT_ID)
				.getChild(TAVERLY_TELE_ID).validate() && !Players.getLocal().isInCombat()
				&& Players.getLocal().getAnimation() == -1;
	}

	@Override
	public void execute() {
		if(!ActionBar.isExpanded()) {
			ActionBar.expand(true);
		}
		ActionBar.getNode(0).use();
	}

	@Override
	public String getState() {
		return "Opening action bar";
	}

}
