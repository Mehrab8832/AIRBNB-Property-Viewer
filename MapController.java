import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * The map class that controls the functionality of the map panel
 *
 * @authors Nikita and Bradly
 * @version 05.04.2021
 */
public class MapController {

    private HashMap<String, ArrayList<AirbnbListing>> boroughHashMap;

    //a hashmap that links every polygon to a unique borough.
    private HashMap<String, Polygon> boroughToHex = new HashMap<>();
    //fxml 'id' of a button.
    private String id = new String();
    private int fromPrice, toPrice;
    private AirbnbListing rowData;

    @FXML
    Polygon ENFI_hex,BARN_hex,HRGY_hex,WALT_hex,HRRW_hex,BREN_hex,CAMD_hex,
            ISLI_hex,HACK_hex,REDB_hex,HAVE_hex,HILL_hex,EALI_hex,KENS_hex,
            WSTM_hex,TOWH_hex,NEWH_hex,BARK_hex,HOUN_hex,HAMM_hex,WAND_hex,
            CITY_hex,GWICH_hex,BEXL_hex,RICH_hex,MERT_hex,LAMB_hex,STHW_hex,
            LEWS_hex, KING_hex,SUTT_hex, CROY_hex, BROM_hex;


    @FXML
    protected void initialize(){

        boroughToHex.put("Enfield",ENFI_hex);
        boroughToHex.put("Barnet",BARN_hex);
        boroughToHex.put("Haringey",HRGY_hex);
        boroughToHex.put("Waltham Forest",WALT_hex);
        boroughToHex.put("Harrow",HRRW_hex);
        boroughToHex.put("Brent",BREN_hex);
        boroughToHex.put("Camden",CAMD_hex);
        boroughToHex.put("Islington",ISLI_hex);
        boroughToHex.put("Hackney",HACK_hex);
        boroughToHex.put("Redbridge",REDB_hex);
        boroughToHex.put("Havering",HAVE_hex);
        boroughToHex.put("Hillingdon",HILL_hex);
        boroughToHex.put("Ealing",EALI_hex);
        boroughToHex.put("Kensington and Chelsea",KENS_hex);
        boroughToHex.put("Westminster",WSTM_hex);
        boroughToHex.put("Tower Hamlets",TOWH_hex);
        boroughToHex.put("Newham",NEWH_hex);
        boroughToHex.put("Barking and Dagenham",BARK_hex);
        boroughToHex.put("Hounslow",HOUN_hex);
        boroughToHex.put("Hammersmith and Fulham",HAMM_hex);
        boroughToHex.put("Wandsworth",WAND_hex);
        boroughToHex.put("City of London",CITY_hex);
        boroughToHex.put("Greenwich",GWICH_hex);
        boroughToHex.put("Bexley",BEXL_hex);
        boroughToHex.put("Richmond upon Thames",RICH_hex);
        boroughToHex.put("Merton",MERT_hex);
        boroughToHex.put("Lambeth",LAMB_hex);
        boroughToHex.put("Southwark",STHW_hex);
        boroughToHex.put("Lewisham",LEWS_hex);
        boroughToHex.put("Kingston upon Thames",KING_hex);
        boroughToHex.put("Sutton",SUTT_hex);
        boroughToHex.put("Croydon",CROY_hex);
        boroughToHex.put("Bromley",BROM_hex);
    }

    /**
     * This method is called in MainWindowController whenever the user navigates to the borough map panel.
     * it highlights each borough red or green depending on the number of available properties in that borough (
     * the number of available properties is depends on the 'from' and 'to' values a user has selected).
     * @param listingsByBorough A hashmap with keys as boroughs and values as properties of that borough.
     * @param fromPrice spinner from price value selected by the user
     * @param toPrice spinner to price value selected by the user
     */
    public void highlightPriceRangeBoroughs(HashMap<String, ArrayList<AirbnbListing>> listingsByBorough,
                                            int fromPrice, int toPrice )
    {
        Set<String> allBoroughs = listingsByBorough.keySet();
        HashMap<String,ArrayList<Integer>> pricesInBoroughs = new HashMap<>();

        this.fromPrice = fromPrice;
        this.toPrice = toPrice;
        this.boroughHashMap = listingsByBorough;

        for (String borough:
                allBoroughs) {

            ArrayList<Integer> prices = new ArrayList<>();
            for (AirbnbListing listing:
                    listingsByBorough.get(borough)) {

                prices.add(listing.getPrice());

            }

            if (pricesInBoroughs.get(borough) == null){

                pricesInBoroughs.put(borough,prices);

            }
        }

        for (String borough :
                allBoroughs) {
            Boolean correctPrice = false;
            for (int price: pricesInBoroughs.get(borough)
            ) {
                if(price >= fromPrice && price <= toPrice){
                    correctPrice = true;
                }
            }
            if ( correctPrice ) {
                boroughToHex.get(borough).setFill(Color.GREEN);
            } else {
                boroughToHex.get(borough).setFill(Color.RED);
            }
        }
    }

