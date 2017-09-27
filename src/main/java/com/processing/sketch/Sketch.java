package com.processing.sketch;

import processing.core.*;

public class Sketch extends PApplet {

	public static final int LIFESPAN = 200;
	public static int frame = 0;
	public static int orbSize = 16;
	public static PVector orb;

	private Population population;
	private int generation = 0;

    @Override
    public void settings() {
        size(800,600);
    }

    @Override
    public void setup() {
	    population = new Population(this);
	    orb = new PVector(width / 2, 50);
	    noStroke();
	    frameRate(120);
    }

    @Override
    public void draw() {
    	background(13);
    	rectMode(PConstants.CORNER);
	    fill(255, 0, 0);
	    rect(0, 150, 400, 20);
	    rect(400, 400, 400, 20);
	    rectMode(PConstants.CENTER);
	    fill(0, 255, 0);
	    ellipse(orb.x, orb.y, 32, 32);
		population.run();
		fill(255);
		text(frame, 5, 15);
		text(generation, 5, 30);
		frame++;
		if(frame == LIFESPAN) {
			frame = 0;
			generation++;
			population.evaluate();
			population.selection();
		}
    }
}
