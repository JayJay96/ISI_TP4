package model;

import controller.Controller;

public class Poly implements Form{

	@Override
	public void createForm(Tortue t, Integer... integers) {
		if(integers.length < 2) throw new IllegalArgumentException("Cette méthode attend deux paramètres");
		Controller controller = Controller.getInstance();
		for (int j=0;j<integers[1];j++) {
			controller.createSegment(t, integers[0]);
			t.droite(360/integers[1]);
		}
	}
}
