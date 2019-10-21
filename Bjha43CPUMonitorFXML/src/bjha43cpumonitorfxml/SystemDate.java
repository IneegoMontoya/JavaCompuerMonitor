/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bjha43cpumonitorfxml;


import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Byron Hammann - bjha43
 * Class to create a date object, format it, and then return it in a form of a string to the controller
 */
public class SystemDate {
    private SimpleDateFormat dateFormat;
    private Calendar calendar;
    
    public SystemDate(){
    
        dateFormat = new SimpleDateFormat("hh:mm:ss a");
        calendar = Calendar.getInstance();
        System.out.println(dateFormat.format(calendar.getTime()));
    }
    
    public String getTime(){
        calendar = Calendar.getInstance();
        String times;
        times = (String)dateFormat.format(calendar.getTime()); 
        return times;
    }
    
    
    
    
}
