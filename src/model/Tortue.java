package model;

// package logo;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


/*************************************************************************

	Un petit Logo minimal qui devra etre ameliore par la suite

	Source originale : J. Ferber - 1999-2001

			   Cours de DESS TNI - Montpellier II

	@version 2.0
	@date 25/09/2001

**************************************************************************/


public class Tortue implements Comparable<Tortue>
{
	public static final int rp=10, rb=5; // Taille de la pointe et de la base de la fleche
	public static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)
	
	private Integer id;
	private int x, y;	
	private int dir;
	private boolean crayon; 
	private int coul;
	private int arrowColor;
	
	public void setColor(int n) {coul = n;}
	public int getColor() {return coul;}

	public Tortue(Integer id, Integer arrowColor) {
		this.id = id;
		this.arrowColor = arrowColor;
		reset();
	}

	public void reset() {
		x = 0;
		y = 0;
		dir = -90;
		coul = 0;
		crayon = true;
  	}

	public void setPosition(int newX, int newY) {
		x = newX;
		y = newY;
	}

	public void avancer(int dist) {
		int newX = (int) Math.round(x+dist*Math.cos(ratioDegRad*dir));
		int newY = (int) Math.round(y+dist*Math.sin(ratioDegRad*dir));
		x = newX;
		y = newY;
	}

	public void droite(int ang) {
		dir = (dir + ang) % 360;
	}

	public void gauche(int ang) {
		dir = (dir - ang) % 360;
	}

	public void baisserCrayon() {
		crayon = true;
	}

	public void leverCrayon() {
		crayon = false;
	}

	public void couleur(int n) {
		coul = n % 12;
	}

	public void couleurSuivante() {
	 	couleur(coul+1);
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDir() {
		return dir;
	}
	public void setDir(int dir) {
		this.dir = dir;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tortue other = (Tortue) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public boolean isCrayon() {
		return crayon;
	}
	public void setCrayon(boolean crayon) {
		this.crayon = crayon;
	}
	public int getArrowColor() {
		return arrowColor;
	}
	public void setArrowColor(int arrowColor) {
		this.arrowColor = arrowColor;
	}
	@Override
	public int compareTo(Tortue o) {
		if(o.id == this.id){
			return 0;
		}
		return 1;
	}
}
