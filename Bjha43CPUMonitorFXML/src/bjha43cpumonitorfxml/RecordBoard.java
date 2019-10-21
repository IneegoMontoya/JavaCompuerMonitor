/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bjha43cpumonitorfxml;

/**
 *
 * @author Byron Hammann - bjha43
 * 
 * Object to handle the assembly of what will go on the record board
 */
public class RecordBoard {
    
private static int recCount;
    private double cpuPercent;
    private String output;
    private String date;
    
    public RecordBoard(){
        
    }
    
    public String dispRecord(Gauge gauge, SystemDate systemDate){
       
        cpuPercent = (double)gauge.sendCPUUsage();
        cpuPercent *= 100;
        date = (String)systemDate.getTime();
        
        output = ("Record " +  recCount + ": " + String.format("%.2f",cpuPercent) +  "% at " + date );
        
        return output;
    }
    
    public int getRecCount(){
        return recCount;
    }
    
    public void incrementRecord(){
        recCount++;
    }
    
    public void resetRecords(){
        
        recCount = 0;
    }
}