    /**
     * The borough click method stores the borough pressed by a user using SceneBuilder id values set to the name
     * of each borough.
     * @param event
     * @throws Exception
     */
    @FXML
    public void boroughClick(ActionEvent event) throws Exception {
        //open info on borough
        final Node source = (Node) event.getSource();
        id = source.getId();
        openPropertyWindow();
    }

    /**
     * After pressing one of the borough's buttons and calling the boroughClick method, the openPropertyWindow method
     * is called which creates a tableView object and displays to the user all available properties within the spec-
     * ified price range within the selected borough. The borough's name appears at the title of the window.
     * @throws Exception
     */
    public void openPropertyWindow() throws Exception{
        try {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            TableView<AirbnbListing> tableOne = new TableView<>();

            TableColumn<AirbnbListing, String> nameColumn = new TableColumn<>("Name");
            TableColumn<AirbnbListing, Integer> priceColumn = new TableColumn<>("Price");
            TableColumn<AirbnbListing, Integer> reviewsColumn = new TableColumn<>("Reviews");
            TableColumn<AirbnbListing, Integer> minNightsColumn = new TableColumn<>("Minimum Nights");

            nameColumn.setCellValueFactory(new PropertyValueFactory<AirbnbListing, String>("host_name"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<AirbnbListing, Integer>("price"));
            reviewsColumn.setCellValueFactory(new PropertyValueFactory<AirbnbListing, Integer>("numberOfReviews"));
            minNightsColumn.setCellValueFactory(new PropertyValueFactory<AirbnbListing, Integer>("minimumNights"));

            // If a property is selected (by pressing once on it) in the table view, a new window will popup informing
            // the user of a description of that property.
            tableOne.setRowFactory( tv -> {
                        TableRow<AirbnbListing> row = new TableRow<>();
                        row.setOnMouseClicked(event -> {
                            if (event.getClickCount() == 1 && (!row.isEmpty())) {
                                AirbnbListing rowData = row.getItem();
                                this.rowData = rowData;
                                createSpecificPropertyView();
                            }
                        });
                        return row;
            });
            nameColumn.setMinWidth(175);
            priceColumn.setMinWidth(75);
            reviewsColumn.setMinWidth(75);
            minNightsColumn.setMinWidth(150);

            tableOne.getColumns().addAll(nameColumn,priceColumn,reviewsColumn,minNightsColumn);

            ObservableList<AirbnbListing> ObsBoroughView = FXCollections.observableArrayList(sorter());
            tableOne.setItems(ObsBoroughView);


            VBox vBox = new VBox();
            vBox.getChildren().addAll(tableOne);
            Scene scene = new Scene(vBox);

            stage.setTitle(id+" properties");
            stage.setScene(scene);
            stage.show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sorts the ArrayList of properties in the selected borough by returning another ArrayList that holds only those
     * properties which are within the specified price range.
     * @return ArrayList of airbnb listings within the selected price range
     */
    public ArrayList<AirbnbListing> sorter(){
        ArrayList<AirbnbListing> allListingsInBorough = boroughHashMap.get(id);
        ArrayList<AirbnbListing> result = new ArrayList<>();
        for (AirbnbListing listing: allListingsInBorough
             ) {
            if(listing.getPrice() >= fromPrice && listing.getPrice() <= toPrice ){
                result.add(listing);
            }
        }
        return result;
    }

    /**
     * As detailed in the openPropertyWindow method, this createSpecificPropertyView method is called when a user
     * wants to look at a specific property in the list shown.
     * @throws Exception
     */
    public void createSpecificPropertyView(){
        try {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);

            TableView<AirbnbListing> tableOne = new TableView<>();

            TableColumn<AirbnbListing, String> nameColumn = new TableColumn<>("Name");
            TableColumn<AirbnbListing, String> descColumn = new TableColumn<>("Description");


            nameColumn.setCellValueFactory(new PropertyValueFactory<AirbnbListing, String>("host_name"));
            descColumn.setCellValueFactory(new PropertyValueFactory<AirbnbListing, String>("name"));


            nameColumn.setMinWidth(175);
            descColumn.setMinWidth(400);


            tableOne.getColumns().addAll(nameColumn,descColumn);

            ObservableList<AirbnbListing> ObsBoroughView = FXCollections.observableArrayList(getRowData());
            tableOne.setItems(ObsBoroughView);


            VBox vBox = new VBox();
            vBox.getChildren().addAll(tableOne);
            Scene scene = new Scene(vBox);

            stage.setTitle("Name and description of property");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a specific Airbnb listing 'row' in the table of properties shown to a user.
     * @return Returns one listing
     */
    public AirbnbListing getRowData(){
        return rowData;
    }
}