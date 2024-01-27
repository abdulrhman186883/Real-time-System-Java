/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import burgler_alarm.Config;
import events.InvalidFingerPrint;
import events.detectFingerPrint;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class FingerPrintSensor {

    ArrayList<String> fingerPrints;
    private int entry_attempts = 0;
    private String fingerPrint;

    public ArrayList<String> getFingerPrints() {
        return fingerPrints;
    }

    public void setFingerPrints(ArrayList<String> fingerPrints) {
        this.fingerPrints = fingerPrints;
    }

    public void setEntry_attempts(int entry_attempts) {
        this.entry_attempts = entry_attempts;
    }

    public int getEntry_attempts() {
        return entry_attempts;
    }

    public FingerPrintSensor() {

        this.fingerPrints = new ArrayList<>();
        this.fingerPrints.add("Mostafa");
        this.fingerPrints.add("Ragab");
        this.fingerPrints.add("Belal");

    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

//    public boolean AddFingerPrint(String fingerPrint) {
//        this.fingerPrints.add(fingerPrint);
//        return true;
//    }
    public boolean RemoveFingerPrint(String fingerPrint) {
        this.fingerPrints.remove(fingerPrint);
        return true;
    }

    public boolean checkFingerPrint(String fingerPrint) {
        return this.fingerPrints.contains(fingerPrint);
    }

//    public void runn() {
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(detectFingerPrint.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                String[] fingers = new String[]{"Belaf", "Belad", "Belal"};
//
//                for (int i = 0; i <= 3; i++) {
//
//                    setEntry_attempts(getEntry_attempts() + 1);
//                    if (getEntry_attempts() != 0) {
//                        if(i!=3){
//                        Config.sendEvent(new detectFingerPrint(fingers[i]));
//                        }
//                        if (i == 3) {
//                            Config.sendEvent(new InvalidFingerPrint(true));
//                            break;
//                        }
//
//                    } else {
//                        break;
//
//                    }
//                    if (getEntry_attempts() == 0) {
//                        break;
//                    }
//                }
//            }
//
//        }
//        ).start();
//
//    }
}
