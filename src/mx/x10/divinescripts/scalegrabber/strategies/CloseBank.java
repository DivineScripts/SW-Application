package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;


public class CloseBank implements Strategy {

	@Override
	public boolean isValid() {
		return Bank.isOpen() && !Inventory.contains(SCALE_ID);
	}

	@Override
	public void execute() {
		Bank.close();
	}

	@Override
	public String getState() {
		return "Closing Bank";
	}

}
