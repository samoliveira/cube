/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tpcube;


import java.util.Scanner;
/**
 *
 * @author Usuario
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
        int matrix[][] = new int[dim][dim];
        String mtx = "";
        
        for(int i  = 0; i < 6; i++){
            str = sc.next();
            System.out.println("str: "+ str);
            for(int j = 0; j < dim; j++){
                for(int k = 0; k < dim; k++){
                    mtx = sc.next();
                    matrix[j][k] = (int)mtx.charAt(0);
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
            matrix = new int[dim][dim];
        }
        cb.rotate(2);
        cb.print();
        
    }
    
}
