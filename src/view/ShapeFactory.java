package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import controller.Controller;
import model.Tortue;

/**
 * Classe permettant de créer d'autre type de forme pour les tortues
 * @author Epulapp
 *
 */
public class ShapeFactory {
	private static final ShapeFactory INSTANCE = new ShapeFactory();
	
	private ShapeFactory(){}
	
	public static ShapeFactory getInstance(){
		return INSTANCE;
	}
	
	/**
	 * Methode permettant de créer des shapes pour une tortue donnée
	 * @param t La tortue cible
	 * @param graph Le graphique cible
	 * @param xCentre La coordonnée x du centre de la forme à créer
	 * @param yCentre La coordonnée y du centre de la forme à créer
	 * @return
	 */
	public Shape createShape(Tortue t, Graphics graph, Integer xCentre, Integer yCentre){
		switch(t.getShape()){
		case "circle" :
			Shape circle = new Ellipse2D.Double(t.getX()-8, t.getY()-8, 16, 16);
			graph.setColor(Controller.getInstance().getColors().containsKey(t.getArrowColor())?Controller.getInstance().getColors().get(t.getArrowColor()) : Color.BLACK);
			graph.fillOval(t.getX()-8, t.getY()-8, 16, 16);
			return circle;
		default:
			//Calcule les 3 coins du triangle a partir de
			// la position de la tortue p
			Point p = new Point(xCentre, yCentre);
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
			graph.setColor(Controller.getInstance().getColors().containsKey(t.getArrowColor())?Controller.getInstance().getColors().get(t.getArrowColor()) : Color.BLACK);
			graph.fillPolygon(arrow);
			return arrow;
		}
	}
}
