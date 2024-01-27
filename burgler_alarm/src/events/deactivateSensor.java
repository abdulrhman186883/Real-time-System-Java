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
public class deactivateSensor {
    private boolean clicked; 
    private int counter;

    public boolean isClicked() {
        return clicked;
    }

    public int getCounter() {
        return counter;
    }

    public deactivateSensor(boolean clicked, int counter) {
        this.clicked = clicked;
        this.counter = counter;
    }
  
}
