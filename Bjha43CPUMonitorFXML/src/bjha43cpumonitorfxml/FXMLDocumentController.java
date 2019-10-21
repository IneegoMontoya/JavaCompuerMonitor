/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bjha43cpumonitorfxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 *
 * @author Byron Hammann - bjha43
 * 
 * Controller for the interface created with Scene Builder. 
 * Object handlers for the buttons, and connectivity to the Gauge object data,
 * System time, and the records to be posted.
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button strtButton;
    @FXML
    private Button recButton;
    @FXML
    Gauge gauge = new Gauge();
    @FXML
    ImageView handImage;
    @FXML
    SystemDate systemTime;
    @FXML
    TextField textBox2;
    @FXML
    TextField textBox3;
    @FXML
    TextField textBox1;
    @FXML
    TextField textBox4;
    @FXML
    private RecordBoard recordBoard = new RecordBoard();
    @FXML
    private DigitalDisplay digital = new DigitalDisplay();
    @FXML
    private Label label;
    
    // Event handler for the start button. Will change the start button to the stop
    // and also switch the text values of the buttons depending on their state. 
     @FXML
    private void startButtonAction(ActionEvent event) {
        
        if("Start".equals(strtButton.getText())&& gauge.isRunning()== false)
        {
            gauge.start();
            strtButton.setText("Stop");
            recButton.setText("Record");
            digital.start();
        }
        else if("Stop".equals(strtButton.getText())&& gauge.isRunning()== true)
        {
            recButton.setText("Reset");
            gauge.pause();
            strtButton.setText("Start");
            recButton.setText("Reset");
            digital.pause();
        }
    }
    //Event handler for the record button, which will also become the reset button
    // depending on the state of the monitor.
    @FXML
       private void recButtonAction(ActionEvent event) {
        
           int recs;
        if("Stop".equals(strtButton.getText())){
            
            recordBoard.incrementRecord();
            recs = recordBoard.getRecCount();
            if(recs >4)
                recs = recs%4;
               switch (recs) {
                   case 1:
                       textBox1.setText(recordBoard.dispRecord(gauge, systemTime));
                       textBox1.setAlignment(Pos.CENTER_LEFT);
                       break;
                   case 2:
                       textBox2.setText(recordBoard.dispRecord(gauge, systemTime));
                       textBox2.setAlignment(Pos.CENTER_LEFT);
                       break;   
                   case 3:
                       textBox3.setText(recordBoard.dispRecord(gauge, systemTime));
                       textBox3.setAlignment(Pos.CENTER_LEFT);
                       break;
                    case 0:
                    case 4:
                       textBox4.setText(recordBoard.dispRecord(gauge, systemTime));
                       textBox4.setAlignment(Pos.CENTER_LEFT);
                       break;
                   default:
                       break;
               }
           
        }
        else if("Reset".equals(recButton.getText())) 
        {
            gauge.reset(handImage);
            recButton.setText("Record");
            
            textBox1.setText("--.--%");
            textBox1.setAlignment(Pos.CENTER);
            textBox2.setText("--.--%");
            textBox2.setAlignment(Pos.CENTER);
            textBox3.setText("--.--%");
            textBox3.setAlignment(Pos.CENTER);
            textBox4.setText("--.--%");
            textBox4.setAlignment(Pos.CENTER);
            recordBoard.resetRecords();
            label.setText("--.--%");
        }
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gauge.setTimer(handImage); // initialize the guage timer
        systemTime = new SystemDate(); // allocate a new System Date instance.
        digital.setupTimer(gauge,label); //initialize the digital display timer
    }    
    
}
