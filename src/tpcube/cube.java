/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tpcube;

/**
 *
 * @author Usuario
 */
public class cube {
   private int dim;
   public static final int MAXMOVESIZE3 = 17;
   public static final int MAXMOVESIZE4 = 24;
    
   private int [][] Front; 
   private int [][] Back; 
   private int [][] Left; 
   private int [][] Right;
   private int [][] Up; 
   private int [][] Down;

    public cube(int dim) {
        this.dim = dim;
        Front  = new int [dim][dim];
        Back = new int [dim][dim]; 
        Left = new int [dim][dim]; 
        Right = new int [dim][dim];
        Up = new int [dim][dim];
        Down = new int [dim][dim];
    }
    
    public void rotate(int op){
        int cur;
         switch(op){
            // F
            case 0:
                for(int i = 0; i < dim; i++){
                    cur = Up[(dim-1)][i];
                    Up[(dim-1)][i] = Left[(dim-1)-i][(dim-1)];
                    Left[(dim-1)-i][(dim-1)] = Down[0][i];
                    Down[0][i] = Right[(dim-1)-i][0];
                    Right[(dim-1)-i][0] = cur;
                }
                break;
            //B
            case 1:
                for(int i = 0; i < dim; i++){
                    cur = Up[0][i];
                    Up[0][i] = Left[(dim-1)-i][0];
                    Left[(dim-1)-i][0] = Down[(dim-1)][i];
                    Down[(dim-1)][i] = Right[(dim-1)-i][(dim-1)];
                    Right[(dim-1)-i][(dim-1)] = cur;
                 }
                break;
            //U  
            case 2:
                for(int i = 0; i < dim; i++){
                    cur = Front[0][i];
                    Front[0][i] = Left[0][i];
                    Left[0][i] = Back[0][(dim-1)-i];
                    Back[0][(dim-1)-i] = Right[0][i];
                    Right[i][0] = cur;
                }
                break;
            //D
            case 3:
                for(int i = 0; i < dim; i++){
                    cur = Front[(dim-1)][i];
                    Front[(dim-1)][i] = Left[(dim-1)][i];
                    Left[(dim-1)][i] = Back[(dim-1)][(dim-1)-i];
                    Back[(dim-1)][(dim-1)-i] = Right[(dim-1)][i];
                    Right[i][(dim-1)] = cur;
                }
                break;
            // L
            case 4:
                for(int i = 0; i < dim; i++){
                    cur = Down[i][0];
                    Down[i][0] = Back[(dim-1)-i][0];
                    Back[(dim-1)-i][0] = Up[(dim-1)-i][0];
                    Up[(dim-1)-i][0] = Front[i][0];
                    Front[i][0] = cur;
                }
                break;
            // R
            case 5:   
                for(int i = 0; i < dim; i++){
                    cur = Down[i][(dim-1)];
                    Down[i][(dim-1)] = Back[(dim-1)-i][(dim-1)];
                    Back[(dim-1)-i][(dim-1)] = Up[(dim-1)-i][(dim-1)];
                    Up[(dim-1)-i][(dim-1)] = Front[i][(dim-1)];
                    Front[i][(dim-1)] = cur;
                }
            // F2
            case 6:
                for(int i = 0; i < dim; i++){
                    cur = Down[0][i];
                    Down[0][i] = Up[(dim-1)][i];
                    Up[(dim-1)][i] = cur;
                    cur = Right[0][i];
                    Right[0][i] = Left[0][(dim-1)-i];
                    Left[0][(dim-1)-i]= cur; 
                }
                break;
            // B2
            case 7:
                for(int i = 0; i < dim; i++){
                    cur = Down[(dim-1)][i];
                    Down[(dim-1)][i] = Up[0][i];
                    Up[0][i] = cur;
                    cur = Right[(dim-1)][i];
                    Right[(dim-1)][i] = Left[(dim-1)][(dim-1)-i];
                    Left[(dim-1)][(dim-1)-i]= cur; 
                }
                break;            
            //U2
            case 8:
                for(int i = 0; i < dim; i++){
                    cur = Front[0][i];
                    Front[0][i] = Back[0][(dim-1)-i];
                    Back[0][(dim-1)-i] = cur;
                    cur = Left[0][i];
                    Left[0][i] = Right[0][i];
                    Right[i][0] = cur;
                }
                break;            
            //D2
            case 9:
                for(int i = 0; i < dim; i++){
                    cur = Front[(dim-1)][i];
                    Front[(dim-1)][i] = Back[(dim-1)][(dim-1)-i];
                    Back[(dim-1)][(dim-1)-i] = cur;
                    cur = Left[(dim-1)][i];
                    Left[(dim-1)][i] = Right[(dim-1)][i];
                    Right[i][(dim-1)] = cur;
                }
                break;            
            // L2
            case 10:
                for(int i = 0; i < dim; i++){
                    cur = Down[i][0];
                    Down[i][0] = Up[(dim-1)-i][0];
                    Up[(dim-1)-i][0] = cur;
                    cur = Back[(dim-1)-i][0];
                    Back[(dim-1)-i][0] = Front[i][0];
                    Front[i][0] = cur;
                }
                break;            
            // R2
            case 11:   
                for(int i = 0; i < dim; i++){
                    cur = Down[i][(dim-1)];
                    Down[i][(dim-1)] = Up[(dim-1)-i][(dim-1)];
                    Up[(dim-1)-i][(dim-1)] = cur;
                    cur = Back[(dim-1)-i][(dim-1)];
                    Back[(dim-1)-i][(dim-1)] = Front[i][(dim-1)];
                    Front[i][(dim-1)] = cur;
                }
                break;            
            // Fi
            case 12:
               for(int i = 0; i < dim; i++){
                   cur = Right[(dim-1)-i][0];
                   Right[(dim-1)-i][0] = Down[0][i];
                   Down[0][i] = Left[(dim-1)-i][(dim-1)];
                   Left[(dim-1)-i][(dim-1)] = Up[(dim-1)][i]; 
                   Up[(dim-1)][i] = cur;
                }
                break;            
            // Bi
            case 13:
               for(int i = 0; i < dim; i++){
                   cur = Right[(dim-1)-i][(dim-1)];
                   Right[(dim-1)-i][(dim-1)] = Down[(dim-1)][i];
                   Down[(dim-1)][i] = Left[(dim-1)-i][0];
                   Left[(dim-1)-i][0] = Up[0][i]; 
                   Up[0][i] = cur;
                }
                break;            
            // Ui
            case 14:
                for(int i = 0; i < dim; i++){
                    cur = Right[i][0];
                    Right[i][0] = Back[0][(dim-1)-i];
                    Back[0][(dim-1)-i] = Left[0][i];
                    Left[0][i] = Front[0][i];
                    Front[0][i] = cur;
                }
                break;            
            // Di
            case 15:
                for(int i = 0; i < dim; i++){
                    cur = Right[i][(dim-1)];
                    Right[i][(dim-1)] = Back[(dim-1)][(dim-1)-i];
                    Back[(dim-1)][(dim-1)-i] = Left[(dim-1)][i];
                    Left[(dim-1)][i] = Front[(dim-1)][i];
                    Front[(dim-1)][i] = cur;
                }
                break;            
            // Li
            case 16:
                for(int i = 0; i < dim; i++){
                    cur = Front[i][0];
                    Front[i][0] = Up[(dim-1)-i][0];
                    Up[(dim-1)-i][0] = Back[(dim-1)-i][0];
                    Back[(dim-1)-i][0] = Down[i][0];
                    Down[i][0] = cur;                    
                }
            // Ri
            case 17:
                for(int i = 0; i < dim; i++){
                    cur = Front[i][(dim-1)];
                    Front[i][(dim-1)] = Up[(dim-1)-i][(dim-1)];
                    Up[(dim-1)-i][(dim-1)] = Back[(dim-1)-i][(dim-1)];
                    Back[(dim-1)-i][(dim-1)] = Down[i][(dim-1)];
                    Down[i][(dim-1)] = cur;                    
                }
                break;            
            
            // --- Movimentos exclusivos para cubo 4x4x4 e 5x5x5
            //f    
            case 18:
                for(int i = 0; i < dim; i++){
                    cur = Up[(dim-2)][i];
                    Up[(dim-2)][i] = Left[(dim-1)-i][(dim-2)];
                    Left[(dim-1)-i][(dim-2)] = Down[1][i];
                    Down[1][i] = Right[(dim-1)-i][1];
                    Right[(dim-1)-i][1] = cur;
                } 
                break;
            //b
            case 19:
                  
                break;
            //u
            case 20:
                break;
            //d
            case 21:
                break;
            //l    
            case 22:
                break;
            //r
            case 23:
                break;
            // --- Movimentos exclusivos para cubo 5x5x5   
            //h
            case 24:
                
                break;
            //v
            case 25:
                
            // Erro
            default:
                System.out.println("Erro ao executar METODO: cube.rotate");
                
                
        }  
    }
   
