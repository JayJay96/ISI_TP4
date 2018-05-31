package view;

// package logo;

import java.awt.*;

import javax.swing.*;

import controller.Controller;
import model.Tortue;

import java.util.List;

import java.util.*;
import java.util.Map.Entry;

/**
 * Titre :        Logo
 * Description :  Un exemple de programme graphique utilisant la celebre Tortue Logo
 * Copyright :    Copyright (c) 2000
 * Societe :      LIRMM
 * @author J. Ferber
 * @version 2.0
 */

public class FeuilleDessin extends JPanel {
	private Controller controller = Controller.getInstance();

	public FeuilleDessin() {
		
	}

	public void reset() {
		for(Entry<Tortue, List<Segment>> tortue : controller.getTortues().entrySet()){
			tortue.getKey().reset();
			tortue.getValue().clear();
		}
			
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Color c = g.getColor();
		
		Dimension dim = getSize();
		g.setColor(Color.white);
		g.fillRect(0,0,dim.width, dim.height);
		g.setColor(c);
		
		showTurtles(g);
	}
	
	public void showTurtles(Graphics g) {
		for(Entry<Tortue, List<Segment>> tortue : controller.getTortues().entrySet())
			drawTurtle(g, tortue);
	}
	
	public void drawTurtle (Graphics graph, Entry<Tortue, List<Segment>> tortue) {
		if (graph==null)
			return;
		List<Segment> listSegments = tortue.getValue();
		Tortue t = tortue.getKey();
		// Dessine les segments
		for(Iterator it = listSegments.iterator();it.hasNext();) {
			Segment seg = (Segment) it.next();
			seg.drawSegment(graph);
		}

		//Calcule les 3 coins du triangle a partir de
		// la position de la tortue p
		Point p = new Point(t.getX(),t.getY());
		Polygon arrow = new Polygon();

		//Calcule des deux bases
		//Angle de la droite
		double theta=Tortue.ratioDegRad*(t.getDir()*-1);
		//Demi angle au sommet du triangle
		double alpha=Math.atan( (float)Tortue.rb / (float)Tortue.rp );
		//Rayon de la fleche
		double r=Math.sqrt( Tortue.rp*Tortue.rp + Tortue.rb*Tortue.rb );
		//Sens de la fleche

		//Pointe
		Point p2=new Point((int) Math.round(p.x+r*Math.cos(theta)),
						 (int) Math.round(p.y-r*Math.sin(theta)));
		arrow.addPoint(p2.x,p2.y);
		arrow.addPoint((int) Math.round( p2.x-r*Math.cos(theta + alpha) ),
		  (int) Math.round( p2.y+r*Math.sin(theta + alpha) ));

		//Base2
		arrow.addPoint((int) Math.round( p2.x-r*Math.cos(theta - alpha) ),
		  (int) Math.round( p2.y+r*Math.sin(theta - alpha) ));

		arrow.addPoint(p2.x,p2.y);
		graph.setColor(Color.green);
		graph.fillPolygon(arrow);
    }
}
