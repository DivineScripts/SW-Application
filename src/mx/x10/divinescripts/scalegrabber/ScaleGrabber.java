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
import org.powerbot.game.api.util.Timer;


@Manifest(authors = { "DivineScripts" }, description = "Test Script for SW", name = "Test Script")
public class ScaleGrabber extends ActiveScript implements PaintListener{

	//Script vars
	private ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	private Timer timer;
	private int scalePrice, mph, profit;
	public static int  scalesTotal;
	private int scalesInInv;
	private float sph;
	private String currentState = "Starting up...";
	
	public void onStart() {
		timer = new Timer(1000);
		scalePrice = 1100; //will make it dynamic later.
		addStrategies(new OpenActionBar(), new TeleToTaverly(),new WalkToBank(),
				new OpenBank(), new BankScales(), new CloseBank(), new WalkToTunnel(), 
				new GoInTunnel(), new NavigateShortcut(),new SafeSpot(), new Antiban(), 
				new TakeScales());
	}

	@Override
	public int loop() {
			for(Strategy strategy : strategies) {
				if(strategy.isValid()) {
					currentState = strategy.getState();
					strategy.execute();
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
		scalesInInv = Inventory.getCount(Constants.SCALE_ID);
		profit = (scalesTotal + scalesInInv) * scalePrice;
		
		if(profit > 0) {
			mph = (profit / (int)(timer.getElapsed()/1000)) * 60 * 60;
		}
		
		if((scalesTotal + scalesInInv) > 0) {
			sph = ((float)(scalesTotal + scalesInInv) / (float)(timer.getElapsed()/1000)) * 60 * 60;
		}
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 390, 517, 140);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Arial", 0, 20));
		g.drawString("Time Elapsed: " + timer.toElapsedString(), 30, 430);
		g.drawString("$ P/H: " + (NumberFormat.getNumberInstance(Locale.US).format(mph)), 30, 460);
		g.drawString("State: " + currentState, 30, 490);
		g.drawString("Scales Collected: " + (scalesTotal + scalesInInv), 285, 430);
		g.drawString("Scales P/H: " + NumberFormat.getNumberInstance(Locale.US).format((int)sph), 285, 460);
	}
	
}
