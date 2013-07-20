package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Constants;
import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.interactive.NPC;


public class OpenBank implements Strategy {

	NPC banker = NPCs.getNearest(Constants.BANKER_ID);
	
	@Override
	public boolean isValid() {
		return !Bank.isOpen() && Inventory.getCount(Constants.SCALE_ID) > 0 && Players.getLocal().getLocation().distance(Constants.PATH_TO_BANK.getEnd()) < 3;
	}

	@Override
	public void execute() {
		Bank.open();
	}

	@Override
	public String getState() {
		return "Opening bank";
	}

}
