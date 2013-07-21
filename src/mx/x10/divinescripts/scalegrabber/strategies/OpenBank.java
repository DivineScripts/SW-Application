package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;


public class OpenBank implements Strategy {

	@Override
	public boolean isValid() {
		return !Bank.isOpen() && Inventory.getCount(SCALE_ID) > 0 && Players.getLocal().getLocation().distance(PATH_TO_BANK.getEnd()) < 3;
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
