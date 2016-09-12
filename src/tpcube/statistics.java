/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tpcube;

import java.util.Arrays;

/**
 *
 * @author samoliveira
 */
public class statistics 
{
    double[] data;
    int size;
    private individual best;
    boolean Solved;
    public statistics(double[] data, individual best) 
    {
        this.data = data;
        size = data.length;
        this.best = best;
    }   

    double getMean()
    {
        double sum = 0.0;
        for(double a : data)
            sum += a;
        return sum/size;
    }

    double getVariance()
    {
        double mean = getMean();
        double temp = 0;
        for(double a :data)
            temp += (a-mean)*(a-mean);
        return temp/size;
    }
    
    double getStdDev()
    {
        return Math.sqrt(getVariance());
    }

    public double median() 
    {
       Arrays.sort(data);
       
       if (data.length % 2 == 0) 
       {
          return (data[(data.length / 2) - 1] + data[data.length / 2]) / 2.0;
       } 
       else 
       {
          return data[data.length / 2];
       }
    }
    public individual getBest(){
        return best;
    }
    
    
}
