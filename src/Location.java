/**
 * Enum representing various clinic locations.
 * Each location has a county and a zip code.
 *
 * @author Divit Shetty (dps190)
 */
public enum Location {
    BRIDGEWATER("Somerset", "08807"),
    EDISON("Middlesex", "08817"),
    PISCATAWAY("Middlesex", "08854"),
    PRINCETON("Mercer", "08542"),
    MORRISTOWN("Morris", "07960"),
    CLARK("Union", "07066");

    private final String county;
    private final String zip;

    // Constructor
    /**
     * Constructs a Location enum with the specified county and zip code.
     *
     * @param county the county of the location
     * @param zip    the zip code of the location
     */
    Location(String county, String zip) {
        this.county = county;
        this.zip = zip;
    }

    // Method for getting the county
    /**
     * Returns the county of the location.
     *
     * @return the county of the location
     */
    public String getCounty() {
        return county;
    }

    // Method for getting the zip code
    /**
     * Returns the zip code of the location.
     *
     * @return the zip code of the location
     */
    public String getZip() {
        return zip;
    }

    //for city name
    /**
     * Returns the name of the city, derived from the enum constant name.
     *
     * @return the name of the city
     */
    public String getCity() {

        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }

    // Method to return the county name in uppercase
    /**
     * Returns the county name in uppercase.
     *
     * @return the county name in uppercase letters
     */
    public String getCountyUppercase() {
        return county.toUpperCase();
    }

    // Override toString to display location details
    /**
     * Overrides toString to display location details in a formatted string.
     *
     * @return formatted string representation of the location
     */
    @Override
    public String toString() {
        return String.format("%s, %s %s", getCity(), county, zip);
    }

    // Testing the location enum
    /**
     * Testbed main method to test the Location enum.
     */
    public static void main(String[] args) {
        // Test the getCounty and getZip methods
        System.out.println("Testing Location enum values:");
        for (Location location : Location.values()) {
            System.out.println(location);
        }

        // Example of accessing a specific location
        Location location1 = Location.PRINCETON;
        System.out.println("\nLocation 1: City: " + location1.getCity() + ", County: " + location1.getCounty() + ", Zip: " + location1.getZip());
    }
}
