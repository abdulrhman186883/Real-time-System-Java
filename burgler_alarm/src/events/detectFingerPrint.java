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
public class detectFingerPrint {

    private String current_reading;

    public detectFingerPrint(String current_reading) {
        this.current_reading = current_reading;
    }

    public String getCurrent_reading() {
        return current_reading;
    }

}
