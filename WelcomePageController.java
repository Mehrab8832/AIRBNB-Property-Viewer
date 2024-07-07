import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * This class controls the welcome panel
 *
 * @authors Mehrab
 * @version 05.04.2021
 */
public class WelcomePageController{

    @FXML
    Spinner FromSpinner, ToSpinner;

    @FXML
    private TextField priceRangeDisplay;

    /**
     * This method changes the value displayed in the price
     * rane text field according to the value that is passed into it.
     * @param priceRangeDisplay new price range to be displayed.
     */
    public void redisplay(String priceRangeDisplay){
        this.priceRangeDisplay.setText(priceRangeDisplay);
    }

}
