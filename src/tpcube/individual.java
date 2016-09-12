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
public class individual extends Thread{
    private ArrayList<Integer> moves;
    private int fitness;
    private cube in;
    
    public final static int FINALFITNESS = 54;

    public individual(cube in) {
        this.in = new cube(in.getDim());
        this.in.setCube(in);
        this.moves = new ArrayList<Integer>();
    }
    
    
    
    public ArrayList<Integer> getMoves() {
        return moves;
    }
 
    public void setMoves(ArrayList<Integer> moves) {
        this.moves = moves;
    }
    
    
    public void initMoves(int maxSize, int maxMoveNumber){
        /* todos os movimentos iniciais independente do tamanho do individuo 
        nao alteram o cubo magico a fim de que um cubo de solução trivial seja 
        imediatamente resolvido. Para tal cada individuo sera iniciado com um
        valor ate maxSize multiplo de 2, visto que são adicionados 2 ou 4 movimentos
        por vez.
        */
        
        Random rnd = new Random();
        int move;
        moves = new ArrayList<Integer>();
        
        //size = size - size%4;
        int i = 0;
        if(!(maxSize%2 == 0)){
            maxSize++;
        }
            
        while(i < maxSize){
            
            move = rnd.nextInt(maxMoveNumber+1);
            if(move > maxMoveNumber)    
                System.out.println("MAXSIZE: "+maxSize+" i: "+i+" MOVE: "+ move);
            //System.out.println("OIOIOIOIOIOI");
            //System.out.println(maxSize);
            
            switch(rnd.nextInt(2)){
                case 0: //inversos ou 2 movimentos duplos
                    if(i+2 <= maxSize){
                        if(move <= 5){
                            moves.add(move);
                            moves.add(move+12);
                        }
                        if(move >= 12 && move <= maxMoveNumber){
                            moves.add(move);
                            moves.add(move-12);
                        }
                        // dobra movimentos duplos, pois nao tem versão inversa
                        if(move >=6 && move <= 11){
                            moves.add(move);
                            moves.add(move);
                        }
                        i+=2;
                    }
                    break;        
                case 1: // 4 iguais 
                    
                    if(i+4 <= maxSize){
                        moves.add(move);
                        moves.add(move);
                        moves.add(move);
                        moves.add(move);
                        i+=4;
                    }
                    break;   
            }
            
        }
        //System.out.println("MAXSIZE: "+ maxSize+" __i :"+ i);
    }
    
    public void mutate(int maxMoveSize, cube in){
        // Efetua mutação de um gene randomico de posição randomica
        Random rnd = new Random();
        
        int pos = rnd.nextInt(moves.size());
        int newMove = rnd.nextInt(maxMoveSize);
        while(moves.get(pos) == newMove){
            newMove = rnd.nextInt(maxMoveSize);
        }
        moves.set(pos,newMove);
        setFitness();
    }
    public int getFitness(){
        return fitness;
    }
    
    
    public void setFitness(){
        cube candidate = new cube(in.getDim());
        candidate.setCube(in);
        
        // rotaciona o cubo até chegar na configuração final
        
        for(int mov:moves){
            if(mov > 17){
                //System.out.println("MOVE:" + mov);
                
            }
            
            candidate.rotate(mov);
        }
        
        /* a fitness sera definida como o somatório do número máximo de quadrados 
        da mesma cor que estão em cada um dos lados do cubo.
        */
        int qtd = 0;
        char color;
        char cbMatrix[][][] = new char [6][candidate.getDim()][candidate.getDim()];
        cbMatrix = candidate.getCubeMatrix();
        for(int x = 0; x < 6; x++){ 
            color = cbMatrix[x][1][1];
            for(int i = 0; i < candidate.getDim(); i++){
                for(int j = 0; j < candidate.getDim(); j++){
                    if(color == cbMatrix[x][i][j])
                        qtd++;
                }
            }
        }
        fitness = qtd;
    }
    
    public static int FINALFITNESS(){
        return FINALFITNESS;
    }

    @Override
    public void run() {
       setFitness();
    }
}
