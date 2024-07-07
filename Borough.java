import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Models the boroughs of the map
 * @author Koizumi
 */
public class Borough
{
    private String boroughName;

    // average no of reviews per property in that borough
    private double avgReviewCount;
    // total no of available properties in that borough
    private int availablePropertiesCount;
    // total no of properties that are 'Entire home/apt' in that borough
    private int entireHomeCount;
    // the average price of properties in that borough
    private double avgPropertyPrice;

    // add in 4 additional statistic methods

    // the total number of property reviews in this borough
    private int totalReviewCount;
    // the average number of reviews that properties get in this borough per month
    private double avgMonthReviews;
    // the average number of days that a property is available for this borough.
    private double avgAvailability;
    // the most common type of room for a property in this borough.
    private String mostCommonType;

    /**
     * Constructor for Borough
     * 
     * param boroughName supply the name of the borough
     * EXACTLY as it says on the neighbourhood field in the
     * csv file when instantiating a new Borough 
     */
    public Borough(String boroughName)
    {
        this.boroughName = boroughName;

        this.avgReviewCount = 0;
        this.availablePropertiesCount = 0;
        this.entireHomeCount = 0;
        this.avgPropertyPrice = 0;

        this.totalReviewCount = 0;
        this.avgMonthReviews = 0;
        this.avgAvailability = 0;
        this.mostCommonType = "";
    }

    // get methods that convert the statistics into strings

    /**
     * Gets the average number of reviews for this
     * borough.
     * @param listings the ArrayList of AirbnbListings to
     * look at
     */
    public String getAvgReviewCount(ArrayList<AirbnbListing> listings)
    {
        double total = 0;
        int i = 0; // counts the index of the arrayList
        int j = 0; // counts the number of listings in the borough

        while(i < listings.size())
        {
            if(isInBorough(listings.get(i)))
            {
                total += listings.get(i).getNumberOfReviews();
                j += 1;
            }
            i += 1;
        }

        avgReviewCount = total/j;
        return roundToTwoDecimalPlaces(avgReviewCount);
    }

    /**
     * Gets the number of available properties for this
     * borough.
     * @param listings the ArrayList of AirbnbListings to
     * look at
     */
    public String getAvailablePropertiesCount(ArrayList<AirbnbListing> listings)
    {
        int count = 0;
        int i = 0;

        while(i < listings.size())
        {
            if(isInBorough(listings.get(i)) && listings.get(i).getAvailability365() > 0)
            {
                count += 1;
            }
            i += 1;
        }
        availablePropertiesCount = count;
        return "" + availablePropertiesCount;
    }

    /**
     * Gets the number of 'Entire home/apt' properties for this
     * borough.
     * @param listings the ArrayList of AirbnbListings to
     * look at
     */
    public String getEntireHomeCount(ArrayList<AirbnbListing> listings)
    {
        int count = 0;
        int i = 0;

        while(i < listings.size())
        {
            if(isInBorough(listings.get(i)) && listings.get(i).getRoom_type().equals("Entire home/apt"))
            {
                count += 1;
            }
            i += 1;
        }
        entireHomeCount = count;
        return "" + entireHomeCount;
    }

    /**
     * Gets the average price of properties for this
     * borough.
     * @param listings the ArrayList of AirbnbListings to
     * look at
     */
    public String getAvgPropertyPrice(ArrayList<AirbnbListing> listings)
    {
        double total = 0;
        int i = 0;
        int j = 0;

        while(i < listings.size())
        {
            if(isInBorough(listings.get(i)))
            {
                double listingPrice = listings.get(i).getPrice() * listings.get(i).getMinimumNights();
                total += listingPrice;
                j += 1;
            }
            i += 1;
        }

        avgPropertyPrice = total/j;
        return roundToTwoDecimalPlaces(avgPropertyPrice);
    }

    /**
     * Gets the total number of property
     * reviews for this particular borough
     * @param listings the ArrayList of AirbnbListings to
     * look at
     */
    public String getTotalReviewCount(ArrayList<AirbnbListing> listings)
    {
        int total = 0;
        int i = 0;

        while(i < listings.size())
        {
            if(isInBorough(listings.get(i)))
            {
                total += listings.get(i).getNumberOfReviews();
            }
            i += 1;
        }
        totalReviewCount = total;
        return "" + total;
    }

    /**
     * Gets the average number of reviews per month
     * for a property in this borough.
     * @param listings the ArrayList of AirbnbListings to
     * look at
     */
    public String getAvgMonthReviews(ArrayList<AirbnbListing> listings)
    {
        double total = 0;
        int i = 0; // counter for all properties in the csv
        int j = 0; // counter for all properties in this specific borough

        while(i < listings.size())
        {
            if(isInBorough(listings.get(i)))
            {
                total += listings.get(i).getReviewsPerMonth();
                j += 1;
            }
            i += 1;
        }
        avgMonthReviews = total/j;
        return roundToTwoDecimalPlaces(avgMonthReviews);
    }

    /**
     * Gets the average number of days that a property
     * is available for this particular borough.
     * @param listings the ArrayList of AirbnbListings to
     * look at
     */

    public String getAvgAvailability(ArrayList<AirbnbListing> listings)
    {
        double total = 0;
        int i = 0; // number of listings overall
        int j = 0; // number of listings in this borough

        while(i < listings.size())
        {
            if(isInBorough(listings.get(i)))
            {
                total += listings.get(i).getAvailability365();
                j += 1;
            }
            i += 1;
        }

        avgAvailability = total/j;
        return roundToTwoDecimalPlaces(avgAvailability);
    }

    /**
     * Gets the most common room type for a room for this
     * particular borough.
     * @param listings the ArrayList of AirbnbListings to
     * look at
     */
    public String getMostCommonType(ArrayList<AirbnbListing> listings)
    {
        int i = 0;

        int pRoomCount = 0;
        int eHomeCount = 0;

        while(i < listings.size())
        {
            if(isInBorough(listings.get(i)))
            {
                if(listings.get(i).getRoom_type().equals("Entire home/apt"))
                {
                    eHomeCount += 1;
                }
                else if(listings.get(i).getRoom_type().equals("Private room"))
                {
                    pRoomCount += 1;
                }
            }
            i += 1;
        }

        /**
         * Could put the counts in an array and loop over them to determine the
         * largest of them all but since there are only 2 possible room types
         * a simple comparison should suffice.
         */
        if(pRoomCount > eHomeCount)
        {
            mostCommonType = "Private Room";
        }
        else
            {
                mostCommonType = "Entire Home";
            }

        return mostCommonType;
    }

    public String getBoroughName()
    {
        return boroughName;
    }
    
    /**
     * Returns true if the listing is in this borough
     * param listing the listing to look at
     * return returns true if the listing is
     * in this borough 
     */
    private boolean isInBorough(AirbnbListing listing)
    {
        return listing.getNeighbourhood().equals(boroughName); // to fix, getNeighbourhood() method is needed but is acting sus af
    }

    private String roundToTwoDecimalPlaces(Double input){
        DecimalFormat df = new DecimalFormat("###.##");
        return df.format(input);
    }
}
