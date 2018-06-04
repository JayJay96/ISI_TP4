package controller;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import model.Form;
import model.Tortue;
import view.Segment;
public class Controller {

	public static Controller INSTANCE = new Controller(); 
	private static Map<Integer, Color> colors;
	private Map<Tortue, List<Segment>> tortues = new HashMap<>();
	private Map<Tortue, Shape> polygon = new TreeMap<>();
	private Tortue courante;
	private Integer lastTurtleId = 0;
	private int xMax, xMin, yMax, yMin;
	
	private Controller(){
		
	}
	
	static{
		colors = new HashMap<>();
		colors.put(0, Color.BLACK);
		colors.put(1, Color.BLUE);
		colors.put(2, Color.CYAN);
		colors.put(3, Color.DARK_GRAY);
		colors.put(4, Color.RED);
		colors.put(5, Color.GREEN);
		colors.put(6, Color.LIGHT_GRAY);
		colors.put(7, Color.MAGENTA);
		colors.put(8, Color.ORANGE);
		colors.put(9, Color.GRAY);
		colors.put(10, Color.PINK);
		colors.put(11, Color.YELLOW);	
	}
	
	public Tortue createTortue(Integer arrowColor, String shape, Integer x, Integer y){
		Tortue t = new Tortue(++lastTurtleId, arrowColor, shape, x, y);
		tortues.put(t, new ArrayList<>());
		return t;
	}
	
	public void createForm(Tortue t, Form form, Integer... integers){
		form.createForm(t, integers);
	}
	
	public void createSegment(Tortue t, Integer dist){
		Segment seg = new Segment();
		
		seg.getPtStart().x = t.getX();
		seg.getPtStart().y = t.getY();
		t.avancer(dist);
		t.leverCrayon();
		if(t.getX() > xMax){
			t.setPosition(xMax - t.getX(), t.getY());
		} else if (t.getX() < xMin){
			t.setPosition(xMax + t.getX(), t.getY());
		} else if (t.getY() > yMax){
			t.setPosition(t.getX(), yMax - t.getY());
		} else if (t.getY() < yMin){
			t.setPosition(t.getX(), yMin + t.getY());
		}
		t.baisserCrayon();
		seg.getPtEnd().x = t.getX();
		seg.getPtEnd().y = t.getY();
		seg.setColor(colors.containsKey(t.getColor())? colors.get(t.getColor()): Color.BLACK);

		if(!tortues.containsKey(t))
			tortues.put(t, new ArrayList<Segment>());
		if(t.isCrayon())
			tortues.get(t).add(seg);
	}
	
	public void setxMax(int xMax) {
		this.xMax = xMax;
	}

	public void setxMin(int xMin) {
		this.xMin = xMin;
	}

	public void setyMax(int yMax) {
		this.yMax = yMax;
	}

	public void setyMin(int yMin) {
		this.yMin = yMin;
	}

	public void drawForm(Form form){
		form.createForm(courante);
	}

	public Map<Tortue, List<Segment>> getTortues() {
		return tortues;
	}

	public void setTortues(Map<Tortue, List<Segment>> tortues) {
		this.tortues = tortues;
	}
	
	public static Controller getInstance(){
		return INSTANCE;
	}

	public Tortue getCourante() {
		return courante;
	}

	public void setCourante(Tortue courante) {
		this.courante = courante;
	}

	public static Map<Integer, Color> getColors() {
		return colors;
	}

	public Map<Tortue, Shape> getPolygon() {
		return polygon;
	}
}
