import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

/** Controls the
 * statistics window GUI
 * @author Koizumi
 */
public class StatisticsWindowController
{
    /**
     * Instantiate boroughs here.
     *
     * Instantiate boroughs in MainWindowController
     * put them in an ArrayList, then pass them into
     * this class instead?
     */

    private Borough king = new Borough("Kingston upon Thames");
    private Borough croy = new Borough("Croydon");
    private Borough brom = new Borough("Bromley");
    private Borough houn = new Borough("Hounslow");
    private Borough eali = new Borough("Ealing");
    private Borough have = new Borough("Havering");
    private Borough hill = new Borough("Hillingdon");

    private Borough harr = new Borough("Harrow");
    private Borough bren = new Borough("Brent");
    private Borough walt = new Borough("Waltham Forest");
    private Borough city = new Borough("City of London");
    private Borough newh = new Borough("Newham");
    private Borough camd = new Borough("Camden");
    private Borough gree = new Borough("Greenwich");
    private Borough hack = new Borough("Hackney");
    private Borough hamm = new Borough("Hammersmith and Fulham");
    private Borough isli = new Borough("Islington");
    private Borough kens = new Borough("Kensington and Chelsea");
    private Borough lamb = new Borough("Lambeth");
    private Borough lewi = new Borough("Lewisham");
    private Borough sout = new Borough("Southwark");
    private Borough towe = new Borough("Tower Hamlets");
    private Borough wand = new Borough("Wandsworth");
    private Borough west = new Borough("Westminster");
    private Borough bark = new Borough("Barking and Dagenham");
    private Borough barn = new Borough("Barnet");
    private Borough bexl = new Borough("Bexley");
    private Borough enfi = new Borough("Enfield");
    private Borough hari = new Borough("Haringey");
    private Borough mert = new Borough("Merton");
    private Borough redb = new Borough("Redbridge");
    private Borough rich = new Borough("Richmond upon Thames");
    private Borough sutt = new Borough("Sutton");

    @FXML
    Button prevBoroughArrow, nextBoroughArrow;

    // name of the borough currently being shown
    @FXML
    Label boroughName;

    // navigate statistics boxes
    @FXML
    Button box1LeftArrow, box1RightArrow;
    @FXML
    Button box2LeftArrow, box2RightArrow;
    @FXML
    Button box3LeftArrow, box3RightArrow;
    @FXML
    Button box4LeftArrow, box4RightArrow;

    // the displays
    @FXML
    Label box1Title, box1Value;
    @FXML
    Label box2Title, box2Value;
    @FXML
    Label box3Title, box3Value;
    @FXML
    Label box4Title, box4Value;

    // the POSSIBLE statistics boxes
    private StatisticsBox box1 = new StatisticsBox();
    private StatisticsBox box2 = new StatisticsBox();
    private StatisticsBox box3 = new StatisticsBox();
    private StatisticsBox box4 = new StatisticsBox();
    private StatisticsBox box5 = new StatisticsBox();
    private StatisticsBox box6 = new StatisticsBox();
    private StatisticsBox box7 = new StatisticsBox();
    private StatisticsBox box8 = new StatisticsBox();

    // Arrays that are used to navigate the through the boxes
    private ArrayList<StatisticsBox> topLeftBoxes = new ArrayList<>();
    private ArrayList<StatisticsBox> topRightBoxes = new ArrayList<>();
    private ArrayList<StatisticsBox> bottomLeftBoxes = new ArrayList<>();
    private ArrayList<StatisticsBox> bottomRightBoxes = new ArrayList<>();

    // indexes to navigate through the arrays
    private int topLeftIndex = 0;
    private int topRightIndex = 0;
    private int bottomLeftIndex = 0;
    private int bottomRightIndex = 0;

    // the list of boroughs
    ArrayList<Borough> boroughList = new ArrayList<>();

    // index to navigate through the borough array
    private int boroughListIndex = 0;

    // the current borough being displayed on the page
    private Borough currentBorough;

    // data from the csv file into here
    ArrayList<AirbnbListing> listings = new ArrayList<>();


