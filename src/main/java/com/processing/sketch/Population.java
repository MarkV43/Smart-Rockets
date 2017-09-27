package com.processing.sketch;

import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;

public class Population {
    private PApplet p;
    public static final int SIZE = 300;
    private ArrayList<Rocket> rockets;
    private ArrayList<Rocket> matingPool;


    public Population(PApplet parent) {
        rockets = new ArrayList<>(SIZE);
        p = parent;
        for (int i = 0; i < SIZE; i++) {
            rockets.add(i, new Rocket(p));
        }
    }

    public void run() {
    	p.fill(255, 150);
        for (int i = 0; i < SIZE; i++) {
            rockets.get(i).update();
            rockets.get(i).draw();
        }
    }

    public void evaluate() {
        matingPool = new ArrayList<>();
        for (Rocket rocket : rockets) {
            double fitness = rocket.calcFitness();
            for (int j = 0; j < ((int) fitness); j++) {
                matingPool.add(rocket);
            }
        }
    }

    public void selection() {
        ArrayList<Rocket> newRockets = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            DNA A = matingPool.get((int) p.random(matingPool.size())).getDna();
            DNA B = matingPool.get((int) p.random(matingPool.size())).getDna();
            DNA C = A.crossover(B);
            newRockets.add(new Rocket(p, C));
        }
        rockets = newRockets;
    }
}
