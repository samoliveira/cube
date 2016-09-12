/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tpcube;

/**
 *
 * @author samoliveira
 */
public class enviroment {
    private double probCross, probMut;
    private int epochs, size, maxMoveSize, dim, k;
    private cube in;
    private int elitismSize;
    public enviroment(int epochs, int size, int maxMoveSize, int dim, double probCross, double probMut, int k, cube in, int elitismSize){
        this.epochs = epochs;
        this.probCross = probCross;
        this.probMut = probMut;
        this.size = size;
        this.maxMoveSize = maxMoveSize;
        this.dim = dim;
        this.k = k;
        this.in = new cube(dim);
        this.in.setCube(in);
        this.elitismSize = elitismSize;
    }
    
    public int evolve(){
        population p = new population(size, maxMoveSize, dim, in);
        statistics st;
        int i = 0;
        for(i = 0; i < epochs; i++){
            if(!p.setGroupFitness()){
                p.select(k, probCross, probMut, maxMoveSize, in, elitismSize);
                st = new statistics(p.getGroupFitness(), p.getBest());
                System.out.println("BEST:"+ p.getBest().getFitness());
            }
            else{
                System.out.println("IIIIIIII:"+i);
                //st.Solved = true;
                return i;
            }
        }
        return i;
    }
    
    
}
