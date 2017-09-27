package com.processing.sketch;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

import java.awt.*;

public class Rocket {
    private PApplet p;
    private PVector pos;
    private PVector vel = new PVector();
    private PVector acc = new PVector();
    private DNA dna;
    private double fitness;
    private boolean crashed = false;
    private boolean finished = false;
    private int finishFrame = 0;

    public Rocket(PApplet parent) {
        dna = new DNA();
        p = parent;
        pos = new PVector(p.width / 2,p.height - 50);
    }

    public Rocket(PApplet parent, DNA dna) {
        this.dna = dna;
        p = parent;
        pos = new PVector(p.width / 2,p.height - 50);
    }

    public void update() {
        if(!finished && !crashed) {
            acc = dna.get(Sketch.frame);
            vel.add(acc);
            pos.add(vel);
            vel.limit(4);
            if (PApplet.dist(pos.x, pos.y, Sketch.orb.x + Sketch.orbSize / 2, Sketch.orb.y + Sketch.orbSize / 2) <= 16) {
                finished = true;
                finishFrame = Sketch.frame;
            }
            if(pos.x < 0 || pos.y < 0 || pos.x > p.width || pos.y > p.height) {
                crashed = true;
            }
        }
    }

    public void draw() {
    	p.pushMatrix();
    	p.translate(pos.x, pos.y);
    	p.rotate(vel.heading());
        p.rect(0,0,15, 5);
        p.popMatrix();
    }

    public double calcFitness() {
        double dist = PApplet.dist(pos.x, pos.y, Sketch.orb.x, Sketch.orb.y);
        fitness = (p.height - dist) * 10;
        if(finished) {
            fitness += 1000;
        }
        if(crashed) {
            fitness -= 100;
        }
		fitness += (Sketch.LIFESPAN - finishFrame) * 20;
        return fitness;
    }

    public DNA getDna() {
        return dna;
    }
}
