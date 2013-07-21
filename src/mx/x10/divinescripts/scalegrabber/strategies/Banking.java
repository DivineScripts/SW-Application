package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;

public class Banking implements Strategy {

	// made to satisfy the masses

	@Override
	public boolean isValid() {
		return (!Bank.isOpen() && Inventory.getCount(SCALE_ID) > 0 && Players
				.getLocal().getLocation().distance(PATH_TO_BANK.getEnd()) < 3)
				| (Bank.isOpen() && Inventory.getCount(SCALE_ID) > 0)
				| (Bank.isOpen() && !Inventory.contains(SCALE_ID));
	}

	@Override
	public void execute() {
		if(Inventory.getCount(SCALE_ID) > 0) {
			if(Bank.isOpen()) {
				Bank.depositInventory();
			} else {
				Bank.open();
			}		
		} else {
			Bank.close();
		}
	}

	@Override
	public String getState() {
		return "Banking";
	}
}
