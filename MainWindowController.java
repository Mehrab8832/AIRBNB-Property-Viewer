import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * The main class that controls the main window of the AirBnB Program.
 *
 * @authors Nikita, Mehrab, Bradly and Koizumi
 * @version 05.04.2021
 */
public class MainWindowController {

    // buttons used to navigate through the pages
    @FXML
    Button rightArrow, leftArrow;

    @FXML
    BorderPane MainBorderPane;

    // spinners used to input a price range
    @FXML
    Spinner FromSpinner, ToSpinner;

    /**
     * Initialize method that invokes the mainWindowUI as well as the spinners.
     * The arrows are initially set to invisible since a price range is yet to be inputted by the user.
     */
    @FXML
    protected void initialize() {
        loadCenterUI(mainWindowUIs[0]);
        boroughsHoldingListings = propertyList();
        SpinnerValueFactory ToFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0, 10);
        ToSpinner.setValueFactory(ToFactory);

        SpinnerValueFactory FromFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0, 10);
        FromSpinner.setValueFactory(FromFactory);

        //sets the arrows to be invisible initially
        rightArrow.setVisible(false);
        leftArrow.setVisible(false);

    }

    HashMap<String, ArrayList<AirbnbListing>> boroughsHoldingListings = new HashMap<String, ArrayList<AirbnbListing>>();

    //array that stores the names of FXML files
    String[] mainWindowUIs = {"welcomePageStyle", "mapStyle", "StatisticsWindowStyle", "RandomEntryPanel"};
    int currentPage = 0;

    /**
     * Method that implements the functionality of the left arrow.
     *
     * @throws Exception
     */
    public void handleLeftArrow() throws Exception {
        if (currentPage == 1 ) {
            loadCenterUI(mainWindowUIs[currentPage - 1]);
            currentPage--;
        } else if (currentPage == 2) {
            openMap();
            currentPage--;
        } else if(currentPage == 3){
            loadCenterUI(mainWindowUIs[currentPage - 1]);
            currentPage--;
        } else {
            loadCenterUI(mainWindowUIs[mainWindowUIs.length - 1]);
            currentPage = mainWindowUIs.length - 1;
        }
    }

    /**
     * Method that implements the functionality of the right arrow.
     *
     * @throws Exception
     */
    public void handleRightArrow() throws Exception {
        if (currentPage == 0) {
            openMap();
            currentPage++;
        } else if (currentPage == 1) {
            loadCenterUI(mainWindowUIs[currentPage + 1]);
            currentPage++;
        }else if(currentPage == 2){
            openRandomEntryPanel();
            currentPage++;
        }
        else {
            loadCenterUI(mainWindowUIs[0]);
            currentPage = 0;
        }
    }


    /**
     * Method that loads in the different fxml files per window whilst scrolling using the arrow buttons.
     *
     * @param ui
     */
    private void loadCenterUI(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainBorderPane.setCenter(root);
    }

    /**
     * Method that returns the integer value selected in the "From" spinner.
     * @return fromValue.
     *
     * @throws Exception
     */
    public int getFromSpinner() throws Exception {
        int fromValue = (Integer) FromSpinner.getValue();
        return fromValue;
    }

    /**
     * Method that returns the integer value selected in the "To" spinner.
     * @return toValue.
     *
     * @throws Exception
     */
    public int getToSpinner() throws Exception {
        int toValue = (Integer) ToSpinner.getValue();
        return toValue;
    }

    /**
     * Sends an error dialogue window if the user has an incorrect price range.
     *
     * @throws Exception
     */
    public void PRErrorAction () throws Exception
    {
        //creates an error dialogue box that can be displayed when a condition is met
        try {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Price Range");
            alert.setHeaderText(null);
            alert.setContentText("Please input a 'From' value that is lower than 'To'. Try Again.");

            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method sends an error pop up window if the user inputs an incorrect price range.
     * While the correct price range is not inputted by the user, the arrows remain invisible.
     *
     * @throws Exception
     */
    public void onSpinnerMouseClick() throws Exception
    {

        try {
            //loads in the welcomePageStyle so that an object can be created to invoke its methods
            FXMLLoader loader = new FXMLLoader(getClass().getResource("welcomePageStyle.fxml"));
            Parent root = loader.load();

            //checks if the correct price range has been selected
            WelcomePageController welcomePC = loader.getController();
            if (getFromSpinner() <= getToSpinner()) {
                welcomePC.redisplay("£" + getFromSpinner() + " - " + getToSpinner());
                rightArrow.setVisible(true);
                leftArrow.setVisible(true);
            } else {
                PRErrorAction();
                rightArrow.setVisible(false);
                leftArrow.setVisible(false);
            }

            MainBorderPane.setCenter(root);
        } catch (Exception e) {

        }

    }

    /**
     * This method creates an HashMap with borough names as keys
     * and arraylists of listings inside those boroughs as values
     * @return Hashmap with airbnb listings identified by the name of borough.
     */
    public HashMap<String, ArrayList<AirbnbListing>> propertyList() {
        AirbnbDataLoader list = new AirbnbDataLoader();
        HashMap<String, ArrayList<AirbnbListing>> boroughsHoldingListings = new HashMap<String, ArrayList<AirbnbListing>>();

        for (AirbnbListing property : list.load()) {

            // checking borough of element exists in hashmap as key
            if (boroughsHoldingListings.containsKey(property.getNeighbourhood())) {
                //if yes - add this property to the arraylist of that key value pair
                boroughsHoldingListings.get(property.getNeighbourhood()).add(property);
            } else {
                //if no - create a key in the hashmap with a value of an empty arraylist holding property
                ArrayList<AirbnbListing> tempArr = new ArrayList<AirbnbListing>();
                tempArr.add(property);
                boroughsHoldingListings.put(property.getNeighbourhood(), tempArr);
            }

        }

        return boroughsHoldingListings;

    }

    /**
     * This method changes the middle panel to a map of all boroughs,
     * where the user can select a specific borough
     *
     * @throws Exception
     */
    public void openMap() throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("mapStyle.fxml"));
        Parent root = loader.load();

        MapController mapController = loader.getController();
        mapController.highlightPriceRangeBoroughs(boroughsHoldingListings, getFromSpinner(), getToSpinner());

        MainBorderPane.setCenter(root);

    }

    /**
     * This method opens the fourth panel where the user can get a random listing
     * displayed in a new window on press of a button.
     *
     * @throws Exception
     */
    public void openRandomEntryPanel() throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("RandomEntryPanel.fxml"));
        Parent root = loader.load();

        MainBorderPane.setCenter(root);

    }
}