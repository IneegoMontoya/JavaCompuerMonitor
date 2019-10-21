/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bjha43cpumonitorfxml;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 *
 * @author Byron Hammann - bjha43
 * 
 */
public class DigitalDisplay {
    
    
    
    private String output;
    private Timeline timeline;
    private double cpuUsage;
    
    public DigitalDisplay(){
        
    }
    
    public void setupTimer(Gauge gauge, Label text){
        
        timeline = new Timeline(new KeyFrame(Duration.millis(5), (ActionEvent event) -> {
            change(gauge,text);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        
        
    }
    
    public void change(Gauge gauge, Label text){
        cpuUsage = gauge.sendCPUUsage();
        cpuUsage *= 100;
        output =( String.format("%.2f",cpuUsage)+ "%");
        text.setText(output);
    }
    
    public String sendUsage(){
        
        output = (String.format("%.2f",cpuUsage)+ "%");
        
        return output;
    }
    
    public void start(){
        timeline.play();
    }
    
    public void pause(){
        
        timeline.pause();
    }
   
    
}
