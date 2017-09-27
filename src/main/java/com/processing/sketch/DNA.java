package com.processing.sketch;

import processing.core.PVector;

import java.util.ArrayList;

public class DNA {
    private ArrayList<PVector> genes;
    public DNA() {
        genes = new ArrayList<>(Sketch.LIFESPAN);
        for (int i = 0; i < Sketch.LIFESPAN; i++) {
            genes.add(i, PVector.random2D());
        }
    }

    public DNA(ArrayList<PVector> genes) {
        this.genes = genes;
    }

    public PVector get(int index) {
        return genes.get(index);
    }

    public DNA crossover(DNA partner) {
        ArrayList<PVector> newGenes = new ArrayList<>(Sketch.LIFESPAN);
        for (int i = 0; i < Sketch.LIFESPAN; i++) {
            PVector gene;
            if(Math.random() > 0.99) {
                gene = PVector.random2D();
            } else {
                if(Math.random() > 0.5) {
                    gene = get(i);
                } else {
                    gene = partner.get(i);
                }
            }
            newGenes.add(i, gene);
        }
        return new DNA(newGenes);
    }
}
