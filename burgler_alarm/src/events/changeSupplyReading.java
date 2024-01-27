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
public class changeSupplyReading {
    private int reading;

    public changeSupplyReading(int reading) {
        this.reading = reading;
    }

    public int getReading() {
        return reading;
    }
    
    
}