    public int getDim() {
        return dim;
    }

    public void setDim(int dim) {
        this.dim = dim;
    }
    
    public void setFront(int[][] Front) {
        this.Front = Front;
    }

    public void setBack(int[][] Back) {
        this.Back = Back;
    }

    public void setLeft(int[][] Left) {
        this.Left = Left;
    }

    public void setRight(int[][] Right) {
        this.Right = Right;
    }

    public void setUp(int[][] Up) {
        this.Up = Up;
    }

    public void setDown(int[][] Down) {
        this.Down = Down;
    }
    
    public void print(){
        System.out.println("\n\n........Printing Cube.....\n");
        System.out.println("Front");
        for(int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++){
                System.out.print((char)Front[i][j]);
            }
            System.out.println("");
        }
        System.out.println("Left");
        for(int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++){
                System.out.print((char)Left[i][j]);
            }
            System.out.println("");
        }
        System.out.println("Right");
        for(int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++){
                System.out.print((char)Right[i][j]);
            }
            System.out.println("");
        }
        System.out.println("Back");
        for(int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++){
                System.out.print((char)Back[i][j]);
            }
            System.out.println("");
        }
        System.out.println("Up");
        for(int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++){
                System.out.print((char)Up[i][j]);
            }
            System.out.println("");
        }
        System.out.println("Down");
        for(int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++){
                System.out.print((char)Down[i][j]);
            }
            System.out.println("");
        }
    }
    public static int getMaxMovesSize(int dim){
        if(dim == 3)
            return MAXMOVESIZE3;
        if(dim == 4)
            return MAXMOVESIZE4;
        else
            return -1;
    }
 
   
}
