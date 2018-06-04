package controller;

import java.awt.Dimension;
import java.util.Random;

import model.Tortue;
import view.FeuilleDessin;

public class RandomTurtlesThread extends Thread {
	
	private Controller controller = Controller.getInstance();
	private FeuilleDessin feuille;
	
	public RandomTurtlesThread(FeuilleDessin feuille) {
		Dimension dim = feuille.getSize();
		for(int i = 0; i <5; i++){
			Tortue t = controller.createTortue(i, dim.height/2, dim.width/2);
			t.setColor(i);
			t.setPosition(dim.height/2, dim.width/2);
		}
		this.feuille = feuille;
	}
	
	@Override
	public synchronized void start() {
		
		super.start();
	}
	
	@Override
	public void run() {
		Random r = new Random();
		int turtleChoice;
		int dirChoice;
		int distRandom;
		
		while(true){
			turtleChoice = r.nextInt(controller.getTortues().entrySet().size());
			dirChoice = r.nextInt(360);
			distRandom = r.nextInt(100);
			
			
			Tortue tortue = (Tortue) controller.getTortues().keySet().toArray()[turtleChoice];
			tortue.droite(dirChoice);
			controller.createSegment(tortue, distRandom);
			
			
			feuille.repaint();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
