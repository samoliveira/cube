/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tpcube;


import java.util.Scanner;
/**
 *
 * @author samoliveira
 */
public class TpCube {

    /**
     * @param args the command line arguments
     */
    
      
    public static void main(String[] args) {
        // TODO code application logic here
        int dim;
        
        String str = "";
        int ch, c;
        Scanner sc = new Scanner(System.in);
        dim = sc.nextInt();
       
        System.out.println("dim:"+dim);
      
        cube cb = new cube(dim);
        char matrix[][] = new char[dim][dim];
        String mtx = "";
        
        for(int i  = 0; i < 6; i++){
            str = sc.next();
            System.out.println("str: "+ str);
            for(int j = 0; j < dim; j++){
                for(int k = 0; k < dim; k++){
                    mtx = sc.next();
                    matrix[j][k] = mtx.charAt(0);
                    System.out.print(mtx);
                    
                }
                System.out.println("");
            }
            switch (str) {
                case "Front":
                    cb.setFront(matrix);
                    break;
                case "Left":
                    cb.setLeft(matrix);
                    break;
                case "Right":
                    cb.setRight(matrix);
                      break;
                case "Back":
                    cb.setBack(matrix);
                      break;
                case "Up":
                    cb.setUp(matrix);
                       break;
                case "Down":
                    cb.setDown(matrix);
                       break;
                default:
                    System.out.println("Erro ao ler arquivo de entrada!!!\n");
                    System.out.println("str:"+str);
                    break;
            }
            matrix = new char[dim][dim];
        }
        
        cb.print();
        cb.print();
        
        if(args.length == 7){
            int epochs, size, elitismSize, k,maxIndividualSize;
            double probCross, probMut;
            epochs = Integer.parseInt(args[0]);
            size = Integer.parseInt(args[1]);
            probCross = Double.parseDouble(args[2]);
            probMut = Double.parseDouble(args[3]);
            k = Integer.parseInt(args[4]);
            elitismSize = Integer.parseInt(args[5]);
            maxIndividualSize = Integer.parseInt(args[6]);
            
            enviroment env;
            System.out.println("epochs:" + epochs+ "\nsize:" + size+ "\nk:" + k+ 
                    "\nelit:" + elitismSize+ "\nCross:" + probCross+ "\nMut:" + probMut); 
            
            env = new enviroment(epochs, size, maxIndividualSize, dim, probCross, probMut, k, cb, elitismSize);
           
            env.evolve();
            
        }
        else{
            System.out.println("Parametros errados");
        }
        
        
    }
    
}
