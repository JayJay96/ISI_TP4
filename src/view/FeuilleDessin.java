package view;

// package logo;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

public class FeuilleDessin extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;
	private Controller controller = Controller.getInstance();
	private SimpleLogo logo;

	public FeuilleDessin(SimpleLogo logo) {
		this.logo = logo;
		addMouseListener(this);
	}

	public void reset() {
		for(Entry<Tortue, List<Segment>> tortue : controller.getTortues().entrySet()){
			tortue.getValue().clear();
			tortue.getKey().reset(getSize().width/2, getSize().height/2);
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

		Shape arrow = ShapeFactory.getInstance().createShape(t, graph, t.getX(), t.getY());
		
		controller.getPolygon().put(t, arrow);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		for(Entry<Tortue, Shape> polygon : controller.getPolygon().entrySet()){
			if(polygon.getValue().contains(e.getPoint())){
				controller.setCourante(polygon.getKey());
				logo.getColorList().setSelectedIndex(polygon.getKey().getColor());
				return;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		return;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		return;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		return;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		return;
		
	}
}
