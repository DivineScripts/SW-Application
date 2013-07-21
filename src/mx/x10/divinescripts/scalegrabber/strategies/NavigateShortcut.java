package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;


public class NavigateShortcut implements Strategy {


	@Override
	public boolean isValid() {
		return !DRAG_AREA.contains(Players.getLocal())
				&& SceneEntities.getNearest(SHORTCUT_ID) != null
				&& Players.getLocal().getAnimation() == -1;
	}

	@Override
	public void execute() {
		if(SceneEntities.getNearest(SHORTCUT_ID).isOnScreen()) {
			SceneEntities.getNearest(SHORTCUT_ID).interact("Squeeze-through");
		} else {
			Camera.turnTo(SceneEntities.getNearest(SHORTCUT_ID));
		}
	}

	@Override
	public String getState() {
		return "Going through shortcut";
	}

}
