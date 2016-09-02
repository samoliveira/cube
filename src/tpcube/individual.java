/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tpcube;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author samoliveira
 */
public class individual {
    private ArrayList<Integer> moves;
    private int fitness;
    
    public void genMoves(int size, int maxMoveSize){
        Random rnd = new Random();
        moves = new ArrayList<Integer>();
        for(int i = 0; i< size; i++){
            moves.add(rnd.nextInt(maxMoveSize));
        }
    }
    
    // Efetua mutação de um gene randomico de posição randomica
    public void mutate(int prob, int maxMoveSize){
        Random rnd = new Random();
        if(prob > rnd.nextInt(100)){
            int pos = rnd.nextInt(moves.size());
            int newMove = rnd.nextInt(maxMoveSize);
            while(moves.get(pos) == newMove){
                newMove = rnd.nextInt(maxMoveSize);
            }
            moves.set(pos,newMove);
        }
    }
    public int getFitness(){
        return fitness;
    }
    public void setFitness(cube in){
        cube candidate = new cube(in.getDim());
        candidate.setCube(in);
        
        
        for(int i = 0; i < in.getDim(); i++){
            
        }
    }
           
}
