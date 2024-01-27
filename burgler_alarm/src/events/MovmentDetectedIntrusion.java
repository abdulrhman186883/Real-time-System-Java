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
public class MovmentDetectedIntrusion {
       private int current_reading;
       private int id;

    public int getCurrent_reading() {
        return current_reading;
    }

    public MovmentDetectedIntrusion(int current_reading,int id) {
        this.current_reading = current_reading;
        this.id = id;
    }
    public int getId(){
    return this.id;
    }
}
