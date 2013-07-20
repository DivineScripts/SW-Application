package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Constants;
import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class NavigateShortcut implements Strategy {

	SceneObject shortcut;
	
	@Override
	public boolean isValid() {
		return !Constants.DRAG_AREA.contains(Players.getLocal())
				&& (shortcut = SceneEntities.getNearest(Constants.SHORTCUT_ID)) != null;
	}

	@Override
	public void execute() {
		if(shortcut.isOnScreen()) {
			if(Players.getLocal().getAnimation() != -1) {
				Task.sleep(50, 60);
			}
			shortcut.interact("Squeeze-through");
		} else {
			Camera.turnTo(shortcut);
		}
	}

	@Override
	public String getState() {
		return "Going through shortcut";
	}

}
