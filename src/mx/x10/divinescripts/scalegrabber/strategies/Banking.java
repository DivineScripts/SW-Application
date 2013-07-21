package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.ScaleGrabber;
import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Timer;

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
		Timer t = new Timer(1500);
		if(Inventory.getCount(SCALE_ID) > 1) {
			if(Bank.isOpen()) {
				Bank.depositInventory();
				while(t.isRunning() && Inventory.getCount() > 0) {
					Task.sleep(40, 60);
				}
				ScaleGrabber.scalesTotal += 28;
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