    @FXML
    protected void initialize()
    {
        listings = new AirbnbDataLoader().load(); // the boroughs in the borough list will use this arrayList

        makeBoroughList();
        makeStatisticsBoxesLists(); // add boroughs and boxes into arrayLists

        currentBorough = boroughList.get(0); // initialises the current borough the page is displaying

        boroughName.setText(currentBorough.getBoroughName()); // display the name of the initial borough
        initializeBoxTitles(); // place the appropriate titles into all of the boxes
        initializeBoxValues(); // place the appropriate values into all of the boxes
        setBoxDisplay(); // display the titles and values of the starting configuration of boxes
    }

    // sets up the list of boroughs
    private void makeBoroughList()
    {
        boroughList.add(king);
        boroughList.add(croy);
        boroughList.add(brom);
        boroughList.add(houn);
        boroughList.add(eali);
        boroughList.add(have);
        boroughList.add(hill);
        boroughList.add(harr);
        boroughList.add(bren);
        boroughList.add(walt);
        boroughList.add(city);
        boroughList.add(newh);
        boroughList.add(camd);
        boroughList.add(gree);
        boroughList.add(hack);
        boroughList.add(hamm);
        boroughList.add(isli);
        boroughList.add(kens);
        boroughList.add(lamb);
        boroughList.add(lewi);
        boroughList.add(sout);
        boroughList.add(towe);
        boroughList.add(wand);
        boroughList.add(west);
        boroughList.add(bark);
        boroughList.add(barn);
        boroughList.add(bexl);
        boroughList.add(enfi);
        boroughList.add(hari);
        boroughList.add(mert);
        boroughList.add(redb);
        boroughList.add(rich);
        boroughList.add(sutt);
    }

    // puts statistics boxes into arrays to facilitate navigation
    private void makeStatisticsBoxesLists()
    {
        // statistics that appear in the top left
        topLeftBoxes.add(box1);
        topLeftBoxes.add(box5);

        // statistics that appear in the top right
        topRightBoxes.add(box2);
        topRightBoxes.add(box6);

        // statistics that appear in the bottom left
        bottomLeftBoxes.add(box3);
        bottomLeftBoxes.add(box7);

        // statistics that appear in the bottom right
        bottomRightBoxes.add(box4);
        bottomRightBoxes.add(box8);
    }

    // initializes the titles of the boxes (only called at the beginning)
    private void initializeBoxTitles()
    {
        box1.setTitle("Average Number of Reviews per Property");
        box2.setTitle("Number of Available Properties");
        box3.setTitle("Number of 'Entire Home' Properties");
        box4.setTitle("Average Property Price");

        box5.setTitle("Total Number of Reviews in this Borough");
        box6.setTitle("Average Monthly Reviews per Property");
        box7.setTitle("Average Property Availability (in days per year)");
        box8.setTitle("Most Common Room Type");
    }

    // places the statistics of the current borough into the statistics boxes
    private void initializeBoxValues()
    {
        box1.setValue(currentBorough.getAvgReviewCount(listings));
        box2.setValue(currentBorough.getAvailablePropertiesCount(listings));
        box3.setValue(currentBorough.getEntireHomeCount(listings));
        box4.setValue(currentBorough.getAvgPropertyPrice(listings));

        box5.setValue(currentBorough.getTotalReviewCount(listings));
        box6.setValue(currentBorough.getAvgMonthReviews(listings));
        box7.setValue(currentBorough.getAvgAvailability(listings));
        box8.setValue(currentBorough.getMostCommonType(listings));
    }

    // update the displayed values
    private void setBoxDisplay()
    {
        box1Title.setText(topLeftBoxes.get(topLeftIndex).getTitle());
        box2Title.setText(topRightBoxes.get(topRightIndex).getTitle());
        box3Title.setText(bottomLeftBoxes.get(bottomLeftIndex).getTitle());
        box4Title.setText(bottomRightBoxes.get(bottomRightIndex).getTitle());

        box1Value.setText(topLeftBoxes.get(topLeftIndex).getValue());
        box2Value.setText(topRightBoxes.get(topRightIndex).getValue());
        box3Value.setText(bottomLeftBoxes.get(bottomLeftIndex).getValue());
        box4Value.setText(bottomRightBoxes.get(bottomRightIndex).getValue());
    }

