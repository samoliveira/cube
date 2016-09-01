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
 * @author Usuario
 */
public class individual {
    private ArrayList<Integer> moves;
    
    public void genMoves(int size, int maxMoveSize){
        Random rnd = new Random();
        moves = new ArrayList<Integer>();
        for(int i = 0; i< size; i++){
            moves.add(rnd.nextInt(maxMoveSize));
        }
    }
    public void mutate(){
        
    }
    
}
