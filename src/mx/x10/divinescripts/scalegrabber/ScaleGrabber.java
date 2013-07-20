package mx.x10.divinescripts.scalegrabber;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import mx.x10.divinescripts.scalegrabber.strategies.Antiban;
import mx.x10.divinescripts.scalegrabber.strategies.BankScales;
import mx.x10.divinescripts.scalegrabber.strategies.CloseBank;
import mx.x10.divinescripts.scalegrabber.strategies.GoInTunnel;
import mx.x10.divinescripts.scalegrabber.strategies.NavigateShortcut;
import mx.x10.divinescripts.scalegrabber.strategies.OpenActionBar;
import mx.x10.divinescripts.scalegrabber.strategies.OpenBank;
import mx.x10.divinescripts.scalegrabber.strategies.SafeSpot;
import mx.x10.divinescripts.scalegrabber.strategies.TakeScales;
import mx.x10.divinescripts.scalegrabber.strategies.TeleToTaverly;
import mx.x10.divinescripts.scalegrabber.strategies.WalkToBank;
import mx.x10.divinescripts.scalegrabber.strategies.WalkToTunnel;
import mx.x10.divinescripts.scalegrabber.utils.Constants;
import mx.x10.divinescripts.scalegrabber.utils.Strategy;

import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;


@Manifest(authors = { "DivineScripts" }, description = "Test Script for SW", name = "Test Script")
public class ScaleGrabber extends ActiveScript implements PaintListener{

	//Script vars
	private ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	private long startTime;
	private long timeRunning;
	private int scalePrice, secsRunning, mph, profit, minsRunning, hoursRunning;
	public static int scalesTaken, scalesTotal;
	private float sph;
	private boolean canStart;
	private String currentState = "Starting up...";
	
	public void onStart() {
		startTime = System.currentTimeMillis();
		scalePrice = 1100; //will make it dynamic later.
		addStrategies(new OpenActionBar(), new TeleToTaverly(),new WalkToBank(),
				new OpenBank(), new BankScales(), new CloseBank(), new WalkToTunnel(), 
				new GoInTunnel(), new NavigateShortcut(),new SafeSpot(), new Antiban(), 
				new TakeScales());
		canStart = true;
	}

	@Override
	public int loop() {
		if (canStart) {
			for(Strategy strategy : strategies) {
				if(strategy.isValid()) {
					currentState = strategy.getState();
					strategy.execute();
				}
			}
		}
		return Random.nextInt(300, 500);
	}

	private void addStrategies(Strategy... strats) {
		for(Strategy s : strats) {
			strategies.add(s);
		}
	}

	@Override
	public void onRepaint(Graphics g) {
		timeRunning = System.currentTimeMillis() - startTime;
		secsRunning = (int) (timeRunning / 1000);
		scalesTaken = Inventory.getCount(Constants.SCALE_ID);
		profit = (scalesTotal + scalesTaken) * scalePrice;
		
		if(profit > 0) {
			mph = (profit / secsRunning) * 60 * 60;
			
		}
		
		if((scalesTotal + scalesTaken) > 0) {
			sph = (float)((scalesTotal + scalesTaken) / secsRunning);
		}
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 390, 517, 140);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Arial", 0, 20));
		if(secsRunning > 60) {
			minsRunning = secsRunning / 60;
			if(minsRunning > 60) {
				hoursRunning = minsRunning / 60;
				minsRunning -= hoursRunning * 60;
				g.drawString("Time Elapsed: " + hoursRunning + "h " + minsRunning + "m ", 30, 430);
			}
		} else {
			g.drawString("Time elapsed: " + secsRunning + "s", 30, 430);
		}
		g.drawString("$ P/H: " + (NumberFormat.getNumberInstance(Locale.US).format(mph)), 30, 460);
		g.drawString("State: " + currentState, 30, 490);
		g.drawString("Scales Collected: " + (scalesTotal + scalesTaken), 285, 430);
		g.drawString("Scales P/H: " + (sph), 285, 460);
	}
	
}