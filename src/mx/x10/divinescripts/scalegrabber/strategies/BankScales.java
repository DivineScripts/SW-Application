package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.ScaleGrabber;
import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;


public class BankScales implements Strategy {

	@Override
	public boolean isValid() {
		return Bank.isOpen() && Inventory.getCount(SCALE_ID) > 0;
	}

	@Override
	public void execute() {
		Bank.depositInventory();
		ScaleGrabber.scalesTotal += 28;
	}

	@Override
	public String getState() {
		return "Banking Hides";
	}

}
