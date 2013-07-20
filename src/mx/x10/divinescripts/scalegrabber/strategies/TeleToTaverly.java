package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Constants;
import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;

public class TeleToTaverly implements Strategy {

	Timer t;
	@Override
	public boolean isValid() {
		return Constants.DRAG_AREA.contains(Players.getLocal())
				&& Players.getLocal().getAnimation() == -1
				&& Inventory.isFull() && !Players.getLocal().isInCombat() 
				&& Widgets.get(Constants.LODESTONE_PARENT_ID).validate();
	}

	@Override
	public void execute() {
			t = new Timer(3000);
			Widgets.get(Constants.LODESTONE_PARENT_ID)
					.getChild(Constants.TAVERLY_TELE_ID).click(true);
			while(t.isRunning()) {
				Task.sleep(40);
			}
	}

	@Override
	public String getState() {
		return "Tele-ing to Taverly";
	}

}
