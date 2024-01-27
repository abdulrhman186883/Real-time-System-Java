/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class Alarm {

    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public Alarm() {
        this.enabled = false;
    }

    public void stopAlarm() {
        this.enabled = false;
    }

    public void raiseAlarm() {
        this.setEnabled(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (enabled) {
                    System.out.println("Help!");

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Alarm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
