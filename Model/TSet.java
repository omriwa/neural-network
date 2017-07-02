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
public class TSet {
    
    private int output = 0;
    private ArrayList <Integer> inputs = null;
    
    public TSet(int out , int ... inputs){
        this.inputs = new ArrayList<>();
        for(int input : inputs)
            this.inputs.add(input);
        output = out;
    }

    public int getInputI(int i){
        return inputs.get(i);
    }
    
    public int getOutput() {
        return output;
    }



    public void setOutput(int output) {
        this.output = output;
    } 
    
    public static TSet [] getXorTrainingSet(){
        int setNum = 0;
        TSet xorSet [] = new TSet[4];
            for(int i = 0 ; i < 2 ; i++)
                for(int j = 0 ; j < 2 ; j++){
                    xorSet[setNum] = new TSet(i, j ,(i ^ j));
                    setNum++;
                }
        return xorSet;
    }
}
