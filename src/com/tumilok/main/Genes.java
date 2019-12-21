package com.tumilok.main;

import java.util.Arrays;

public class Genes {

    private final int genesNumber = 32;
    private final int [] genes = new int[genesNumber];

    public Genes() {
        for (int i = 0; i < this.genesNumber; i++) {
            this.genes[i] = (int) (Math.random() * 8);
        }
        genesCheck();
    }

    public Genes(int []genes) {
        System.arraycopy(genes, 0, this.genes, 0, genesNumber);
        genesCheck();
    }

    public String toString() { return Arrays.toString(this.genes); }

    public void genesCheck() {
        Arrays.sort(genes);
        for (int i = 0; i < genes.length - 1; i++) {
            if (genes[i] != genes[i + 1] && genes[i] + 1 != genes[i + 1]) {
                genes[(int)(Math.random() * genesNumber)] = genes[i] + 1;
                genesCheck();
            }
        }
    }

    public int chooseDirection() {
        return genes[(int)(Math.random() * 32)];
    }

    public int [] getGenes() { return this.genes; }
}
