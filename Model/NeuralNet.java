/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author omri
 */
public class NeuralNet {
    
    public static final int XOR_NET = 0;
    private ArrayList<Layer> layers = null;
    private TSet trainingSet[] = null;
    
    public NeuralNet(int netType){
        if(netType == XOR_NET){
            layers = new ArrayList<>();
            layers.add(new Layer( 2 , Neuron.SIGMOID));//input layer
            layers.add(new Layer( 3 , Neuron.SIGMOID));//hidden layer
            layers.add(new Layer( 2 , Neuron.SIGMOID));//output layer
        }
        trainingSet = TSet.getXorTrainingSet();
    }

    /*connect each two layers of neurons*/
    private void initNeighbors() {
        for (int i = 0; i < layers.size() - 1; i++) {
            //get every neuron in current layer
            Layer curLayer = layers.get(i);
            //get every neuron in next layer
            Layer nextLayer = layers.get(i + 1);
            //set the next neuron layer to be neighbor of the previous
            for (Neuron curNeuron : curLayer.getNeuronsLayer()) {
                for (Neuron nextNeuron : nextLayer.getNeuronsLayer()) {
                    curNeuron.addNeural(nextNeuron);
                }
            }
        }
    }

    public void useInput(TSet set) {
        for(int i = 0 ; i < layers.get(0).getNeuronsLayer().size() ; i++){
            layers.get(0).getNeuronsLayer().get(i).setValue(set.getInputI(i));
        }
    }
    
    public TSet [] getTrainingSet(){
        return trainingSet;
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < layers.size(); i++) {
            output +=("Layer num:" + i + "\n");
            for (Neuron neuron : layers.get(i).getNeuronsLayer()) {
                output += (neuron + "\n");
            }
        }
        return output;
    }
    
    public void propagateInputs(){
        for(int i = 0 ; i < layers.size(); i++){
            for(Neuron neuron : layers.get(i).getNeuronsLayer())
                neuron.transferToNeighbors();
        }
    }
    
    public String getOutput(){
        ArrayList <Double> output = new ArrayList<>();
        for(Neuron neuron : layers.get(2).getNeuronsLayer())
            output.add(neuron.getValue());
        Double arr [] = new Double [output.size()];
        String out = "";
        for(Double num : output)
            out += num + " ";
        return out;
    }

    public static void main(String[] args) {
        NeuralNet xorNet = new NeuralNet(XOR_NET);
        System.out.println(xorNet);
        xorNet.useInput(xorNet.getTrainingSet()[0]);
        xorNet.propagateInputs();
        System.out.println(xorNet);        
    }

}
