package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Timer;

public class NavigateShortcut implements Strategy {

	@Override
	public boolean isValid() {
		return !DRAG_AREA.contains(Players.getLocal())
				&& SceneEntities.getNearest(SHORTCUT_ID) != null
				&& Players.getLocal().getAnimation() == -1;
	}

	@Override
	public void execute() {
		final Timer t = new Timer(3000);
		if(SceneEntities.getNearest(SHORTCUT_ID).isOnScreen() && SceneEntities.getNearest(SHORTCUT_ID).interact("Squeeze-through")) {
			while(t.isRunning() && !DRAG_AREA.contains(Players.getLocal())) {
				Task.sleep(30, 60);
			}
		} else {
			Camera.turnTo(SceneEntities.getNearest(SHORTCUT_ID));
			while(t.isRunning() && !SceneEntities.getNearest(SHORTCUT_ID).isOnScreen()) {
				Task.sleep(30, 60);
			}
		}
	}

	@Override
	public String getState() {
		return "Going through shortcut";
	}

}
