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
public class cube {
   private int dim;
   private boolean isDimSet;
   
   public static final int MAXMOVESIZE3 = 17;
   public static final int MAXMOVESIZE4 = 24;
  
   public static char white = 'W';
   public static char orange = 'O';
   public static char red = 'R';
   public static char green = 'G';
   public static char blue = 'B';
   public static char yellow = 'Y'; 
           
   private char [][] Front; 
   private char [][] Back; 
   private char [][] Left; 
   private char [][] Right;
   private char [][] Up; 
   private char [][] Down;

    public cube(int dim) {
        this.dim = dim;
        isDimSet = true;
        Front  = new char [dim][dim];
        Back = new char [dim][dim]; 
        Left = new char [dim][dim]; 
        Right = new char [dim][dim];
        Up = new char [dim][dim];
        Down = new char [dim][dim];
    }
    
    
    public void rotate(int op){
        char cur;
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
                    Right[0][i] = cur;
                }
                break;
            //D
            case 3:
                for(int i = 0; i < dim; i++){
                    cur = Front[(dim-1)][i];
                    Front[(dim-1)][i] = Left[(dim-1)][i];
                    Left[(dim-1)][i] = Back[(dim-1)][(dim-1)-i];
                    Back[(dim-1)][(dim-1)-i] = Right[(dim-1)][i];
                    Right[(dim-1)][i] = cur;
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
                break;
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
                    Right[0][i] = cur;
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
                    Right[(dim-1)][i] = cur;
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
                    cur = Right[0][i];
                    Right[0][i] = Back[0][(dim-1)-i];
                    Back[0][(dim-1)-i] = Left[0][i];
                    Left[0][i] = Front[0][i];
                    Front[0][i] = cur;
                }
                break;            
            // Di
            case 15:
                for(int i = 0; i < dim; i++){
                    cur = Right[(dim-1)][i];
                    Right[(dim-1)][i] = Back[(dim-1)][(dim-1)-i];
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
                break;
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

            /*
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
            */        
            default:
                System.out.println("............................................"); 
               System.out.println("Erro ao executar METODO: cube.rotate __op: " + op);
                
               break;
                
        }  
    }
    
    public int getDim() {
        return dim;
    }
    public void setMatrix(char[][][] matrix, int dim){
        this.dim = dim;
        Front = matrix[0];
        Left = matrix[1] ;
       Right = matrix[2];
       Back = matrix[3];
       Up = matrix[4] ;
      Down =  matrix[5];
    }
    
    
    
    public char [][][] getCubeMatrix(){
        char matrix[][][] =  new char[6][dim][dim];
        matrix[0] = Front.clone();
        matrix[1] = Left.clone();
        matrix[2] = Right.clone();
        matrix[3] = Back.clone();
        matrix[4] = Up.clone();
        matrix[5] = Down.clone();
        return matrix;
    }
    
    public void setCube(cube cb){ 
        setDim(cb.getDim());
        setFront(cb.getFront());
        setLeft(cb.getLeft());
        setRight(cb.getRight());
        setBack(cb.getBack());
        setUp(cb.getUp());
        setDown(cb.getDown());
    }
    public void setDim(int dim) {
        this.dim = dim;
        isDimSet = true;
    }
    
    public void setFront(char[][] Front) {     
        for(int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++){
                this.Front[i][j] = Front[i][j];
            }
        }  
     
    }

    public void setBack(char[][] Back) {
        for(int i = 0; i < dim; i++){
           for(int j = 0; j < dim; j++){
               this.Back[i][j] = Back[i][j];
           }
        }
    }

    public void setLeft(char[][] Left) {
        for(int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++){
                this.Left[i][j] = Left[i][j];
            }
        }         
    }

    public void setRight(char[][] Right) {
        for(int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++){
                this.Right[i][j] = Right[i][j];
            }
        }
    }

    public void setUp(char[][] Up) {
        for(int i = 0; i < dim; i++){
            for(int j = 0; j < dim; j++){
                this.Up[i][j] = Up[i][j];
            }
        }
    }

    public void setDown(char[][] Down) {
        for(int i = 0; i < dim; i++){
           for(int j = 0; j < dim; j++){
               this.Down[i][j] = Down[i][j];
           }
        }    
    }

    public char[][] getFront() {
        return Front;
    }

    public char[][] getBack() {
        return Back;
    }

    public char[][] getLeft() {
        return Left;
    }

    public char[][] getRight() {
        return Right;
    }

    public char[][] getUp() {
        return Up;
    }

    public char[][] getDown() {
        return Down;
    }

    public boolean isIsDimSet() {
        return isDimSet;
    }
    
    

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void print(){
        
        System.out.println("\n........Printing Cube: ["+toString()+"].....\n");
        System.out.println("---HASH---: "+hashCode());
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
