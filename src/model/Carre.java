package model;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import view.Segment;

public class Carre implements Form{

	@Override
	public void createForm(Tortue t, Integer... integers) {
		if(integers.length < 1) throw new IllegalArgumentException("Cette méthode attend un paramètre");
		for (int i=0;i<4;i++) {
			Controller.getInstance().createSegment(t, integers[0]);
			t.droite(90);
		}
	}
}
