/**
 * Sample Skeleton for 'RandomEntryPanel.fxml' Controller Class
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * This class controls the fourth panel with the random listing button.
 *
 * @authors Nikita
 * @version 05.04.2021
 */
public class RandomEntryPanelController {

    @FXML
    private Pane randomListingPane;

    @FXML
    private Button luckyButton;

    // the hyperlink at the bottom right corner of the panel
    @FXML
    private Hyperlink link;



    @FXML
    protected void initialize(){

        link.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

               if (Desktop.isDesktopSupported()){
                   try {
                       Desktop.getDesktop().browse(new URL("https://www.youtube.com/watch?v=oHg5SJYRHA0").toURI());
                   } catch (IOException ioException) {
                       ioException.printStackTrace();
                   } catch (URISyntaxException uriSyntaxException) {
                       uriSyntaxException.printStackTrace();
                   }
               }

            }

        });

    }

    /**
     * Opens a new window with information about a randomly selected listing.
     */
    public void displayRandomListing() throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("RandomEntryDisplay.fxml"));
        Parent root = loader.load();
        AirbnbListing randomListing = new AirbnbDataLoader().randomListing();


        RandomEntryDisplayController randomEntryDisplayController = loader.getController();
        randomEntryDisplayController.populateTextValues(randomListing.getHost_name(), randomListing.getNeighbourhood(),
                                                        randomListing.getRoom_type(),randomListing.getName(),
                                                        randomListing.getPrice(),randomListing.getMinimumNights());


        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

}