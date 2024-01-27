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
public class stopAlarm {
    private boolean stop;
   public  stopAlarm(boolean stop){
        this.stop = stop;
    }

    public boolean isStop() {
        return stop;
    }
    
    
}
