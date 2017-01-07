package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.CubicCurve;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import static java.awt.SystemColor.text;
import static sample.Main.PrintedStrings;

/**
 * Created by Bartłomiej on 04.01.2017.
 */
public class serverGUIController implements Initializable, PrintCallBack{

    @FXML ImageView leftComp;
    @FXML ImageView rightComp;
    @FXML ImageView bottomLeftComp;
    @FXML ImageView bottomRightComp;
    @FXML ImageView serverImage;

    @FXML TextArea textComp1;
    @FXML TextArea textComp2;
    @FXML TextArea textComp3;

    @FXML Button startServerButton;

    @FXML CubicCurve leftCompLine;
    @FXML CubicCurve rightCompLine;
    @FXML CubicCurve bottomRightCompLine;
    @FXML CubicCurve bottomLeftCompLine;

    @FXML CubicCurve leftServerLine;
    @FXML CubicCurve rightServerLine;
    @FXML CubicCurve leftBottomServerLine;
    @FXML CubicCurve rightBottomServerLine;

    public static boolean firstConnected;
    public static boolean secondConnected;
    public static boolean thirdConnected;
    public static boolean fourthCOnnected;

    public static boolean firstInClaster;
    public static boolean secondInClaster;
    public static boolean thirdInClaster;
    public static boolean fourthInClaster;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //leftComp.setImage(new Image("file:/C:/Users/Bartłomiej/IdeaProjects/ServerAAApp/desktop-computer-keyboard-mouse.p"));

        leftComp.setVisible(false);

        leftServerLine.setVisible(false);
        rightBottomServerLine.setVisible(false);
        leftBottomServerLine.setVisible(false);
        rightServerLine.setVisible(false);

        rightCompLine.setVisible(false);
        bottomLeftCompLine.setVisible(false);
        bottomRightCompLine.setVisible(false);
        leftCompLine.setVisible(false);

        textComp1.setVisible(false);
    }

    public void printLogs(){
        synchronized (PrintedStrings.stringsToPrint){
            String text = "";
            if(!PrintedStrings.stringsToPrint.isEmpty()) {
                for (String str : PrintedStrings.stringsToPrint) {
                    text = text + str + "\n";
                }
            }
            textComp1.setText(text);

            firstConnected = false; //TODO obsluz wszystkich;
        }
    }


    public void startServerAction(ActionEvent event){
        ServerStarter.start();
        startServerButton.setVisible(false);
        serverImage.setEffect(null);

        Runnable task = () ->{

                do {
                    flagCoordinator();
                    if(textComp1.getText().equals(""))
                        printLogs();

                    //TODO reszta klientow
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }while(true);
        };
        Thread t = new Thread(task);
        t.start();
    }

    public void flagCoordinator(){
        if(firstConnected){
            leftComp.setVisible(true);
            leftServerLine.setVisible(true);
            textComp1.setVisible(true);
        }

        if(firstInClaster){
            leftCompLine.setVisible(true);
        }
    }
}
