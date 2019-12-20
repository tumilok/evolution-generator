package com.tumilok.main;

import java.util.Arrays;

public class Genes {

    private final int genesNumber = 32;
    private final int [] genes = new int[genesNumber];

    public Genes() {
        for (int i = 0; i < this.genesNumber; i++) {
            if (i < 8) {
                this.genes[i] = i;
            } else {
                this.genes[i] = (int) (Math.random() * 8);
            }
        }
        Arrays.sort(genes);
    }

    public Genes(int []genes) {
        for (int i = 0; i < this.genesNumber; i++) {
            this.genes[i] = genes[i];
         }
    }

    public String toString() {
        String toReturn = "";
        for (int i = 0; i < this.genesNumber; i++) {
            toReturn += " " + this.genes[i];
        }
        return toReturn;
    }

    public int chooseDirection() {
        return genes[(int)(Math.random() * 32)];
    }

    public int [] getGenes() {
        return this.genes;
    }
}
