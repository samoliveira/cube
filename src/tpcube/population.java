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
public class population {
    // Lista de todos os individuos da população
    ArrayList<individual> group;
    ArrayList<individual> sons;
    private int size;
    
    public population(int size, int maxsize, int dim){
        this.size = size;
        
        group = new ArrayList<>(size);
        Random rnd = new Random(); 
        for(int i = 0; i < size; i++){
            group.add(new individual());
            group.get(i).genMoves(rnd.nextInt(maxsize), cube.getMaxMovesSize(dim));
        }
    }
    public void crossover(){
        
    }
    
    
}
