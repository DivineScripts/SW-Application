package mx.x10.divinescripts.scalegrabber.strategies;

import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class GoInTunnel implements Strategy {

	SceneObject tunnel;
	
	@Override
	public boolean isValid() {
		return (tunnel = SceneEntities.getNearest(TUNNEL_ID)) != null
				&& tunnel.isOnScreen()
				&& !Players.getLocal().isMoving();
	}

	@Override
	public void execute() {
		final Timer t = new Timer(3000);
		if(tunnel != null) {
			if(tunnel.interact("Climb-down")) {
				while(t.isRunning() && !DOWN_STAIRS.isOnScreen()) {
					Task.sleep(30,40);
				}
			}
		}
		
	}

	@Override
	public String getState() {
		return "Going down tunnel";
	}

}
