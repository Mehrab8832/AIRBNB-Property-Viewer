import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * A unit test class that tests the methods from the borough class,
 * which are sued for the statistics panel.
 *
 * @authors Nikita
 * @version 05.04.2021
 */
public class BoroughTest {

    private AirbnbListing listing1,listing2,listing3;
    private Borough testingBorough;
    private ArrayList<AirbnbListing> testListings = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        testingBorough = new Borough("Testing");

        listing1 = new AirbnbListing("1","Listing 1","","","Testing", 0,0,"Private room",
                                        30,2,4,"",3,2,150);

        listing2 = new AirbnbListing("2","Listing 2","","","Testing", 0,0,"Entire home/apt",
                                        15,2,15,"",7,4,200);

        listing3 = new AirbnbListing("3","Listing 3","","","Testing", 0,0,"Private room",
                                        40,5,2,"",1,2,300);

        testListings.add(listing1);
        testListings.add(listing2);
        testListings.add(listing3);

    }

    @Test
    public void getAvgReviewCount() {
        double expectedResult = (4+15+2)/3;
        Assert.assertEquals(roundToTwoDecimalPlaces(expectedResult), testingBorough.getAvgReviewCount(testListings));
    }

    @Test
    public void getAvailablePropertiesCount() {
        int expectedResult = 3;
        Assert.assertEquals("" + expectedResult,testingBorough.getAvailablePropertiesCount(testListings));
    }

    @Test
    public void getEntireHomeCount() {
        int expectedResult = 1;
        Assert.assertEquals("" + expectedResult,testingBorough.getEntireHomeCount(testListings));
    }

    @Test
    public void getAvgPropertyPrice() {
        double expectedResult = (30.0 * 2 +15.0 * 2 + 40.0 * 5)/3.0;
        Assert.assertEquals(roundToTwoDecimalPlaces(expectedResult), testingBorough.getAvgPropertyPrice(testListings));
    }

    @Test
    public void getTotalReviewCount() {
        int expectedResult = 4 + 15 + 2;
        Assert.assertEquals(""+expectedResult,testingBorough.getTotalReviewCount(testListings));
    }

    @Test
    public void getAvgMonthReviews() {
        double expectedResult =  (3.0+7.0+1.0)/3.0;
        Assert.assertEquals(roundToTwoDecimalPlaces(expectedResult),testingBorough.getAvgMonthReviews(testListings));
    }

    @Test
    public void getAvgAvailability() {
        double expectedResult = (150.0 + 200.0 + 300.0)/3.0;
        Assert.assertEquals(roundToTwoDecimalPlaces(expectedResult),testingBorough.getAvgAvailability(testListings));
    }

    @Test
    public void getMostCommonType() {
        String expectedResult = "Private Room";
        Assert.assertEquals(expectedResult,testingBorough.getMostCommonType(testListings));
    }

    @Test
    public void getBoroughName() {
        String expectedResult = "Testing";
        Assert.assertEquals(expectedResult,testingBorough.getBoroughName());
    }

    private String roundToTwoDecimalPlaces(Double input){
        DecimalFormat df = new DecimalFormat("###.##");
        return df.format(input);
    }
}