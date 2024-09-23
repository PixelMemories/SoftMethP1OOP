
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
    Location(String county, String zip) {
        this.county = county;
        this.zip = zip;
    }

    // Method for getting the county
    public String getCounty() {
        return county;
    }

    // Method for getting the zip code
    public String getZip() {
        return zip;
    }

    // Method to return the county name in uppercase
    public String getCountyUppercase() {
        return county.toUpperCase();
    }

    // Override toString to display location details
    @Override
    public String toString() {
        return String.format("%s County, Zip Code: %s", county, zip);
    }

    // Testing the location enum
    public static void main(String[] args) {
        // Test the getCounty and getZip methods
        System.out.println("Testing Location enum values:");
        for (Location location : Location.values()) {
            System.out.println(location);
        }

        // Example of accessing a specific location
        Location location1 = Location.PRINCETON;
        System.out.println("\nLocation 1: County: " + location1.getCounty() + ", Zip: " + location1.getZip());
    }
}