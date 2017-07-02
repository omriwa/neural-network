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
public class Layer {
    
    private ArrayList<Neuron> layerNeurals = null;
    
    public Layer(int neuralsNum , int type){
        layerNeurals = new ArrayList<>();
        for(int i = 0 ; i < neuralsNum ; i++)
            layerNeurals.add(new Neuron(type));
    }
    
    public ArrayList<Neuron> getNeuronsLayer(){
        return layerNeurals;
    }
    
}
