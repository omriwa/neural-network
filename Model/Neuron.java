/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author omri
 */
public class Neuron {
    
    public static final int HARDLIMIT = 0 , LINEAR = 1 , SIGMOID = 2;
    private ArrayList<Neuron> neighbors = null;
    private double weight = 1 , bias = 0 , value = 0;
    private int neuralType = 0;
    
    
    public Neuron(int type){
        neighbors = new ArrayList<>();
        this.neuralType = type;
        Random random = new Random();
//        setDelta();
        setScalar();
    }
    
    public String toString(){
        return(
                "Neuron:\n"
                + "value-" + value
                + "\n"
                + "delta-" + bias
                + "\n"
                + "scalar-" + weight
                + "\n"
//                + printNeighbors()
        );       
    }
    
    private void addValToNeuron(Double v){
        value += v;
    }
    
    private String printNeighbors(){
        String output = "neighbors:\n";
        for(Neuron n : neighbors)
            output += n.getValue();
        return output;
    }
    
    public double getValue() {
        return value;
    }
    
    public void setValue(double v){
        value = v;
    }
    
    public void transferToNeighbors(){
        //transfer value with func
        transferValue();
        //transfer the value to the neighbors
        for(Neuron neighbor : neighbors)
            neighbor.addValToNeuron(value);
    }
    
    public void addNeural(Neuron n){
        neighbors.add(n);
    }
    
    private void hardLimitTransfer(){
        double outcome = (weight * value) + bias;
        if(outcome < 0)
            value = 0;
        else
            value = 1;
    }
    
    private void linearTransfer(){}
    
    private void sigmoidTransfer(){
        value = 1/(1 - Math.pow(Math.E, -value));
    }
    
    private void transferValue(){
        //check the neuron type and uses the right transfer func
        if(neuralType == HARDLIMIT)
            hardLimitTransfer();
        else if(neuralType == LINEAR)
            linearTransfer();
        else
            sigmoidTransfer();
        //transfer the value to the neighbors
        for(Neuron n : neighbors)
            n.addValToNeuron(value);  
    }    
    
    private void setDelta(){
        bias = getRandom();
    }
    
    private void setScalar(){
        Random random = new Random();
        weight = getRandom();
    }
    /*get random in range of -1 and 1*/
    private double getRandom(){
        Random random = new Random();
            
        return random.nextDouble();
    }
}
