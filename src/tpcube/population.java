/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tpcube;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author samoliveira
 */
public class population{
    // Lista de todos os individuos da população
    private ArrayList<individual> group;
    private ArrayList<individual> sons;
    private int size;

    public ArrayList<individual> getGroup() {
        return group;
    }
    
    public population(int size, int maxIndividualSize, int dim, cube in){
        this.size = size;
        
        group = new ArrayList<>(size);
        Random rnd = new Random(); 
        
        /* gera uma nova população de (size)individuos e com numero de movimentos 
        variando de 20 a  maxMoveSize movimentos
        */
        for(int i = 0; i < size; i++){
            group.add(new individual(in));
            group.get(i).initMoves(20+rnd.nextInt(maxIndividualSize), 17);
            System.out.println(group.get(i).toString());
        }
    }
    
    public void select(int k, double probCross, double probMut, int maxMoveSize, cube in, int elitismSize){
        // o objetivo deste metodo é selecionar de 2 em dois inviduos até gerar N(size) novos;
        individual parent1 ;
        individual parent2 ;
        Random rnd = new Random();
        boolean Empty = true;
        sons = new ArrayList<>();
        
        // --- gera uma nova população
        // -------- ELISTISMO -------
        // copiamos o melhor individuo para a nova geração
        System.out.println("SELECT\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        int best = 0, maxFitness;
        
        for(int j = 0; j < elitismSize; j++){
            maxFitness = -1;
            for(int i = 0; i < group.size(); i++){
                 if(maxFitness < group.get(i).getFitness()){
                     best = i;
                 }
             }
         //   System.out.println(group.get(best).toString());
            sons.add(group.remove(best));
            
         }
         if(sons.size()> 0){
            group.addAll(sons);    
             
         }
         //print();
         
        // ---- OPERACOES GENETICAS ----
        //enche a lista de filhos ate termos N(size) filhos;
        while(Empty){
            parent1 = tournament(k);
            parent2 = tournament(k);
            // cruzamento
            double nxt = rnd.nextDouble();
            if(nxt <  probCross && Empty){
                //faz o teste de limite populacional dentro do metodo crossover
                // se há espaço para apenas 1 individuo seleciona o de maior fitness
                crossover(in, parent1, parent2); 
             //   System.out.println("Crossover "+probCross+ "next: "+nxt+" Empty:" + Empty);
                if(sons.size() == size){
                    Empty = false;
                }
                
            }
            // mutação
            if(rnd.nextDouble() < probMut && Empty){
                parent1.mutate(cube.getMaxMovesSize(in.getDim()));
                sons.add(parent1);
                if(sons.size() == size)
                    Empty = false;
               // System.out.println("MUTOU parent1");
               // System.out.println(""+parent1.toString());
            }
            if(rnd.nextDouble() < probMut && Empty){
                parent2.mutate(cube.getMaxMovesSize(in.getDim()));
                sons.add(parent2);
                if(sons.size() == size)
                    Empty = false;
                //System.out.println("MUTOU parent2");
              //  System.out.println(""+parent2.toString());
            }
            
        }
        group.clear();
        group.addAll(sons);
        sons = new ArrayList<>(size);   
    }
    
    private int[] genListRandom(int n){
        // gera uma lista de n elementos aleatorios
        int []ListRand = new int[n];
        Random rnd = new Random();
        int x,y,aux;
        for(int i = 0; i < n; i++){
            ListRand[i] = i;
        }
         //minimo de swaps necessario é n/2 mas por segurança n pareceu bom ;-) ...
        for(int i = 0; i < n; i++){
            x = rnd.nextInt(n);
            y = rnd.nextInt(n);
            aux = ListRand[x];
            ListRand[x] = ListRand[y];
            ListRand[y] = aux;  
        }
        return ListRand;
    }
    
    public double[] getGroupFitness(){
        // retorna uma lista com todas as fitness, com o fim de calcular as estatisticas da população
        double []data = new double[group.size()];
        for(int i = 0; i < group.size(); i++){
            data[i] = group.get(i).getFitness();
        }
        return data;
    }
    
    public individual getBest(){
        // retorna o individuo com a melhor fitness;
        int maxFitness = -1;
        int best = 0;
        for(int i = 0; i < group.size(); i++){
            if(maxFitness < group.get(i).getFitness()){
                best = i;
                maxFitness = group.get(i).getFitness();
            }
        }
        return group.get(best);
    }
   
    public boolean setGroupFitness(){
        boolean finish = false;
        for(int i = 0; i < group.size(); i++){
            group.get(i).run();//threta hahaha
            System.out.println(""+group.get(i).toString());
        }
        for(int i = 0; i < group.size(); i++){
            try {
                group.get(i).join();//threta hahaha
                
            } catch (InterruptedException ex) {
                Logger.getLogger(population.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        group.get(0).in.print();
        if(getBest().getFitness() == individual.FINALFITNESS){
            finish = true;
        }
            
        return finish;
    }
    
    public void print(){
        System.out.println("--------------------printing group----------------");
        for(individual ind:group){
            System.out.println("-Fitness: " +ind.getFitness());
            System.out.println(ind.toString());
            
        }
    }
    
    private individual tournament(int k){
        /* torneio :
        apartir de uma lista de n elementos de 1-n com ordem aleatoria,
        seleciona os k primeiros elementos faz o torneio selecionando o individuo 
        com melhor fitness
        */
        Random rnd = new Random();
        ArrayList<Integer> IndList = new ArrayList<Integer>();
        int best = -1, MaxFitness = -1;
        
        int []ListRand;
        ListRand = genListRandom(group.size());
       // for(int l:ListRand){
           // System.out.println("L: "+ l);
       // }
        for(int i = 0; i < k; i++){
            if(MaxFitness < group.get(ListRand[i]).getFitness()){
                best = i;
                MaxFitness = group.get(ListRand[i]).getFitness();
             //   System.out.println("MAXFITNESS:::"+MaxFitness);
            }
        }  
     //   System.out.println("MAXFITNESS:::"+MaxFitness);
        return group.get(best); 
    }
    
    
    private void crossover(cube in, individual ind1, individual ind2){//[0-100]
        /* 
        crossover: 
        - copia os os filhos do individuo[1] ate crosspoint1 para o filho[1], 
            completa com os pontos depois de crosspont2 do individuo[2]
        - copia os os filhos do individuo[2] ate crosspoint2 para o filho[2], 
            completa com os pontos depois de crosspont2 do individuo[1]
        
        se existe apenas um espaço na população coloca o filho de maior Fitness
        */
 
        Random rnd = new Random();
        int crosspoint1, crosspoint2;
        
        individual son2 = new individual(in);
        individual son1 = new individual(in);
        
        crosspoint1 = rnd.nextInt(ind1.getMoves().size());
        crosspoint2 = rnd.nextInt(ind2.getMoves().size());
       // System.out.println("ind1.size():" + ind1.getMoves().size());
       // System.out.println("ind2.size():" + ind2.getMoves().size());
        for(int i = 0; i < crosspoint1; i++){
            son1.getMoves().add(ind1.getMoves().get(i));
          //  System.out.println("ind1 i: "+i + " Crosspoint1:" + crosspoint1);
          //  System.out.println(ind1.getMoves().get(i));
        }
        for(int i = 0; i < crosspoint2; i++){
            son2.getMoves().add(ind2.getMoves().get(i));
           // System.out.println("ind2 i: "+i + " Crosspoint2:" + crosspoint2);
           // System.out.println(ind2.getMoves().get(i));
        }
        for(int i = crosspoint2; i < ind2.getMoves().size(); i++){
            son1.getMoves().add(ind2.getMoves().get(i));
            //System.out.println("ind2 i: "+i + " Crosspoint2:" + crosspoint2);
            //System.out.println(ind2.getMoves().get(i));
        }
        for(int i = crosspoint1; i < ind1.getMoves().size(); i++){
            son2.getMoves().add(ind1.getMoves().get(i));
            //System.out.println("ind1 i: "+i + " Crosspoint1:" + crosspoint1);
            //System.out.println(ind1.getMoves().get(i));
        }
        // se cabe apenas um filho coloca o filho de maior fitness
        if(size == sons.size()-1){
            son1.setFitness();
            son2.setFitness();
            if(son1.getFitness() > son2.getFitness()){    
                sons.add(son1);
        //        System.out.println("ADDED SON CROSSOVER 1: " +son1.toString());
            }else{
                sons.add(son2);
          //      System.out.println("ADDED SON CROSSOVER 2: " +son2.toString());
            }
        }
        else{
            sons.add(son2);
            //System.out.println("ADDED SON CROSSOVER 2: " +son2.toString());
            sons.add(son1);
        //    System.out.println("ADDED SON CROSSOVER 1: " +son1.toString());
        }      
    }  
}
