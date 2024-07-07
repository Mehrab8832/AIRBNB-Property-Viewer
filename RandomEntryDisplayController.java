import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * This class controls the window that displays a random entry on click of a button
 * in the fourth panel.
 *
 * @authors Nikita
 * @version 05.04.2021
 */
public class RandomEntryDisplayController {

    @FXML
    private Text hostnameText,neighbourhoodText,descriptionText,
            priceText, minNightsText, roomTypeText;

    @FXML
    private Button tryAgainButton;

    @FXML
    private ImageView hostImage;

    @FXML
    protected void initialize(){
        setHostImage();
    }


    /**
     * Assigns values to the text boxes to be displayed.
     */
    public void populateTextValues(String hostnameText, String neighbourhoodText, String roomTypeText,
                                   String descriptionText, int priceText, int minNightsText){
        this.hostnameText.setText(hostnameText);
        this.neighbourhoodText.setText(neighbourhoodText);
        this.roomTypeText.setText(roomTypeText);
        this.descriptionText.setText(descriptionText);
        this.priceText.setText("£" + priceText);
        this.minNightsText.setText(minNightsText + " nights");
        setHostImage();
    }

    /**
     * Changes the displayed values to values from a new random listing
     */
    @FXML
    public void tryAgainButtonPressed(){
        AirbnbListing randomListing = new AirbnbDataLoader().randomListing();

        populateTextValues(randomListing.getHost_name(), randomListing.getNeighbourhood(),
                randomListing.getRoom_type(),randomListing.getName(),
                randomListing.getPrice(),randomListing.getMinimumNights());
    }

    /**
     * Sets the imageView image to an image from thispersondoesnotexist.com
     *  needs an internet connection to work properly
     */
    private void setHostImage(){
        try {
            Image image = new Image("https://thispersondoesnotexist.com/image");
            hostImage.setImage(image);
        }catch (Exception e){
            System.out.println("Could not load image in");
        }
    }
}
