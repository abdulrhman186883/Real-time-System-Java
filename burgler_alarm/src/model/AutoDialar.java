/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;

/**
 *
 * @author dell
 */
public class AutoDialar {

    VoiceSynthesizer voice;
    HashMap<String, String> phoneNumber; // key: Facility name, Value: Number.

    public AutoDialar(VoiceSynthesizer voice) {
        this.voice = voice;
        phoneNumber = new HashMap<>();
        this.phoneNumber.put("Police", "122");
        this.phoneNumber.put("Manager", "01062505830");
        this.phoneNumber.put("Technecian", "01095804595");
    }

    public HashMap<String, String> getPhoneNumber() {
        return phoneNumber;
    }

    public void call(String facilityName, String location) {
        String number = this.phoneNumber.get(facilityName);
        System.out.println("Calling the: " + facilityName + " with the number: " + number);
        if ("Police".equals(facilityName)) {
            voice.report(facilityName, location);
        } else if ("Technecian".equals(facilityName)) {
            voice.report(facilityName, location);
        } else if ("Manager".equals(facilityName)) {
            voice.report(facilityName, location);
        }
    }

}