    private void resetBoxValues() // use this to reset the configuration whenever borough arrows are called
    {
        topLeftIndex = 0;
        topRightIndex = 0;
        bottomLeftIndex = 0;
        bottomRightIndex = 0;
    }

    /** updates a given statistic box to display the correct values
     *
     * @param box the statistics box that you want to display
     * @param value the text field that you want to display to
     */
    private void updateBoxValue(StatisticsBox box, Label value)
    {
        switch(box.getTitle()) // add in more cases for the 4 additional statistics
        {
            case "Average Number of Reviews per Property":
                value.setText(currentBorough.getAvgReviewCount(listings));
                break;
            case "Number of Available Properties":
                value.setText(currentBorough.getAvailablePropertiesCount(listings));
                break;
            case "Number of 'Entire Home' Properties":
                value.setText(currentBorough.getEntireHomeCount(listings));
                break;
            case "Average Property Price":
                value.setText(currentBorough.getAvgPropertyPrice(listings));
                break;
            case "Total Number of Reviews in this Borough":
                value.setText(currentBorough.getTotalReviewCount(listings));
                break;
            case "Average Monthly Reviews per Property":
                value.setText(currentBorough.getAvgMonthReviews(listings));
                break;
            case "Average Property Availability (in days per year)":
                value.setText(currentBorough.getAvgAvailability(listings));
                break;
            case "Most Common Room Type":
                value.setText(currentBorough.getMostCommonType(listings));
                break;
        }
    }

    /**
     * updates the title of a given statistics box to
     * display the correct values
     */
    private void updateBoxTitle(StatisticsBox box, Label title)
    {
        title.setText(box.getTitle());
    }

    // methods for on click events
    // clean up code duplication using method that uses an array and index as parameters

    /**
     * arrows for navigation between boroughs
     * @throws Exception
     */
    public void onBoroughLeftArrow() throws Exception
    {
        resetBoxValues();

        if (boroughListIndex > 0){
            currentBorough = boroughList.get(boroughListIndex-1); // update current borough

            // update values, then display values
            boroughName.setText(currentBorough.getBoroughName());
            initializeBoxValues();
            setBoxDisplay();
            boroughListIndex--;
        }
        else{
            currentBorough = boroughList.get(boroughList.size()-1);; // update current borough

            // update values, then display values
            boroughName.setText(currentBorough.getBoroughName());
            initializeBoxValues();
            setBoxDisplay();
            boroughListIndex = boroughList.size()-1;
        }
    }

    public void onBoroughRightArrow() throws Exception
    {
        resetBoxValues();

        if (boroughListIndex < boroughList.size()-1){
            currentBorough = boroughList.get(boroughListIndex+1);

            // update values, then display values
            boroughName.setText(currentBorough.getBoroughName());
            initializeBoxValues();
            setBoxDisplay();
            boroughListIndex++;
        }
        else{
            currentBorough = boroughList.get(0);

            // update values, then display values
            boroughName.setText(currentBorough.getBoroughName());
            initializeBoxValues();
            setBoxDisplay();
            boroughListIndex = 0;
        }
    }

    public void onBox1LeftArrow() throws Exception
    {
        if (topLeftIndex > 0){
            updateBoxTitle(topLeftBoxes.get(topLeftIndex-1), box1Title);
            updateBoxValue(topLeftBoxes.get(topLeftIndex-1), box1Value);
            topLeftIndex--;
        }
        else{
            updateBoxTitle(topLeftBoxes.get(topLeftBoxes.size()-1), box1Title);
            updateBoxValue(topLeftBoxes.get(topLeftBoxes.size()-1), box1Value);
            topLeftIndex = topLeftBoxes.size() - 1;
        }
    }

