/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bjha43cpumonitorfxml;

import static java.lang.Double.NaN;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author Byron Hammann - bjha43
 */
public class Gauge {
    
    private double cpuUsage;
    private KeyFrame keyFrame;
    private Timeline timeline;
    private static double cpuUse = 0;
    private double angle = 2.7;
    private double rotation;
    
    
    
    public Gauge(){
        
        //setTimer();
    }
    
    public static double getCPUUsage(){
        //used the function provided in the "starter code" 
        
         OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        double value = 0;
        for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            if (method.getName().startsWith("getSystemCpuLoad")
                    && Modifier.isPublic(method.getModifiers())) {
                try {
                    if(NaN== (double)method.invoke(operatingSystemMXBean))
                        return value;
                    else if (NaN!= (double)method.invoke(operatingSystemMXBean))
                        value = (double) method.invoke(operatingSystemMXBean);
                } catch (Exception e) {
                    value = 0;
                }
                return value;
            }
        }
        return value;
    }
 
    public void update(ImageView handImage){
        // send the ImageVeiw item through the controller here to update and let the 
        // display know how much to rotate the hand.
        // eyballed the limits of the face: min is at -135 degrees, and max was around 132.7
        // rounded this to a positive 135, for a range of -135 to 135 for a total of 270 degrees. 
        //By dividing this by 100 the angle gets set to 2.7.
        cpuUsage = Gauge.getCPUUsage();
        rotation = (cpuUsage * angle * 100)-135 ;
        handImage.setRotate(rotation);
        
        }
    
    public void setTimer(ImageView handImage)
            {
           /* Timeline seems pretty jerky still. I had a hard time at first even
              knowing the thing was moving, because my systems cpu usage was always at 0.
              I had to open three games and two other IDE's and leave them running just to make sure
              this thing was working. 
              
              I tried multiple durations 
                1000: was way too jerky
                10,0000: Didn't move at all - and I beleive is too high a frame rate for JavaFX
                5,000: Didn't ever seem to get above 0;
                baclked it down to 3,500: same problem
                2500: seems the least jerky, and most acurate.
                But sped it up to 5
                
                
             
*/
            keyFrame = new KeyFrame(Duration.millis(5), (ActionEvent event) -> {
            update(handImage);
           
            });
            timeline = new Timeline(keyFrame);
            timeline.setCycleCount(Animation.INDEFINITE);
            }
    
      public void start() {
        timeline.play();
    }
      
      public void pause(){
        
        timeline.pause();
    }
      
      
      public boolean isRunning(){
        if(timeline != null){
            if(timeline.getStatus()== Animation.Status.RUNNING)
                return true;
        }
            
        return false;
     
    
    }
      
      public double rotations(){
          return rotation;
      }
    
       public void reset(ImageView handImage){
        
        timeline.pause();
        cpuUsage = 0;
        rotation = 0;
        handImage.setRotate(-135.0);
        timeline.playFromStart();
        timeline.pause();
        
    }
       
        public double sendCPUUsage(){
          
          return cpuUsage;
      }
      
}
    

