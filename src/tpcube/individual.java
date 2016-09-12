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
    public cube in;
    
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
            maxSize += 2 - maxSize%2;
        }
            
        while(i < maxSize){
            
            move = rnd.nextInt(6);
            //move = 5;
            
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
    
    public void mutate(int maxMoveSize){
        // Efetua mutação de um gene randomico de posição randomica
        Random rnd = new Random();
        
        int pos = rnd.nextInt(moves.size());
        int newMove = rnd.nextInt(maxMoveSize);
        while(moves.get(pos) == newMove){
            newMove = rnd.nextInt(maxMoveSize);
        }
        moves.set(pos,newMove);
        
    }
    public int getFitness(){
        return fitness;
    }
    
    
    public void setFitness(){
        cube candidate = new cube(in.getDim());
        candidate.setMatrix(in.getCubeMatrix(), in.getDim());
        
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
        
        
        for(int x = 0; x < 6; x++){ 
            color = candidate.getCubeMatrix()[x][1][1];
            for(int i = 0; i < candidate.getDim(); i++){
                for(int j = 0; j < candidate.getDim(); j++){
                    if(color == candidate.getCubeMatrix()[x][i][j])
                        qtd++;
                }
            }
        }
        fitness = qtd;
    }
    
    @Override
    public String toString(){
        String str = "";
        for(int mov: moves){
            switch(mov){
            // F
            case 0:
                str+="F";
                break;
            //B
            case 1:
                str+="B";
                break;
            //U  
            case 2:
                str+="U";
                break;
            //D
            case 3:
                str+="D";
                break;
            // L
            case 4:
                str+="L";
                break;
            // R
            case 5:   
                str+="R";
                break;
            // F2
            case 6:
                str+="F2";
                break;
            // B2
            case 7:
                str+="B2";
                break;            
            //U2
            case 8:
                str+="U2";
                break;            
            //D2
            case 9:
                str+="D2";
                break;            
            // L2
            case 10:
                str+="L2";
                break;            
            // R2
            case 11:   
                str+="R2";
                break;            
            // Fi
            case 12:
                str+="Fi";
                break;            
            // Bi
            case 13:
                str+="Bi";
                break;            
            // Ui
            case 14:
                str+="Ui";
                break;            
            // Di
            case 15:
                str+="Di";
                break;            
            // Li
            case 16:
                str+="Li";
                break;
            // Ri
            case 17:
                str+="Ri";
                break;
            default:
                str+="MOV: "+mov; 
                break;    
            }
            
            str+= " ";
        }
        return str;
    }
    
    public static int FINALFITNESS(){
        return FINALFITNESS;
    }

    @Override
    public void run() {
       System.out.println("@@@"); 
       setFitness();
    }
}
