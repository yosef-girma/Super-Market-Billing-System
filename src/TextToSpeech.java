/**
 * Created by test on 10/3/2017.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import com.sun.speech.freetts.FreeTTS;
import com.sun.speech.freetts.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
public class TextToSpeech extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {



        TextArea textArea=new TextArea();


        Button speak=new Button("   Speak it");

          speak.setOnAction(ev ->

          {

//System.setProperty("mbrola.base","C:\\Users\\iup\\workspace\\newpro\\mbrolla");

              Voice voice;
              VoiceManager vm=VoiceManager.getInstance();

              voice=vm.getVoice("kevin16");
              voice.allocate();


              try
              {
                  voice.setVolume(90);
               voice.speak(textArea.getText());
               voice.deallocate();
              }
              catch(Exception e)
              {
                  System.out.println("No input");
              }
           });

          VBox box=new VBox(speak,textArea);
        primaryStage.setScene(new Scene(box,400,400));
        primaryStage.show();



    }
}
