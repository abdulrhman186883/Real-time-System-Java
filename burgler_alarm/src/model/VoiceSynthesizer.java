/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author dell
 */
public class VoiceSynthesizer {

    public void report(String facilityName, String location) {
        switch (facilityName) {
            case "Police":
                System.out.println("Police Help us!!, here is our location:" + location);
                break;
            case "Tech":
                System.out.println("Tech please come and fix the issue in location:" + location);

                break;
            case "Manager":
                System.out.println("Manager there were an issue at location:" + location);

                break;
        }
      
    }

}
