package model;

import controller.Controller;

public class Spiral implements Form{

	@Override
	public void createForm(Tortue t, Integer... integers) {
		if(integers.length < 3) throw new IllegalArgumentException("Cette m�thode attend trois param�tres");
		for (int i = 0; i < integers[1]; i++) {
			t.couleur(t.getColor()+1);
			Controller.getInstance().createSegment(t, integers[0]);
			t.droite(360/integers[2]);
			integers[0] = integers[0]+1;
		}
	}
}
