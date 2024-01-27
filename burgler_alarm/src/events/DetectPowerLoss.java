/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

/**
 *
 * @author dell
 */
public class DetectPowerLoss {
       private int current_reading;

    public int getCurrent_reading() {
        return current_reading;
    }

    public DetectPowerLoss(int current_reading) {
        this.current_reading = current_reading;
    }
    
}
