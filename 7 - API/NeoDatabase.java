import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.URL;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

/**
 *  The <code>NeoDatabase</code> class contains and manages the <code>NearEarthObject</code> records which have been
 *  downloaded from the online dataset
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */
public class NeoDatabase extends ArrayList<NearEarthObject> {
    /**
     * Comparator currently used to sort the database
     */
    Comparator<NearEarthObject> comp;
    /**
     * Today's date
     */
    Date today = new Date();
    /**
     * Most recent approach date of the <code>NearEarthObject</code>
     */
    Date approachDate;
    /**
     * API KEY specific to this application used to perform queries to the NASA NeoW API
     */
    public static final String API_KEY="TmCfBCGLRSNzuA6QU3mluzr1lKRNLNgcU6n0OR4Q";
    /**
     * URL of the REST API used to conduct queries
     */
    public static final String API_ROOT="https://api.nasa.gov/neo/rest/v1/neo/browse?";

    /**
     * Default constructor
     */
    public NeoDatabase() { }

    /**
     * Builds a query URL given a page number
     * @param pageNumber
     *  Integer ranging from 0 to 715 indicating the page the user wishes to load
     * @return
     *  URL string of the getRequest
     * @throws IllegalArgumentException
     *  if <code>pageNumber</code> is less than 0 or greater than 715
     */
    public String buildQueryURL(int pageNumber) throws IllegalArgumentException {
        if (pageNumber<0||pageNumber>715)
            throw new IllegalArgumentException();
        return API_ROOT+"page="+pageNumber+"&api_key="+API_KEY;
    }

    /**
     * Opens a connection to the data source indicated by <code>queryURL</code> and
     * adds all <code>NearEarthObjects</code> found in the dataset
     * @param queryURL
     *  String containing the URL requesting a dataset from the NASA NeoW service
     * @throws IllegalArgumentException
     *  if <code>queryURL</code> is <code>null</code> or could not be resolved by the server
     */
    public void addAll(String queryURL) throws IllegalArgumentException {
        if (queryURL==null)
            throw new IllegalArgumentException();
        try {
            URL getRequest = new URL(queryURL);
            JSONTokener tokener = new JSONTokener(getRequest.openStream());
            JSONObject root = new JSONObject(tokener);
            JSONArray arr = root.getJSONArray("near_earth_objects");
            for (int i=0; i<arr.length(); i++) {
                JSONArray approachData = arr.getJSONObject(i).getJSONArray("close_approach_data");
                JSONObject estimateDiameter = arr.getJSONObject(i).getJSONObject("estimated_diameter");
                if (approachData.length()==0)
                    continue;
                int j=0;
                approachDate = new Date(approachData.getJSONObject(j).getLong("epoch_date_close_approach"));
                while (j<approachData.length()-1 && approachDate.before(today)) {
                    approachDate = new Date(approachData.getJSONObject(++j).getLong("epoch_date_close_approach"));
                }
                add(new NearEarthObject(
                  arr.getJSONObject(i).getInt("neo_reference_id"),
                  arr.getJSONObject(i).getString("name"),
                  arr.getJSONObject(i).getDouble("absolute_magnitude_h"),
                  estimateDiameter.getJSONObject("kilometers").getDouble("estimated_diameter_min"),
                  estimateDiameter.getJSONObject("kilometers").getDouble("estimated_diameter_max"),
                  arr.getJSONObject(i).getBoolean("is_potentially_hazardous_asteroid"),
                  approachData.getJSONObject(j).getLong("epoch_date_close_approach"),
                  approachData.getJSONObject(j).getJSONObject("miss_distance").getDouble("kilometers"),
                  approachData.getJSONObject(j).getString("orbiting_body")
                  )
                );
            }
            System.out.println("Page loaded successfully!");
        } catch (JSONException e) {
            System.out.println("JSONException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
        if (comp!=null)
            sort(comp);
    }

    /**
     * Sorts the database using the specified <code>Comparator</code> of <code>NearEarthObjects</code>
     * @param comp
     *  <code>Comparator</code> of <code>NearEarthObjects</code> which will be used to sort the database
     * @throws IllegalArgumentException
     *  if <code>comp</code> is null
     */
    public void sortBy(Comparator<NearEarthObject> comp) throws IllegalArgumentException {
        if (comp == null)
            throw new IllegalArgumentException();
        this.comp = comp;
        sort(comp);
    }

    /**
     * Displays the database in a neat, tabular form, listing all member variables for each <code>NearEarthObject</code>
     */
    public void printTable() {
        System.out.printf("%s|%s|%s|%s|%s|%s|%s|%s%n%s%n",
          "    ID    ","               Name               "," Mag. "," Diameter "," Danger "," Close Date "," Miss Dist "," Orbits ",
          "=".repeat(107)
        );
        for (NearEarthObject neo : this) {
            System.out.printf("  %-8d%34s%7.01f%10.03f%9b%7tm-%<td-%<ty%13.0f%9s%n",
              neo.getReferenceID(),
              neo.getName(),
              neo.getAbsoluteMagnitude(),
              neo.getAverageDiameter(),
              neo.getDangerous(),
              neo.getClosestApproachDate(),
              neo.getMissDistance(),
              neo.getOrbitingBody()
            );
        }
    }

}
