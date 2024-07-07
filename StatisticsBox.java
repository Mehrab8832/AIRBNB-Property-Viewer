
/** Models the
 * statistics boxes
 * in the statistics window.
 *
 * Create and array of StatisticsBox
 * in the controller class and loop over them.
 * @author Koizumi
 */
public class StatisticsBox
{
    private String title = "Default Title";
    private String value = "0";

    /**
     * Constructor
     */
    public StatisticsBox()
    {

    }

    // sets the title of the statistic box
    public void setTitle(String givenTitle)
    {
        title = givenTitle;
    }

    // sets the value of the statistic box
    public void setValue(String givenValue)
    {
        value = givenValue;
    }

    // get methods
    public String getTitle()
    {
        return title;
    }

    public String getValue()
    {
        return value;
    }
}