    public void onBox1RightArrow() throws Exception
    {
        if (topLeftIndex < topLeftBoxes.size()-1){
            updateBoxTitle(topLeftBoxes.get(topLeftIndex+1), box1Title);
            updateBoxValue(topLeftBoxes.get(topLeftIndex+1), box1Value);
            topLeftIndex++;
        }
        else{
            updateBoxTitle(topLeftBoxes.get(0), box1Title);
            updateBoxValue(topLeftBoxes.get(0), box1Value);
            topLeftIndex = 0;
        }
    }

    /**
     * arrows for the top right statistic boxes
     * @throws Exception
     */
    public void onBox2LeftArrow() throws Exception
    {
        if (topRightIndex > 0){
            updateBoxTitle(topRightBoxes.get(topRightIndex-1), box2Title);
            updateBoxValue(topRightBoxes.get(topRightIndex-1), box2Value);
            topRightIndex--;
        }
        else{
            updateBoxTitle(topRightBoxes.get(topRightBoxes.size()-1), box2Title);
            updateBoxValue(topRightBoxes.get(topRightBoxes.size()-1), box2Value);
            topRightIndex = topRightBoxes.size() - 1;
        }
    }

    public void onBox2RightArrow() throws Exception
    {
        if (topRightIndex < topRightBoxes.size()-1){
            updateBoxTitle(topRightBoxes.get(topRightIndex+1), box2Title);
            updateBoxValue(topRightBoxes.get(topRightIndex+1), box2Value);
            topRightIndex++;
        }
        else{
            updateBoxTitle(topRightBoxes.get(0), box2Title);
            updateBoxValue(topRightBoxes.get(0), box2Value);
            topRightIndex = 0;
        }
    }

    /**
     * arrows for the bottom left statistic boxes
     * @throws Exception
     */
    public void onBox3LeftArrow() throws Exception
    {
        if (bottomLeftIndex > 0){
            updateBoxTitle(bottomLeftBoxes.get(bottomLeftIndex-1), box3Title);
            updateBoxValue(bottomLeftBoxes.get(bottomLeftIndex-1), box3Value);
            bottomLeftIndex--;
        }
        else{
            updateBoxTitle(bottomLeftBoxes.get(bottomLeftBoxes.size()-1), box3Title);
            updateBoxValue(bottomLeftBoxes.get(bottomLeftBoxes.size()-1), box3Value);
            bottomLeftIndex = bottomLeftBoxes.size() - 1;
        }
    }

    public void onBox3RightArrow() throws Exception
    {
        if (bottomLeftIndex < bottomLeftBoxes.size()-1){
            updateBoxTitle(bottomLeftBoxes.get(bottomLeftIndex+1), box3Title);
            updateBoxValue(bottomLeftBoxes.get(bottomLeftIndex+1), box3Value);
            bottomLeftIndex++;
        }
        else{
            updateBoxTitle(bottomLeftBoxes.get(0), box3Title);
            updateBoxValue(bottomLeftBoxes.get(0), box3Value);
            bottomLeftIndex = 0;
        }
    }

    /**
     * arrows for the bottom right statistic boxes
     * @throws Exception
     */
    public void onBox4LeftArrow() throws Exception
    {
        if (bottomRightIndex > 0){
            updateBoxTitle(bottomRightBoxes.get(bottomRightIndex-1), box4Title);
            updateBoxValue(bottomRightBoxes.get(bottomRightIndex-1), box4Value);
            bottomRightIndex--;
        }
        else{
            updateBoxTitle(bottomRightBoxes.get(bottomRightBoxes.size()-1), box4Title);
            updateBoxValue(bottomRightBoxes.get(bottomRightBoxes.size()-1), box4Value);
            bottomRightIndex = bottomRightBoxes.size() - 1;
        }
    }

    public void onBox4RightArrow() throws Exception
    {
        if (bottomRightIndex < bottomRightBoxes.size()-1){
            updateBoxTitle(bottomRightBoxes.get(bottomRightIndex+1), box4Title);
            updateBoxValue(bottomRightBoxes.get(bottomRightIndex+1), box4Value);
            bottomRightIndex++;
        }
        else{
            updateBoxTitle(bottomRightBoxes.get(0), box4Title);
            updateBoxValue(bottomRightBoxes.get(0), box4Value);
            bottomRightIndex = 0;
        }
    }
}

