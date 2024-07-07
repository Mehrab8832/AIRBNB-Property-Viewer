
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;


/**
 * The main window class controls the initialisation of the entire application
 *
 * @author Nikita
 */
public class MainWindow extends Application
{
    @FXML
    BorderPane MainBorderPane;

    /**
     * The start method is the main entry point for this AirBNB Listing application.
     *
     * @param  stage the primary stage for this application.
     */
    @Override
    public void start(Stage stage) throws Exception {

        try {
            URL url = getClass().getResource("MainWindowStyle.fxml");
            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root);

            stage.setTitle("Airbnb London Property Viewer ");
            stage.setScene(scene);

            // Show the Stage (window)
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }





    }

}
